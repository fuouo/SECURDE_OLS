package subservlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RMFilter;
import model.RMType;
import model.ReadingMaterial;
import service.ReadingMaterialService;
import servlet.MasterServlet;

/**
 * Servlet implementation class DisplayAuthorServlet
 */
//@WebServlet("/DisplayAuthorServlet")
public class DisplayCategoryServlet{
	private static final long serialVersionUID = 1L;
	public static final String URL = "/DisplayCategoryServlet";
       
    public DisplayCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("DISPLAY CATEGORY GET");
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("DISPLAY CATEGORY POST");
    	
    	ArrayList<ReadingMaterial> readingMaterials = new ArrayList<>();
    	String searchString = (String) request.getParameter(ReadingMaterial.TABLE_RM);
    	RMType rmType = RMType.getValue((String) request.getParameter(RMType.ENUM_RMType));
    	RMFilter rmFilter = RMFilter.getValue((String) request.getParameter(RMFilter.ENUM_RMFilter));
    
    	System.out.println(searchString);
    	System.out.println(rmType);
    	System.out.println(rmFilter);
    	
    	if(rmFilter != null){
	    	//if filter
	    	if(rmFilter == RMFilter.AUTHOR){
	    		readingMaterials = ReadingMaterialService.getAllRMofThisAuthor(searchString);
	    	}else if(rmFilter == RMFilter.PUBLISHER)
	    		readingMaterials = ReadingMaterialService.getAllRMofThisPublisher(searchString);
	    	
    	}
    	else if(rmType != null){
	    	//if type
	    	if(rmType == RMType.BOOK){
	    		readingMaterials = ReadingMaterialService.getRMByType(RMType.BOOK);
	    	}else if(rmType == RMType.MAGAZINE){
	    		readingMaterials = ReadingMaterialService.getRMByType(RMType.MAGAZINE);
	    	}else if(rmType == RMType.THESIS){
	    		readingMaterials = ReadingMaterialService.getRMByType(RMType.THESIS);
	    	}else if(rmType == RMType.ALL){
	    		readingMaterials = ReadingMaterialService.getRMByType(RMType.ALL);
	    	}
    	}
    	
    	request.getSession().setAttribute("numOfRM", readingMaterials.size());
    	request.getSession().setAttribute(ReadingMaterial.TABLE_RM, readingMaterials);

    	request.getRequestDispatcher("/WEB-INF/secured/rm-search-results.jsp").forward(request, response);
    	
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		else 
			doPost(request, response);
	}


}
