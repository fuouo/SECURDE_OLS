package subservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.UserType;
import service.UserService;
import servlet.MasterServlet;
import utils.Utils;

/**
 * Servlet implementation class LoginServlet
 */
public class SignInServlet {

	public static final String URL = "/SignInServlet";
	
    private SignInServlet() { }

    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
    	System.out.println("SIGN IN SERVLET  GET");
    	
	}

	private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("SIGN IN SERVLET POST");
		String refererURI =  request.getParameter("referrer");
		System.out.println("FROM referrer" + refererURI);
		//retrieve attributes sign in details (idnumber, password)
		String idNumber = (String) request.getParameter(User.COL_IDNUMBER);
		String password = (String) request.getParameter(User.COL_PASSWORD);
		System.out.println("[idNumber  --- Password] : " + idNumber + "    " + password);
		//search user from db
		User user = UserService.loginUser(idNumber, password);
		//If user exists, proceed to home page
		if(user!=null){
			System.out.println("USER NOT NULL ");			
			
			// encrypt idnumber
			String stringToHash = user.getIdnumber();
			String idnumber_hashed = Utils.get_SHA_256_SecureString(stringToHash, "");
			
			// Create cookie
			Cookie idNumURLcookie = new Cookie(User.COL_IDNUMBER, idnumber_hashed);
			idNumURLcookie.setHttpOnly(true);
			idNumURLcookie.setSecure(true);
			
			
			// Add cookie to list of cookies
			response.addCookie(idNumURLcookie);	
			
			//Pass first name and last name of user
			request.setAttribute(User.COL_FIRSTNAME, user.getFirstName());
			request.setAttribute(User.COL_LASTNAME, user.getLastName());
			
			System.out.println(UserService.getUserType(user.getIdnumber()));
			//Check if a user is logged in
			Cookie[] cookies = request.getCookies();

			//System.out.println("[Cookies]: " + cookies.length);
			//Search specific cookie
			for(int i = 0; i < cookies.length; i ++) {
				System.out.println(cookies[i].getName());
				}
			
			if(UserService.getUserType(user.getIdnumber()) == UserType.STUDENT || 
					UserService.getUserType(user.getIdnumber()) == UserType.FACULTY) {
				request.setAttribute("referrer", refererURI);
				/*if (refererURI.contains("RMSearchResultsPageServlet"))
					request.getRequestDispatcher("/RMSearchResultsPageServlet").forward(request, response);
				else*/
					request.setAttribute("ErrMessage", "");
					request.getRequestDispatcher("/HomePageServlet").forward(request, response);
					
			} else {
				request.getRequestDispatcher("/AdminAreaServlet").forward(request, response);
			}
		}
		else{
			System.out.println("WRONG EMAIL OR PASSWORD");
			request.setAttribute("ErrMessage", "*Invalid username or password");
			request.getRequestDispatcher("/SignInSignUpPageServlet").forward(request, response);
			
		}
		//else, display error message
		
		
		
	}
	
	public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}
	
	

}
