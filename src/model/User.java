package model;

import java.util.Date;

public class User {
	
	public static final String TABLE_USER = "user";
	public static final String TABLE_LOCKOUT = "lockout";
	
	public static final String COL_IDNUMBER = "id_number";
	public static final String COL_USERTYPE = "user_type";
	public static final String COL_FIRSTNAME = "first_name";
	public static final String COL_MI = "middle_initial";
	public static final String COL_LASTNAME = "last_name";
	public static final String COL_PASSWORD = "passwordHash";
	public static final String COL_EMAIL = "email_address";
	public static final String COL_BDAY = "birthday";
	public static final String COL_SQID = SecretQuestion.COL_SQID;
	public static final String COL_SQANSWER = "sq_answer";
	public static final String COL_STATUS = "status";
	
	public static final String COL_LOCKOUTID = "lockoutID";
	public static final String COL_NUMATTEMPTS = "num_attempts";
	
	protected String idnumber;
	protected String firstName;
	protected String middleInitial;
	protected String lastName;
	protected String password;
	protected String email;
	protected Date birthdate;
	protected SecretQuestion secretQuestion;
	
	protected int lockoutID;
	protected int numAttempts;

	protected String secretAnswer;
	protected UserType userType;
	protected UserStatus status;
	
	public User() {
		
	}

	public String getIdnumber() {
		return idnumber;
	}
	
	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getMiddleInitial() {
		return middleInitial;
	}
	
	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getBirthdate() {
		return birthdate;
	}
	
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	public SecretQuestion getSecretQuestion() {
		return secretQuestion;
	}
	
	public void setSecretQuestion(SecretQuestion secretQuestion) {
		this.secretQuestion = secretQuestion;
	}
	
	public String getSecretAnswer() {
		return secretAnswer;
	}
	
	public void setSecretAnswer(String secretAnswer) {
		this.secretAnswer = secretAnswer;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public int getLockoutID() {
		return lockoutID;
	}

	public void setLockoutID(int lockoutID) {
		this.lockoutID = lockoutID;
	}

	public int getNumAttempts() {
		return numAttempts;
	}

	public void setNumAttempts(int numAttempts) {
		this.numAttempts = numAttempts;
	}
}
