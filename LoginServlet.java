package basicprojects;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	@Override
	public void init() throws ServletException {
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/practice","root","root");
			ps=con.prepareStatement("select * from users where username=? and password=?");	
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Override
	public void destroy() {
		try
		{
			rs.close();
			ps.close();
			con.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		
		String uname=request.getParameter("t1");
		String pass=request.getParameter("t2");
		
		out.println("<html>");
		out.println("<body bgcolor=cyan>");
		try 
		{
			ps.setString(1,uname);
			ps.setString(2, pass);
		    rs=ps.executeQuery();
		    if(rs.next())
		    	out.println("<h1>welcome to servlet page!!");
		    else
		    {
		    	out.println("<h1>user not found");
		    	out.println("<a href=login.html <h3>Try Again</a>");
		    }
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		out.println("</body></html>");
		out.close();
	}
	

}
