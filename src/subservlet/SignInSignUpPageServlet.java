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
//@WebServlet("/SignInSignUpPageServlet")
public class SignInSignUpPageServlet{
	private static final long serialVersionUID = 1L;
	public static final String URL = "/SignInSignUpPageServlet";
       
    public SignInSignUpPageServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("SIGN IN SIGN UP PAGE GET");
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("SIGN IN SIGN UP POST");
    	String refererURI = new URI(request.getHeader("referer")).getPath();
    	request.setAttribute("referrer", refererURI);
    	request.getRequestDispatcher("/WEB-INF/secured/sign-in-sign-up.jsp").forward(request, response);
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}


}
