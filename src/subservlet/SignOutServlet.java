package subservlet;

import java.io.IOException;

import com.sun.org.apache.xerces.internal.util.URI;

import model.User;
import service.CookieService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.MasterServlet;

/**
 * Servlet implementation class SignOutServlet
 */
//@WebServlet("/SignOutServlet")
public class SignOutServlet{
	private static final long serialVersionUID = 1L;
	public static final String URL = "/SignOutServlet";

	public SignOutServlet() {
		super();
		// TODO Auto-generated constructor stub

	}

	private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("SignOutServlet GET");
		
	}

	private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("SignOutServlet POST");

		// get all cookies
		Cookie[] cookies = request.getCookies();

		System.out.println("Cookies: " + cookies.length);

		if(cookies!=null)
		{
			// delete all cookies
			for(int i = 0; i < cookies.length; i ++) {
				if(cookies[i].getName().equals(User.COL_IDNUMBER)) {
					Cookie cookie = cookies[i];
					cookie.setMaxAge(0);
					cookie.setValue(null);
					response.addCookie(cookie);
				}
			}
		}

		// invalidate session
		request.getSession(false).invalidate();

		// web content caching
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.

		System.out.println("Cookies: " + cookies.length);


		request.getRequestDispatcher("HomePageServlet").forward(request, response);
	}

	public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}


}

