package subservlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ReservedRoom;
import model.Room;
import model.User;
import service.CookieService;
import service.ReadingMaterialService;
import service.RoomService;
import servlet.MasterServlet;

/**
 * Servlet implementation class AccountPageServlet
 */
//@WebServlet("/AccountPageServlet")
public class ReserveMRServlet{
	private static final long serialVersionUID = 1L;
	public static final String URL = "/ReserveMRServlet";
       
    public ReserveMRServlet() {
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
    	
    	User user = CookieService.isUser(request);
    	
		//TODO: Please erase this. this is for debugging only ... Thanks :) -D
		user = new User();
		user.setIdnumber("11400366");
		////// 
    	
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
			System.out.println("USER IS NOT NULLLLLLL");
			System.out.println("MR ID NUM   " + request.getParameter(ReservedRoom.COL_MRID));
			ReservedRoom rr = new ReservedRoom();
			rr.setMrID(Integer.parseInt(request.getParameter(ReservedRoom.COL_MRID)));
			rr.setReservedDate(new Date());
			rr.setTimeStart(Integer.parseInt(request.getParameter(ReservedRoom.COL_TIMESTART)));
			rr.setTimeEnd(Integer.parseInt(request.getParameter(ReservedRoom.COL_TIMESTART)));
			
			rr.setUser(user);
			
			boolean result = RoomService.checkIfRoomIsReserved(rr);
			if(result)
				request.getRequestDispatcher("MeetingRoomPageServlet").forward(request, response);
			
			result =  !RoomService.reserveRoom(rr); 
			
			System.out.println("Reserved Room ... " + result);
			
			if(result)
				request.getRequestDispatcher("MeetingRoomPageServlet").forward(request, response);
			else
				request.getRequestDispatcher("HomePageServlet").forward(request, response);
		}
		else
			request.getRequestDispatcher("SignInSignUpPageServlet").forward(request, response);
		
		
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}


}
