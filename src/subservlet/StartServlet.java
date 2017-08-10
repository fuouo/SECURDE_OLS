package subservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.UserType;
import service.CookieService;
import service.UserService;
import servlet.MasterServlet;

public class StartServlet{

	public static final String URL = "/StartServlet";
	private static final long serialVersionUID = 1L;
  
    public StartServlet() {super();}
    
    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
    	System.out.println("StartServlet GET");
    	
    	User user = CookieService.isUser(request);
    	
    	if(user!=null) {
    		if(UserService.getUserType(user.getIdnumber()) == UserType.ADMIN ||
        			UserService.getUserType(user.getIdnumber()) == UserType.LIBSTAFF || 
        			UserService.getUserType(user.getIdnumber()) == UserType.LIBMNGR) {
    			request.getSession().setAttribute("destination", "default");
        		request.getRequestDispatcher("/AdminAreaServlet").forward(request, response);
        	} else {
        		request.getRequestDispatcher("/HomePageServlet").forward(request, response);
        	}
    	} else {
    		request.getRequestDispatcher("/HomePageServlet").forward(request, response);
    	}
	}
    
	private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
    	System.out.println("StartServlet POST");
    	
    	User user = CookieService.isUser(request);
    	
    	if(UserService.getUserType(user.getIdnumber()) == UserType.ADMIN ||
    			UserService.getUserType(user.getIdnumber()) == UserType.LIBSTAFF || 
    			UserService.getUserType(user.getIdnumber()) == UserType.LIBMNGR) {
    		request.getSession().setAttribute("destination", "default");
			request.getRequestDispatcher("/AdminAreaServlet").forward(request, response);
    	} else {
    		request.getRequestDispatcher("/HomePageServlet").forward(request, response);
    	}
	}
	
	public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		else
		doPost(request, response);
	}

}
