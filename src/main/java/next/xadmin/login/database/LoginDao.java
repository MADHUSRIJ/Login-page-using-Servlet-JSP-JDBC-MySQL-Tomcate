package next.xadmin.login.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import next.xadmin.login.bean.LoginBean;

public class LoginDao {
	public void loadDriver() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			}
		catch(Exception e) {
			System.out.println("loadDriver "+e);
		}
	}
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","madhu123");
		}
		catch(Exception e) {
			System.out.println("getconnection "+e);
		}
		return conn;
	}
	
	
	public boolean validate(LoginBean loginBean) {
		// TODO Auto-generated method stub
		loadDriver();
		Connection conn = getConnection();
		
		boolean status = false;
		
		String sql = "select * from login where username = ? and password = ?";
		
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			System.out.println("Uname ===== "+loginBean.getUsername());
			ps.setString(1, loginBean.getUsername());
			ps.setString(2, loginBean.getPassword());
			
			ResultSet rs = ps.executeQuery();
			
			status = rs.next();
		}
		catch(Exception e) {
			System.out.println("preparedstmnt "+e);
			
		}
		return status;
	}
	
	
	
}
