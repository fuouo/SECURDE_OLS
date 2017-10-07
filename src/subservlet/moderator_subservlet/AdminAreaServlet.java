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
import utils.MyLogger;

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
    	
    	if(user.getStatus() == UserStatus.PENDING){
    		request.getSession().setAttribute(User.COL_USERTYPE, user.getUserType());
    		request.getRequestDispatcher("/AdminLoadSecretQuestionServlet").forward(request, response);
    	}
    	else {
    		String url = request.getParameter("destination");
    		if(url == null)
    			url = (String) request.getSession().getAttribute("destination");
    		
    		if(url != null){
	    		String userName;
				System.out.println("User is not Null!!");
				userName = (String) user.getFirstName() + " " + user.getLastName();
				System.out.println(userName);
				request.getSession().setAttribute(User.COL_FIRSTNAME+User.COL_LASTNAME,
						userName);
	    		
				request.getSession().setAttribute(User.COL_USERTYPE, user.getUserType());
	    		
	    		System.out.println("URL: " + url);
	    		if(url.equals("default")){
	    			
	    			if(user.getUserType() == UserType.LIBMNGR || user.getUserType() == UserType.LIBSTAFF){
	    				url = "/WEB-INF/secured/a-manage-books.jsp";
	    			}
	    			else if(user.getUserType() == UserType.ADMIN)
	    				url = "/AdminDisplayAccountsServlet";
	    			else{
	    				url = "/HomePageServlet";
	    			}
	    			
	    		}
	    		System.out.println("URL: " + url);
	    	
	    		request.getRequestDispatcher(url).forward(request, response);
    		}else{
    			//unauthorized
    			System.out.println("Unauthorized User. Redirect to error page");
    			request.getRequestDispatcher("/WEB-INF/error/error-unauthorized.jsp").forward(request, response);
    			//request.getRequestDispatcher("/HomePageServlet").forward(request, response);
    			MyLogger.getInstance().severe("Unauthorized user " + user.getIdnumber() + " attempted to access admin homepage.");
    		}
    	}
    	
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}


}
