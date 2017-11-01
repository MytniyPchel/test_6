package updates;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class UpadateHomeLocation
 */
@WebServlet("/UpadateHomeLocation")
public class UpadateHomeLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpadateHomeLocation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String Latitude=null,Longitude=null,email=null;
		Latitude=request.getParameter("Latitude");
		Longitude=request.getParameter("Longitude");
		email=request.getParameter("email");
		
		response.getWriter().print("email = " + email + ", " + Latitude + ", " + Longitude);
		if (email == null || Longitude==null || Latitude==null ) {
			
			response.getWriter().println("please insert parameters");
		}
		else{
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/friends finder", "root", "");
				Statement statement=(Statement) connection.createStatement();
				String sql="UPDATE register SET Latitude='"+Latitude+"' , Longitude='"+Longitude+"' WHERE email='"+email+"'";
				int i=statement.executeUpdate(sql);
				if(i>0){
					response.getWriter().println("data insrt success");
				}
				else{
					response.getWriter().print("DATA INSERT FALSE....");	
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
