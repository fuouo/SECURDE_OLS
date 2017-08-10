package subservlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Review;
import model.User;
import service.ReviewService;
import service.UserService;
import servlet.MasterServlet;

/**
 * Servlet implementation class AccountPageServlet
 */
//@WebServlet("/AccountPageServlet")
public class ReviewServlet{
	private static final long serialVersionUID = 1L;
	public static final String URL = "/ReviewServlet";
       
    public ReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("REVIEW GET");
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("REVIEW POST");
    	String review = request.getParameter("textReview");
    	User user = null;
    	
    	//Check if a user is logged in
		Cookie[] cookies = request.getCookies();

		System.out.println("[Cookies]: " + cookies.length);
		//Search specific cookie
		for(int i = 0; i < cookies.length; i ++) {
			System.out.println(cookies[i].getName());
			if(cookies[i].getName().equals(User.COL_IDNUMBER)) {
				user = UserService.viewProfileUser(cookies[i].getValue());
			}
		}
		
		Review rev = new Review();
		rev.setReview(review);
		rev.setUser(user);
		rev.setDate_reviewed(new Date());
		rev.setRMID(request.getParameter("rmID"));
		ReviewService.addReview(rev);
		
		request.getRequestDispatcher("HomePageServlet").forward(request, response);
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		else doPost(request, response);
	}


}
