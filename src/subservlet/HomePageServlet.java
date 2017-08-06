package subservlet;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.util.URI;

import model.User;
import service.CookieService;
import service.UserService;
import servlet.MasterServlet;

public class HomePageServlet{

	public static final String URL = "/HomePageServlet";
	private static final long serialVersionUID = 1L;
  
    public HomePageServlet() {super();}
    
    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
    	System.out.println("HOMEPAGE GET");
    	
    	User user = CookieService.isUser(request);
    	
		//If user is logged in		
		if(user!=null)
		{
			System.out.println("User is NOT null");
			String userName = (String) user.getFirstName() + " " + user.getLastName();
			request.getSession().setAttribute(User.COL_FIRSTNAME+User.COL_LASTNAME,
					userName);
		}
		else {
			System.out.println("User is null");
			request.getSession().setAttribute(User.COL_FIRSTNAME+User.COL_LASTNAME,
					"Sign In");
			}
			
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
    
	private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("HOMEPAGE POST");
		//Check if a user is logged in
		String refererURI = (String) request.getAttribute("referrer");
		System.out.println("FROM : " + refererURI);
		User user = CookieService.isUser(request);
    	
		//If user is logged in		
		if(user!=null)
		{
			System.out.println("User is NOT null");
			String userName = (String) user.getFirstName() + " " + user.getLastName();
			request.getSession().setAttribute(User.COL_FIRSTNAME+User.COL_LASTNAME,
					userName);
		}
		else {
			System.out.println("User is null");
			request.getSession().setAttribute(User.COL_FIRSTNAME+User.COL_LASTNAME,
					"Sign In");
			}
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		else
		doPost(request, response);
	}

}
