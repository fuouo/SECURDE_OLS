package subservlet;

import java.io.IOException;

import com.sun.org.apache.xerces.internal.util.URI;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.MasterServlet;

/**
 * Servlet implementation class SignInSignUpPageServlet
 */
//@WebServlet("/SessionTimeoutOnCloseBrowserServlet")
public class SessionTimeoutOnCloseBrowserServlet{
	private static final long serialVersionUID = 1L;
	public static final String URL = "/SessionTimeoutOnCloseBrowserServlet";

	public SessionTimeoutOnCloseBrowserServlet() {
		super();
		// TODO Auto-generated constructor stub

	}

	private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("SessionTimeoutOnCloseBrowserServlet GET");

		// invalidate session
		request.getSession(false).invalidate();

		// web content caching
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
	}

	private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("SessionTimeoutOnCloseBrowserServlet POST");

		// invalidate session
		request.getSession(false).invalidate();

		// web content caching
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
	}

	public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}


}
