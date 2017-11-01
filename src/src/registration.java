package src;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class registration
 */
@WebServlet("/registration")
public class registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public registration() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=null,email=null,birthdate=null,password=null,name=null,imgPath=null,Latitude=null,Longitude=null;
		String gender=null;
		
		username=request.getParameter("username");
		email=request.getParameter("email");
		birthdate=request.getParameter("birthdate");
		password=request.getParameter("password");
		gender=request.getParameter("gender");
		name=request.getParameter("name");
		imgPath=request.getParameter("imgPath");
		
		Latitude=request.getParameter("Latitude");
		Longitude=request.getParameter("Longitude");
		
		if(username == "" || email == "" || birthdate == null || password == null || name == null)
		{
			response.getWriter().println("please insert parameters");

		}
		else{
			//response.getWriter().println("success");
			try {
				
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/friends finder", "root", "");
				Statement statement=(Statement) connection.createStatement();
				
				String path = "E:/chandresh/friends finder/images/" + name + ".jpg";
				
				byte[] decode = Base64.decodeBase64(imgPath);
				
				OutputStream out1 = null;
				try{
				 out1 = new BufferedOutputStream(new FileOutputStream(path));
				 out1.write(decode);
				}
				catch (Exception e){
					e.printStackTrace();
				}
				finally 
    			{
    			    if (out1 != null) 
    			    {
    			        out1.close();
    			    }
    			}
				
						
				
				String sql="insert into register (username, email, birthdate, password, gender,name,Latitude,Longitude) values ('"+username+"', '"+email+"', '"+birthdate+"', '"+password+"' ,'"+gender+"' ,'"+name+"' ,'"+Latitude+"' ,'"+Longitude+"')";
				//response.getWriter().print(sql);
				int i=statement.executeUpdate(sql);
				if(i>0){
					response.getWriter().println("data insrt success");
				}
				else{
					response.getWriter().print("DATA INSERT FALSE....");	
				}
				connection.close();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				response.getWriter().print(e1.toString());
				e1.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				response.getWriter().print(e.toString());
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
