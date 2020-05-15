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

  
// Servlet Name 
@WebServlet("/InsertData") 
public class InsertData extends HttpServlet { 
    private static final long serialVersionUID = 1L; 
  
    protected void doGet(HttpServletRequest request,  
HttpServletResponse response) 
        throws ServletException, IOException 
    { 
        try { 
  
            // Initialize the database 
            Connection con = DatabaseConnection.initializeDatabase(); 

            /*
  
            // Create a SQL query to insert data into demo table 
            // demo table consists of two columns, so two '?' is used 
            PreparedStatement st = con 
                   .prepareStatement("insert into demo values(?, ?)"); 
  
            // For the first parameter, 
            // get the data using request object 
            // sets the data to st pointer 
            st.setInt(1, Integer.valueOf(request.getParameter("id"))); 
  
            // Same for second parameter 
            st.setString(2, request.getParameter("string")); 
  
            // Execute the insert command using executeUpdate() 
            // to make changes in database 
            st.executeUpdate(); 

            */
            PrintWriter out = response.getWriter(); 
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from employees limit 4");
            while (rs.next()) {
                  String lastName = rs.getString("last_name");
                  out.println("<html><body><b>Done with a Select statement" + lastName  + "</b></body></html>"); 
            }

  
            // Close all the connections 
            stmt.close(); 
            con.close(); 
  
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
    } 
} 
