import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class M_Controller {
	
	
	PreparedStatement psmt = null;
	Connection conn = null;
	ResultSet rs = null;
	
	model md = new model();
	
	public void bringCharInfo() {
		
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
			
		
		}
		 md.setCharName(name_arr);
	}

}
