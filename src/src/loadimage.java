package src;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Decoder.BASE64Encoder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class loadimage
 */
@WebServlet("/loadimage")
public class loadimage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loadimage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			String username=null, password=null;
			username=request.getParameter("username");
			password=request.getParameter("password");
			if(username==null || password==null)
			{
				response.getWriter().print("please insert parameter..");
			}
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/friends finder", "root", "");
			Statement statement = (Statement) connection.createStatement();
			
			String sql = "select * from register where email='"+username+"' and password='"+password+"' ";
			
			ResultSet resultSet = statement.executeQuery(sql);
			
			JSONArray jsonArray = new JSONArray();
			while(resultSet.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("userid", resultSet.getInt(1));
				jsonObject.put("username", resultSet.getString(6));
				jsonObject.put("name", resultSet.getString(7));
				String path = "E:/chandresh/friends finder/images/" + resultSet.getString(7) + ".jpg";
				BufferedImage bufferedImage = ImageIO.read(new File(path));
				jsonObject.put("imgPath", encodeToString(bufferedImage, "jpg"));
				jsonArray.add(jsonObject);
			}
			response.getWriter().print(jsonArray.toString());
		}	
		catch(Exception e) {
			e.printStackTrace();
			response.getWriter().print("Error : " + e);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public static String encodeToString(BufferedImage image, String type) 
	{
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try 
        {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);

            bos.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return imageString;
    }

}
