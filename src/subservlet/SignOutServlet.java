package subservlet;

import java.io.IOException;

import com.sun.org.apache.xerces.internal.util.URI;

import service.CookieService;

import javax.servlet.ServletException;
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
    	
    	CookieService.deleteAllCookies(request, response);
    	request.getRequestDispatcher("HomePageServlet").forward(request, response);
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("SignOutServlet POST");
    	
    	CookieService.deleteAllCookies(request, response);
    	request.getRequestDispatcher("HomePageServlet").forward(request, response);
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}


}

