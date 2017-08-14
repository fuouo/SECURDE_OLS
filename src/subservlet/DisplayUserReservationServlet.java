package subservlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ReadingMaterial;
import model.ReservedRoom;
import model.User;
import service.CookieService;
import service.ReadingMaterialService;
import service.RoomService;
import servlet.MasterServlet;


public class DisplayUserReservationServlet {

	private static final long serialVersionUID = 1L;
	public static final String URL = "/DisplayUserReservationServlet";
	
    public DisplayUserReservationServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }
	
	private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("DISPLAY USER RESERVATIONS GET");
    	request.getRequestDispatcher("/StartServlet").forward(request, response);
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	// TODO Auto-generated method stub
    	System.out.println("DISPLAY USER RESERVATIONS POST");
    	
    	User user = CookieService.isUser(request);
    	
    	if(user == null)
    		request.getRequestDispatcher("SignInSignUpPageServlet").forward(request, response);
    	else{
    	
	    	ArrayList<ReadingMaterial> rmList = ReadingMaterialService.getCurrentBorrowedRMOfUser(user.getIdnumber());
	    	request.getSession().setAttribute("current_" + ReadingMaterial.TABLE_RESERVEDRM, rmList);
	    	request.getSession().setAttribute("currentRMSize", rmList.size());
	    	
	    	rmList = ReadingMaterialService.getPastBorrowedRMOfUser(user.getIdnumber());
	    	request.getSession().setAttribute("previous_" + ReadingMaterial.TABLE_RESERVEDRM, rmList);
	    	request.getSession().setAttribute("previousRMSize", rmList.size());
	    	
	    	ArrayList<ReservedRoom> mrList = RoomService.getReservedRoomsAtThisDateADMIN(new Date());
	    	request.getSession().setAttribute(ReservedRoom.TABLE_NAME, mrList);
	    	request.getSession().setAttribute("MRSize", mrList.size());
	      	
	    	request.getRequestDispatcher("/WEB-INF/secured/user-reservations.jsp").forward(request, response);
    	}
    	
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		else doPost(request, response);
	}
	
}
