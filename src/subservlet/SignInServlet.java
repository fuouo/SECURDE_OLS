package subservlet;

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
import utils.Utils;
import utils.XssSanitizerUtil;

/**
 * Servlet implementation class LoginServlet
 */
public class SignInServlet {

	public static final String URL = "/SignInServlet";

	private SignInServlet() { }

	private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		System.out.println("SIGN IN SERVLET  GET");
		request.getRequestDispatcher("/SignInSignUpServlet").forward(request, response);

	}

	private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("SIGN IN SERVLET POST");
		String refererURI =  request.getParameter("referrer");
		System.out.println("FROM referrer" + refererURI);
		//retrieve attributes sign in details (idnumber, password)
		String idNumber = (String) XssSanitizerUtil.stripXSS(request.getParameter(User.COL_IDNUMBER));
		String password = (String) XssSanitizerUtil.stripXSS(request.getParameter(User.COL_PASSWORD));
		System.out.println("[idNumber  --- Password] : " + idNumber + "    " + password);
		//search user from db
		User user = UserService.loginUser(idNumber, password);
		//If user exists, proceed to home page
		if(user!=null){
			System.out.println("USER NOT NULL ");	
			
			
			// 1. session handling
			
			// invalidate current session
			request.getSession(false).invalidate();

			// create a new one
			request.getSession(true);
			
			
			// 2. cookie handling

			// encrypt idnumber
			String stringToHash = user.getIdnumber();
			String idnumber_hashed = Utils.get_SHA_256_SecureString(stringToHash, "");

			System.out.println("Hashed: " + idnumber_hashed);

			// Create cookie
			Cookie idNumURLcookie = new Cookie(User.COL_IDNUMBER, idnumber_hashed);
			//idNumURLcookie.setHttpOnly(true);

			// Add cookie to list of cookies
			response.addCookie(idNumURLcookie);	

			//Pass first name and last name of user
			request.setAttribute(User.COL_FIRSTNAME, user.getFirstName());
			request.setAttribute(User.COL_LASTNAME, user.getLastName());

			System.out.println(UserService.getUserType(user.getIdnumber()));

			request.setAttribute("referrer", refererURI);
			request.setAttribute("ErrMessage", "");
			request.getRequestDispatcher("/StartServlet").forward(request, response);

			
		}
		else{
			UserStatus userStatus = UserService.getUserStatus(idNumber);
			
			if(userStatus != null && userStatus.equals(UserStatus.LOCKOUT)) {
				MyLogger.getInstance().warn("Attempted to log in a locked out account for " + idNumber);
				request.getRequestDispatcher("/WEB-INF/error/error-lock-out.jsp").forward(request, response);
				
			} else {

				System.out.println("WRONG EMAIL OR PASSWORD");
				request.setAttribute("ErrMessage", "*Invalid username or password");
				request.getRequestDispatcher("/WEB-INF/error/error-default.jsp").forward(request, response);
			}

		}
		//else, display error message



	}

	public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		else doPost(request, response);
	}



}
