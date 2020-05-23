import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException;

public class DatabaseConnection { 
    protected static Connection initializeDatabase() 
        throws SQLException, ClassNotFoundException 
    { 
        String dbDriver = "com.mysql.jdbc.Driver"; 
        String dbURL = "jdbc:mysql://192.168.33.31:3306/"; 
        String dbName = "employees"; 
        String dbUsername = "app"; 
        String dbPassword = "secret-db-password"; 
  
        Class.forName(dbDriver); 
        Connection con = DriverManager.getConnection(dbURL + dbName, 
                                                     dbUsername,  
                                                     dbPassword); 
        return con; 
    } 
} 
  

