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
			System.out.println("�ڿ��ݳ�����");
			e.printStackTrace();
		}
	}
	
	//�α���
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
			System.out.println("DB ����");
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	//ȸ������
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
			System.out.println("DB ����");
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
	//ĳ���ͻ���
	public void deleteChar(model md) {
		getCon();
		
		try {
			String sql = "delete from char_info where id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, md.getUser_id());
			row = psmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("����̹� �ε� ����");
			e.printStackTrace();
		} finally {
			close();
		}
	}
	//ĳ���� ���� Ȯ��
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
			System.out.println("DB ����");
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	//ĳ���� �����
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
			System.out.println("DB ����");
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	//���� �ƴ���
	public boolean isHitBall(model md, int actNum, int comNum) {
		
		int random = rd.nextInt(100)+1;
		int percent = 40;
		//System.out.println("������ : " + random);
		// ���� ��Ʈ[1] ����[2] ������[3]
		// ���� ��ȭ��[1] �����̴�[2] ����[3]
		//������ ����
		if(md.getOdState() == 0) {
			//������ �����϶� ��ǻ���� ��ȭ��, ����
			if(actNum == 2) {
				if(comNum == 1) {
					percent += 10;
				}
				if(comNum == 3) {
					percent -= 10;
				}
			}
			//������ �������϶� ��ǻ���� ��ȭ��, ����
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
			//��ǻ���� �����϶� ������ ��ȭ��, ����
			if(comNum == 2) {
				if(actNum == 1) {
					percent += 20;
				}
				if(actNum == 3) {
					percent -= 10;
				}
			}
			//��ǻ���� �������϶� ������ ��ȭ��, ����
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
	
	//������ ���
	public String hitBall(model md, int actNum) {
		
		int random = rd.nextInt(100)+1;
		//System.out.println("������ : " + random);
		//��Ʈ 1��Ÿ 40% 2��Ÿ 0%  3��Ÿ 0%  �ƿ� 30% �Ŀ� 10% Ȩ�� 0%
		//���� 1��Ÿ 25% 2��Ÿ 25% 3��Ÿ 20% �ƿ� 10% �Ŀ� 10% Ȩ�� 10%
		//������ 1��Ÿ 25% 2��Ÿ 15% 3��Ÿ 10%  �ƿ� 20% �Ŀ� 10% Ȩ�� 20%
		
		if(actNum == 1)
		{
			if(random <= 40) {
				return "1��Ÿ";
			}else if(random <= 80) {
				return "�ƿ�";
			}
			else{
				return "�Ŀ�";
			}
		}
		else if(actNum == 2){
			if(random <= 25) {
				return "1��Ÿ";
			}else if(random <= 50) {
				return "2��Ÿ";
			}else if(random <= 70) {
				return "3��Ÿ";
			}else if(random <= 80) {
				return "�ƿ�";
			}else if(random <= 90) {
				return "�Ŀ�";
			}else {
				return "Ȩ��";
			}
		}
		else{
			if(random <= 25) {
				return "1��Ÿ";
			}else if(random <= 40) {
				return "2��Ÿ";
			}else if(random <= 50) {
				return "3��Ÿ";
			}else if(random <= 70) {
				return "�ƿ�";
			}else if(random <= 80) {
				return "�Ŀ�";
			}else {
				return "Ȩ��";
			}
		}
	}
	
	//��� ����
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
	// ĳ���� ���� ã��
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