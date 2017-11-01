package src;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=null, password=null;
		boolean isvalide=false;
		username=request.getParameter("username");
		password=request.getParameter("password");
		if (username==null || password==null) {
			response.getWriter().print("please insert data.....");
		}
		else{
		

			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/friends finder", "root", "");
				Statement statement=(Statement) connection.createStatement();
				String sql="select * from register where email='"+username+"' and password='"+password+"' ";
				ResultSet resultSet = statement.executeQuery(sql);		
				
				JSONObject jobj = new JSONObject();	
				while(resultSet.next()){
					jobj.put("userid", resultSet.getInt(1));
					isvalide=true;
				}
			

				
				if(isvalide){
					jobj.put("Islogin","Success");
				}
				else{
					jobj.put("Islogoin","Failed");
				}
				response.getWriter().println(jobj.toString());
				connection.close();
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
