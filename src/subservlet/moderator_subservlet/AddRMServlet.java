package subservlet.moderator_subservlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RMType;
import model.ReadingMaterial;
import service.ReadingMaterialService;
import servlet.MasterServlet;


public class AddRMServlet {

	private static final long serialVersionUID = 1L;
	public static final String URL = "/AddRMServlet";
	
    public AddRMServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }
	
	private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("ADD RM GET");
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("ADD RM POST");
    	
    	System.out.println("RM- " + request.getParameter("newRMType").toUpperCase());
    	System.out.println("Title " + request.getParameter("title"));
    	System.out.println("Loc " + request.getParameter("location-id"));
    	System.out.println("Author " + request.getParameter("author-name"));
    	System.out.println("Publisher " + request.getParameter("publisher"));
    	System.out.println("Year " + request.getParameter("year-published"));
    	System.out.println("Tags " + request.getParameter("tags"));
    	
    	ReadingMaterial rm = new ReadingMaterial();
    	rm.setTitle(request.getParameter("title"));
    	rm.setRMType(RMType.getValue(request.getParameter("newRMType").toUpperCase()));
    	rm.setRMID_Location(request.getParameter("location-id"));
    	rm.setAuthor(request.getParameter("author-name"));
    	rm.setPublisher(request.getParameter("publisher"));
    	rm.setYear(Integer.parseInt(request.getParameter("year-published")));
    	rm.setTags(request.getParameter("tags"));
    	Date today = new Date();
    	rm.setDateArrived(new Date());

    
    	boolean result = ReadingMaterialService.addRM(rm);
    	
    	System.out.println("ADDING BOOK : " + result);
    	
    	if(result) {
    		// redirect to success page
    	} else {
    		// redirect to fail page
    	}
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}
	
}
