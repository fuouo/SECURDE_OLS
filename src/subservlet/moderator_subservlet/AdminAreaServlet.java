package subservlet.moderator_subservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.UserType;
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
    	
    	UserType userType = UserType.ADMIN;
    	

    	if(userType == UserType.ADMIN){
    		request.getRequestDispatcher("/a-manage-accounts.jsp").forward(request, response);
    	}
    	else if(userType == UserType.LIBMNGR || userType == UserType.LIBSTAFF){
    		request.getRequestDispatcher("/a-manage-books.jsp").forward(request, response);
        }
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}


}
