package models.database;

import config.MySql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MySQLConnect {
	public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
                // Chú ý: Thay đổi các thông số kết nối cho phù hợp.
                MySql db = new MySql();
                String hostName = db.get("host");
                String dbName = db.get("DBName");
                String userName = db.get("username");
                String password = db.get("password");
                return getMySQLConnection(hostName, dbName, userName, password);
	 }
	  
	 public static Connection getMySQLConnection(String hostName, String dbName,
	         String userName, String password) throws SQLException,
	         ClassNotFoundException {
	    
	     Class.forName("com.mysql.jdbc.Driver");
	  
	     // Cấu trúc URL Connection đối với MySQL:
	     // Ví dụ: 
	     // jdbc:mysql://localhost:3306/simplehr
	     String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
	  
	     Connection conn = DriverManager.getConnection(connectionURL, userName,
	             password);
	     return conn;
	 }
}
