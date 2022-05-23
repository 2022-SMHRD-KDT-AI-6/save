
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller_yuseon {

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

	public boolean loginId(model md) {
		getCon();
		boolean result = true;
		try {
			String sql = "select id, pw from user_info where ID = ? and PW = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, md.getUser_id());
			psmt.setString(2, md.getUser_pw());
			rs = psmt.executeQuery();
			result =rs.next();
			
				
		} catch (SQLException e) {
			System.out.println("DB 오류");
			e.printStackTrace();
		} finally {

			close();
		}
		return result;
	}

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
			}else {
			
				sql = "insert into user_info values(?, ? ,0)";
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
	



}
