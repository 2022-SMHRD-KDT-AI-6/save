
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;





public class Controller {

	Random rd = new Random();
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	int row = 0;

	public void getCon() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";

			String user = "cgi_8_0516_5";
			String password = "smhrd5";
			conn = DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {

		try {
			if (rs != null) {
				rs.close();
			}

			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("자원반납오류");
			e.printStackTrace();
		}

	}
	
	//로그인
	public boolean loginId(model md) {
		getCon();
		boolean result = true;
		try {
			String sql = "select id, pw from user_info where ID = ? and PW = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, md.getUser_id());
			psmt.setString(2, md.getUser_pw());
			rs = psmt.executeQuery();
			result = rs.next();

		} catch (SQLException e) {
			System.out.println("DB 오류");
			e.printStackTrace();
		} finally {

			close();
		}
		return result;
	}
	//회원가입
	public boolean makeId(model md) {
		getCon();
		boolean result = true;
		try {
			String sql = "select * from user_info where ID = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, md.getUser_id());
			rs = psmt.executeQuery();

			if (rs.next()) {
				return false;
			} else {

				sql = "insert into user_info values(?, ?)";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, md.getUser_id());
				psmt.setString(2, md.getUser_pw());

				row = psmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("DB 오류");
			e.printStackTrace();
		} finally {
			close();
		}

		if (row != 0) {
			return true;
		} else {
			return false;
		}
	}
	//캐릭터삭제
	public int deleteChar(model md) {
	getCon();
	
	boolean result = true;
	try {
		String sql = "delete from char_info where user_id = ?";
		psmt = conn.prepareStatement(sql); 
		psmt.setString(1, md.getUser_id());
		row = psmt.executeUpdate();
	} catch (SQLException e) {
		System.out.println("드라이버 로딩 오류");
		e.printStackTrace();
	} finally {
		close();
	}
	return row;
	}
	//캐릭터 존재 확인
	public boolean checkChar(model md) {
		getCon();
		boolean result = true;
		try {
			String sql = "select * from char_info where ID= ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, md.getUser_id());
			rs = psmt.executeQuery();
			result = rs.next();

		} catch (SQLException e) {
			System.out.println("DB 오류");
			e.printStackTrace();
		} finally {

			close();
		}
		return result;
	}
	
	//볼을 쳤는지 
	public boolean isHitBall(model md) { 
		
		int random = rd.nextInt(100)+1; 
		System.out.println("랜덤수 : " + random); 
		
		if(random <= 35) { 
			return true;
		}return false;
		
	}
	
	//쳤을때 결과 
	public String hitBall(model md) {
		
		int random = rd.nextInt(100)+1;
		System.out.println("랜덤수 : " + random); 
		
		if(random <= 20) { 
			return "ONEBASE";
		}else if(random <= 40) {
			return "DOUBLE";
		}else if(random <= 50) { 
			return "TRIPLE";
		}else if(random <= 80) { 
			return "OUT";
		}else if(random <= 90) {
			return "FOUL";
		}else { 
			return "HOMERUN";
		}
	}
	
	//결과 저장
	public void playUpdate(model md, String gameState) {
		
		int odState = md.getOdState();
		int teamPoint = md.getTeamPoint();
		int enemyPoint = md.getEnemyPoint();
		int strikeCount = md.getStrikeCount();
		int outCount = md.getOutCount();
		
		if(odState==0) {
			switch(gameState) { 
			case "ONEBASE": 
				teamPoint += 1;
				md.setTeamPoint(teamPoint);
				break;
				
			case "DOUBLE": 
				teamPoint += 3;
				md.setTeamPoint(teamPoint);
				break;
				
			case "TRIPLE": 
				teamPoint += 5;
				md.setTeamPoint(teamPoint);
				break;
				
			case "HOMERUN": 
				teamPoint += 10;
				md.setTeamPoint(teamPoint);
				break;
				
			case "FOUL": 
				strikeCount += 1;
				md.setStrikeCount(strikeCount);
				break;
				
			case "OUT":
				outCount += 1;
				md.setOutCount(outCount);
				break;
			}
		}else {
			switch(gameState) {
			case "STRIKE":
				strikeCount += 1;
				md.setStrikeCount(strikeCount);
				break;
			case "OUT":
				outCount += 1;
				md.setOutCount(outCount);
				break;
			case "ONEBASE":
				enemyPoint += 1;
				md.setEnemyPoint(enemyPoint);
				break;
			case "DOUBLE":
				enemyPoint += 3;
				md.setEnemyPoint(enemyPoint);
				break;
			case "TRIPLE":
				enemyPoint += 5;
				md.setEnemyPoint(enemyPoint);
				break;
			case "HOMERUN":
				enemyPoint += 10;
				md.setEnemyPoint(enemyPoint);
				break;
			case "FOUL":
				strikeCount += 1;
				md.setStrikeCount(strikeCount);
				break;
			
			}//switch end
		}//else end
	}// playUpdate end
	
	
//	model md = new model();
	// 캐릭터 정보 찾기
	public void bringCharInfo(model md) {
		getCon();
		
		String[] name_arr = new String[5]; 
		int num = 0;
		
		try {
			String sql = "selelct name from char_info where id = ?";
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, md.getUser_id());
			rs = psmt.executeQuery();
			while(rs.next()) {
				name_arr[num] = rs.getString(2);
				num++;
			}
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			close();
		}
		 md.setCharName(name_arr);
	}//bringcharinfo end

}//Controller end

