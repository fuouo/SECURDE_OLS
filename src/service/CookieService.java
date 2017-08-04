package service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import model.User;

public class CookieService {
	
	public static User isUser(HttpServletRequest request)
	{
		User user = null;
    	
    	//Check if a user is logged in
		Cookie[] cookies = request.getCookies();

		//System.out.println("[Cookies]: " + cookies.length);
		//Search specific cookie
		if(cookies!=null)
		{
			for(int i = 0; i < cookies.length; i ++) {
				System.out.println(cookies[i].getName());
				if(cookies[i].getName().equals(User.COL_IDNUMBER)) {
					user = UserService.viewProfileUser(cookies[i].getValue());
				}
			}
		}
		
		return user;	
	}

}
