package subservlet.moderator_subservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.UserStatus;
import model.UserType;
import service.CookieService;
import service.UserService;
import servlet.MasterServlet;

/**
 * Servlet implementation class SignInSignUpPageServlet
 */
//@WebServlet("/SignInSignUpPageServlet")
public class AdminAreaServlet{
	private static final long serialVersionUID = 1L;
	public static final String URL = "/AdminAreaServlet";
       
    public AdminAreaServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("ADMIN AREA PAGE GET");
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("ADMIN AREA POST");
    	
    	User user = CookieService.isUser(request);
    	
    	if(user != null) {
    		if(user.getStatus() == UserStatus.PENDING){
        		request.getRequestDispatcher("/WEB-INF/secured/a-change-password.jsp").forward(request, response);
        	}
        	else {
        		String url = request.getParameter("destination");
        		if(url == null){
        			
        			if(user.getUserType() == UserType.LIBMNGR || user.getUserType() == UserType.LIBSTAFF){
        				url = "a-manage-books.jsp";
        			}
        			else if(user.getUserType() == UserType.ADMIN)
        				url = "AdminDisplayAccountsServlet";
        			
        		}
        	
        		request.getRequestDispatcher("/WEB-INF/secured/" + url).forward(request, response);
        	}
    	} else {
    		
    	}
    	
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}


}
