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
			if (rs.next()) {
				result = true;
			} else {
				result = false;
			}
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
	public void deleteChar(model md) {
		getCon();
		
		try {
			String sql = "delete from char_info where id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, md.getUser_id());
			row = psmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("드라이버 로딩 오류");
			e.printStackTrace();
		} finally {
			close();
		}
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
			if (rs.next()) {
				result = true;
			} else {
				result = false;
			}
		} catch (SQLException e) {
			System.out.println("DB 오류");
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	//캐릭터 만들기
	public void makeChar(model md) {
		// String[] charName = new String[5];
		String[] charName = md.getCharName();
		getCon();
		try {
			for (int k = 0; k < 5; k++) {
				String sql = "insert into char_info  values(?, ?)";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, md.getUser_id());
				psmt.setString(2, charName[k]);
				row = psmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("DB 오류");
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	//볼을 쳤는지
	public boolean isHitBall(model md, int actNum, int comNum) {
		
		int random = rd.nextInt(100)+1;
		int percent = 40;
		//System.out.println("랜덤수 : " + random);
		// 공격 번트[1] 스윙[2] 강스윙[3]
		// 수비 변화구[1] 슬라이더[2] 직구[3]
		//유저의 공격
		if(md.getOdState() == 0) {
			//유저의 스윙일때 컴퓨터의 변화구, 직구
			if(actNum == 2) {
				if(comNum == 1) {
					percent += 10;
				}
				if(comNum == 3) {
					percent -= 10;
				}
			}
			//유저의 강스윙일때 컴퓨터의 변화구, 직구
			if(actNum == 3) {
				if(comNum == 1) {
					percent -= 10;
				}
				if(comNum == 3) {
					percent += 10;
				}
			}
			else {
				percent = 70;
			}
		}
		else{
			//컴퓨터의 스윙일때 유저의 변화구, 직구
			if(comNum == 2) {
				if(actNum == 1) {
					percent += 20;
				}
				if(actNum == 3) {
					percent -= 10;
				}
			}
			//컴퓨터의 강스윙일때 유저의 변화구, 직구
			if(comNum == 3) {
				if(actNum == 1) {
					percent -= 10;
				}
				if(actNum == 3) {
					percent += 15;
				}
			}
			else {
				percent = 70;
			}
		}
		
		if(random <= percent) {
			return true;
		}
		return false;
		
	}
	
	//쳤을때 결과
	public String hitBall(model md, int actNum) {
		
		int random = rd.nextInt(100)+1;
		//System.out.println("랜덤수 : " + random);
		//번트 1루타 40% 2루타 0%  3루타 0%  아웃 30% 파울 10% 홈런 0%
		//스윙 1루타 25% 2루타 25% 3루타 20% 아웃 10% 파울 10% 홈런 10%
		//강스윙 1루타 25% 2루타 15% 3루타 10%  아웃 20% 파울 10% 홈런 20%
		
		if(actNum == 1)
		{
			if(random <= 40) {
				return "1루타";
			}else if(random <= 80) {
				return "아웃";
			}
			else{
				return "파울";
			}
		}
		else if(actNum == 2){
			if(random <= 25) {
				return "1루타";
			}else if(random <= 50) {
				return "2루타";
			}else if(random <= 70) {
				return "3루타";
			}else if(random <= 80) {
				return "아웃";
			}else if(random <= 90) {
				return "파울";
			}else {
				return "홈런";
			}
		}
		else{
			if(random <= 25) {
				return "1루타";
			}else if(random <= 40) {
				return "2루타";
			}else if(random <= 50) {
				return "3루타";
			}else if(random <= 70) {
				return "아웃";
			}else if(random <= 80) {
				return "파울";
			}else {
				return "홈런";
			}
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
	public model bringCharInfo(model md) {
		getCon();
		
		String[] name_arr = new String[5];
		int num = 0;
		
		try {
			String sql = "select name from char_info where id = ?";
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, md.getUser_id());
			rs = psmt.executeQuery();
			while(rs.next()) {
				name_arr[num] = rs.getString(1);
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
		 return md;
	}
}//Controller end