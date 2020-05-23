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
@WebServlet("/GetAllEmployees") 
public class GetAllEmployees extends HttpServlet { 
    private static final long serialVersionUID = 1L; 
  
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    { 
        try { 
  
            Connection con = DatabaseConnection.initializeDatabase(); 

            JSONArray employees = new JSONArray();
            response.setContentType("application/json");
            PrintWriter out = response.getWriter(); 
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from employees");
            while (rs.next()) {
                  JSONObject employee = new JSONObject();
                  employee.put("emp_no", rs.getInt("emp_no"));
                  employee.put("first_name", rs.getString("first_name"));
                  employee.put("last_name", rs.getString("last_name"));
                  employees.add(employee);
            }
            
            stmt.close(); 
            con.close(); 

            out.println(employees.toJSONString());
            out.close();

  
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
    } 
} 
