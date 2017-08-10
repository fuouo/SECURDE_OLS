package subservlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ReadingMaterial;
import model.User;
import service.CookieService;
import service.ReadingMaterialService;
import servlet.MasterServlet;

/**
 * Servlet implementation class AccountPageServlet
 */
//@WebServlet("/AccountPageServlet")
public class ReserveRMServlet{
	private static final long serialVersionUID = 1L;
	public static final String URL = "/ReserveRMServlet";
       
    public ReserveRMServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("RESERVE RM PAGE GET");
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("RESERVE RM PAGE POST");
    	
    	String location = request.getParameter("locationID");
    	System.out.println("LOCATION " + location);
    	User user = CookieService.isUser(request);
    	
		//If user is logged in		
		if(user!=null)
		{
			System.out.println("User is NOT null");
			String userName = (String) user.getFirstName() + " " + user.getLastName();
			request.getSession().setAttribute(User.COL_FIRSTNAME+User.COL_LASTNAME,
					userName);
		}
		else {
			System.out.println("User is null");
			request.getSession().setAttribute(User.COL_FIRSTNAME+User.COL_LASTNAME,
					"Sign In");
		}
		
		
		
		//If user is logged in
		if(user != null)
		{
			/*request.getSession().setAttribute(User.COL_FIRSTNAME+User.COL_LASTNAME,
											user.getFirstName() + " " + user.getLastName());*/
			System.out.println("USER IS NOT NULLLLLLL");
			System.out.println("ID NUM   " + location);
			
			ReadingMaterial rm = ReadingMaterialService.getRMByID(location);
			rm.setUserReserved(user);
			rm.setDateReserved(Calendar.getInstance().getTime());			// NOW LANG EH
			ReadingMaterialService.reserveRM(rm);
			
			request.getSession().setAttribute("message", "Successfully Reserved Book");
	    	request.getRequestDispatcher("SuccessPageServlet").forward(request, response);
			
		}
		else
			request.getRequestDispatcher("SignInSignUpPageServlet").forward(request, response);
		
		
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		else doPost(request, response);
	}


}
