package model;

public enum UserStatus {
	
	PENDING, ACTIVATED, DEACTIVATED, LOCKOUT;

	public static UserStatus getValue(String userStatus) {
		switch(userStatus) {
		case "PENDING": return PENDING;
		case "ACTIVATED": return ACTIVATED;
		case "DEACTIVATED": return DEACTIVATED;
		case "LOCKOUT" : return LOCKOUT;
		default: return null;
		}
	}
	
}
