package subservlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RMFilter;
import model.RMType;
import model.ReadingMaterial;
import model.User;
import service.CookieService;
import service.ReadingMaterialService;
import servlet.MasterServlet;

/**
 * Servlet implementation class AccountPageServlet
 */
//@WebServlet("/AccountPageServlet")
public class RMSearchResultsPageServlet{
	private static final long serialVersionUID = 1L;
	public static final String URL = "/RMSearchResultsPageServlet";
       
    public RMSearchResultsPageServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("RM SEARCH RESULTS PAGE GET");
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("RM SEARCH RESULTS PAGE POST");
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
    	
    	
		String searchString = (String) request.getParameter(ReadingMaterial.TABLE_RM);
    	RMFilter rmFilter = RMFilter.getValue((String) request.getParameter(RMFilter.ENUM_RMFilter));
    	RMType rmType = RMType.getValue((String) request.getParameter(RMType.ENUM_RMType));
    	System.out.println("Searching " + searchString + " with Filter " + rmFilter + " Type " + rmType);
    	ArrayList<ReadingMaterial> readingMaterials = ReadingMaterialService.searchRM(rmFilter, rmType, searchString);
    	
    	System.out.println("[SEARCH] : " + searchString);
    	/*System.out.println("[RESULTS] : ");
    	for(int i = 0; i < readingMaterials.size(); i++){
    		System.out.println(i + " : " + readingMaterials.get(i).getTitle());
    		System.out.println("	 : " + readingMaterials.get(i).getStatus());
    		System.out.println("     : " + readingMaterials.get(i).getTags());
    	}
    	*/
    	request.getSession().setAttribute("numOfRM", readingMaterials.size());
    	request.getSession().setAttribute(ReadingMaterial.TABLE_RM, readingMaterials);
    	
    	request.getRequestDispatcher("/WEB-INF/secured/rm-search-results.jsp").forward(request, response);
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}


}
