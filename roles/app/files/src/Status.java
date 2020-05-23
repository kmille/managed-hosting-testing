import java.io.IOException;
import java.io.PrintWriter; 
import java.sql.Connection; 
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement; 
  
import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

  
// Servlet Name 
@WebServlet("/status") 
public class Status extends HttpServlet { 
    private static final long serialVersionUID = 1L; 
  
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    { 
        try { 
  
            Connection con = DatabaseConnection.initializeDatabase(); 

            response.setContentType("application/json");
            PrintWriter out = response.getWriter(); 
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT 'up' as state from dual");
            rs.next();
            String state = new String(rs.getString("state"));
            if (state.equals("up")) {
                out.println("{\"state\": \"up\"}");
            } else {
                out.println("{\"state\": \"down\"}");
            }
            
            stmt.close(); 
            con.close(); 

            out.close();

  
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
    } 
} 

