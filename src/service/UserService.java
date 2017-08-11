package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.User;
import model.UserStatus;
import model.UserType;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Encoder;

import utils.MyLogger;
import utils.Utils;
import utils.XssSanitizerUtil;
import db.Query;

public class UserService {

	// register user
	public static boolean registerUser(User user) {
		boolean result = false;

		String query = "\nINSERT INTO " + User.TABLE_USER + "\n"
				+ " VALUES (?, ?, ?, ?, ?, SHA2(?, 512), ?, ?, ?, ?, ?);";

		ArrayList<Object> input = new ArrayList<>();
		input.add(user.getIdnumber());
		input.add(user.getUserType());
		input.add(user.getFirstName());
		input.add(user.getMiddleInitial());
		input.add(user.getLastName());
		input.add(user.getPassword());
		input.add(user.getEmail());
		input.add(Utils.convertDateJavaToStringDB(user.getBirthdate()));
		input.add(user.getSecretQuestion().getSQID());
		input.add(user.getSecretAnswer());
		input.add(UserStatus.ACTIVATED+"");

		Query q = Query.getInstance();

		try {
			result = q.runInsertUpdateDelete(query, input);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	// register libstaff/libmngr
	public static boolean registerLibStaffLibMngr(User user) {
		boolean result = false;

		String query = "\nINSERT INTO " + User.TABLE_USER + "\n"
				+ " VALUES (?, ?, ?, ?, ?, SHA2(?, 512), ?, ?, ?, ?, ?);";

		String query_event = "\nCREATE EVENT activate_event_" + user.getIdnumber() +"\n"
				+ " ON SCHEDULE AT CURRENT_TIMESTAMP + INTERVAL 1 DAY \n" 
				+ " DO \n"
				+ "\tUPDATE " + User.TABLE_USER + "\n"
				+ "\tSET status = ?\n"
				+ "\tWHERE id_number = ? AND status = ?;";

		ArrayList<Object> input = new ArrayList<>();
		input.add(user.getIdnumber());
		input.add(user.getUserType());
		input.add(user.getFirstName());
		input.add(user.getMiddleInitial());
		input.add(user.getLastName());
		input.add(user.getPassword());
		input.add(user.getEmail());
		input.add(Utils.convertDateJavaToStringDB(user.getBirthdate()));
		input.add(user.getSecretQuestion().getSQID());
		input.add(user.getSecretAnswer());
		input.add(UserStatus.PENDING+"");

		Query q = Query.getInstance();

		try {
			result = q.runInsertUpdateDelete(query, input);

			input.clear();
			input.add(UserStatus.DEACTIVATED + "");
			input.add(user.getIdnumber());
			input.add(UserStatus.PENDING + "");

			result = q.runSQLEvent(query_event, input);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	// login
	public static User loginUser(String id_number, String password) {
		User user = null;
		boolean isBruteForce = false;

		String query_idnumber = "\nSELECT " + User.COL_IDNUMBER + ", " + 
				User.COL_STATUS + 
				" FROM " + User.TABLE_USER + 
				" WHERE " + User.COL_IDNUMBER + " = ?;";

		String query_select = "\nSELECT " 
				+ User.COL_IDNUMBER + ", "
				+ User.COL_FIRSTNAME + ", "
				+ User.COL_LASTNAME + ", "
				+ User.COL_STATUS + "\n"
				+ " FROM " + User.TABLE_USER + "\n"
				+ " WHERE " + User.COL_IDNUMBER + " = ?"
				+ " AND " + User.COL_PASSWORD + " = SHA2(?, 512);";

		String query_delete = "\nDELETE FROM " + User.TABLE_LOCKOUT
				+ " WHERE " + User.COL_LOCKOUTID + " = ?;";

		ArrayList<Object> input = new ArrayList<>();
		input.add(id_number);

		Query q = Query.getInstance();
		ResultSet r = null;
		ResultSet r2 = null;

		try {
			r = q.runQuery(query_idnumber, input);

			// id number exists
			if(r.next()) {
				UserStatus status = UserStatus.getValue(r.getString(User.COL_STATUS));

				// check password
				input.clear();
				input.add(id_number);
				input.add(password);

				r2 = q.runQuery(query_select, input);

				// login is successful
				if(r2.next()) {

					// create user
					user = new User();
					user.setIdnumber(XssSanitizerUtil.stripXSS(id_number));
					user.setFirstName(XssSanitizerUtil.stripXSS(r2.getString(User.COL_FIRSTNAME)));
					user.setLastName(XssSanitizerUtil.stripXSS(r2.getString(User.COL_LASTNAME)));
					user.setStatus(UserStatus.getValue(r2.getString(User.COL_STATUS)));

					// if account has been brute-forced before, then delete instance in lockout
					input.clear();
					input.add(User.TABLE_LOCKOUT + "_" + id_number);
					q.runInsertUpdateDelete(query_delete, input);
					
					MyLogger.getInstance().info("User " + user.getIdnumber() + " logged in.");

				} else {

					// brute force attack if account is activated
					if(status == UserStatus.ACTIVATED) {
						// login attempt may be brute force
						isBruteForce = true;
					}
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(r != null)
					r.close();
				if(r2 != null)
					r2.close();
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if(isBruteForce) {
			addLoginAttempt(id_number);
		}

		return user;
	}

	// login attempt failed
	public static void addLoginAttempt(String idnumber) {

		String query_insert_update = "\nINSERT INTO " + User.TABLE_LOCKOUT + "\n"
				+ " SET " + User.COL_LOCKOUTID + " = ?, "
				+ User.COL_IDNUMBER + " = ?, "
				+ User.COL_NUMATTEMPTS + " = 1 "
				+ " ON DUPLICATE KEY UPDATE "
				+ User.COL_NUMATTEMPTS + " = " + User.COL_NUMATTEMPTS + " + 1;";

		String query_select = "\nSELECT " + User.COL_NUMATTEMPTS + "\n"
				+ " FROM " + User.TABLE_LOCKOUT 
				+ " WHERE " + User.COL_IDNUMBER + " = ?;";

		String query_update_status = "\nUPDATE " + User.TABLE_USER + "\n"
				+ "\tSET status = ?\n"
				+ "\tWHERE id_number = ? AND status = ?;";

		String query_delete = "\nDELETE FROM " + User.TABLE_LOCKOUT
				+ " WHERE " + User.COL_LOCKOUTID + " = ?;";

		ArrayList<Object> input = new ArrayList<>();
		input.add(User.TABLE_LOCKOUT + "_" + idnumber);
		input.add(idnumber);

		Query q = Query.getInstance();
		ResultSet r = null;

		try {

			// update num_attempts
			// insert if not exists
			q.runInsertUpdateDelete(query_insert_update, input);		

			input.clear();
			input.add(idnumber);

			// get current num_attempts
			r = q.runQuery(query_select, input);

			if(r.next()) {
				// if equal to 5, update status of user to LOCKOUT

				int num_attempts = Integer.parseInt(r.getString(User.COL_NUMATTEMPTS));

				if(num_attempts == 5) {
					input.clear();
					input.add(UserStatus.LOCKOUT + "");
					input.add(idnumber);
					input.add(UserStatus.ACTIVATED + "");

					q.runSQLEvent(query_update_status, input);

					// then delete instance in lockout table
					input.clear();
					input.add(User.TABLE_LOCKOUT + "_" + idnumber);

					q.runInsertUpdateDelete(query_delete, input);
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(r != null)
					r.close();
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static UserStatus getUserStatus(String idnumber) {
		UserStatus userStatus = null;

		String query = "\nSELECT " + User.COL_STATUS 
				+ " FROM " + User.TABLE_USER
				+ " WHERE " + User.COL_IDNUMBER + " = ?";

		ArrayList<Object> input = new ArrayList<>();
		input.add(idnumber);

		Query q = Query.getInstance();
		ResultSet r = null;

		try {
			r = q.runQuery(query, input);

			if(r.next()) {
				userStatus = UserStatus.getValue(r.getString(User.COL_STATUS));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return userStatus;
	}

	// view profile
	public static User viewProfileUser(String id_number) {
		User user = null;

		String query = "\nSELECT * FROM " + User.TABLE_USER
				//				+ " WHERE " + User.COL_IDNUMBER + " = ?";
				+ " WHERE SHA2(" + User.COL_IDNUMBER + ", 256) = ?";

		ArrayList<Object> input = new ArrayList<>();
		input.add(id_number);

		Query q = Query.getInstance();
		ResultSet r = null;

		try {
			r = q.runQuery(query, input);

			if(r.next()) {				
				user = new User();

				// id number
				user.setIdnumber(XssSanitizerUtil.stripXSS(r.getString(User.COL_IDNUMBER)));

				// user type
				user.setUserType(UserType.getValue(XssSanitizerUtil.stripXSS(r.getString(User.COL_USERTYPE))));

				// name
				user.setFirstName(XssSanitizerUtil.stripXSS(r.getString(User.COL_FIRSTNAME)));
				user.setMiddleInitial(XssSanitizerUtil.stripXSS(r.getString(User.COL_MI)));
				user.setLastName(XssSanitizerUtil.stripXSS(r.getString(User.COL_LASTNAME)));

				// email
				user.setEmail(XssSanitizerUtil.stripXSS(r.getString(User.COL_EMAIL)));

				// birthday
				user.setBirthdate(r.getDate(User.COL_BDAY));

				// status of account
				user.setStatus(UserStatus.getValue(r.getString(User.COL_STATUS)));

				// Secret Question answer
				user.setSecretAnswer(XssSanitizerUtil.stripXSS((r.getString(User.COL_SQANSWER))));	
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(r != null)
					r.close();
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return user;
	}

	// get user type
	public static UserType getUserType(String id_number) {
		UserType userType = null;

		String query =  "\nSELECT " + User.COL_USERTYPE
				+ " FROM " + User.TABLE_USER + "\n"
				+ " WHERE " + User.COL_IDNUMBER + " = ?;";

		ArrayList<Object> input = new ArrayList<>();
		input.add(id_number);

		Query q = Query.getInstance();
		ResultSet r = null;

		try {
			r = q.runQuery(query, input);

			if(r.next()) {
				userType = UserType.getValue(r.getString(User.COL_USERTYPE));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(r != null)
					r.close();
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return userType;
	}

	// change password of any user
	public static boolean changePassword(User user) {
		boolean result = false;

		String query = "\nUPDATE " + User.TABLE_USER
				+ " SET "
				+ User.COL_PASSWORD + " = SHA2(?, 512)\n"
				+ " WHERE " + User.COL_IDNUMBER + " = ?";

		ArrayList<Object> input = new ArrayList<>();
		input.add(user.getPassword());
		input.add(user.getIdnumber());

		Query q = Query.getInstance();
		try {
			result = q.runInsertUpdateDelete(query, input);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	// change password of libstaff/libmngr to activate account
	public static boolean changePasswordToActivateAccount(User user) {
		boolean result = false;

		String query = "\nUPDATE " + User.TABLE_USER
				+ " SET "
				+ User.COL_PASSWORD + " = SHA2(?, 512),"
				+ User.COL_STATUS + "  = ?\n"
				+ " WHERE " + User.COL_IDNUMBER + " = ?";

		ArrayList<Object> input = new ArrayList<>();
		input.add(user.getPassword());
		input.add(UserStatus.ACTIVATED);
		input.add(user.getIdnumber());

		Query q = Query.getInstance();
		try {
			result = q.runInsertUpdateDelete(query, input);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	// get all locked accounts
	public static ArrayList<User> getLockedAccounts() {
		ArrayList<User> lockedAccounts = new ArrayList<>();
		User user = null;
		Encoder encoder = ESAPI.encoder();

		String query = "\nSELECT * FROM " + User.TABLE_USER
				+ " WHERE " + User.COL_STATUS + " = ?";


		ArrayList<Object> input = new ArrayList<>();
		input.add(UserStatus.LOCKOUT + "");

		Query q = Query.getInstance();
		ResultSet r = null;

		try {
			r = q.runQuery(query, input);
			//r = q.runQuery(query);

			while(r.next()) {


				user = new User();
				user.setIdnumber(XssSanitizerUtil.stripXSS(r.getString(User.COL_IDNUMBER)));
				user.setFirstName(XssSanitizerUtil.stripXSS(r.getString(User.COL_FIRSTNAME)));
				user.setMiddleInitial(XssSanitizerUtil.stripXSS(r.getString(User.COL_MI)));
				user.setLastName(XssSanitizerUtil.stripXSS(r.getString(User.COL_LASTNAME)));
				user.setUserType(UserType.getValue(XssSanitizerUtil.stripXSS(r.getString(User.COL_USERTYPE))));
				user.setStatus(UserStatus.getValue(XssSanitizerUtil.stripXSS(r.getString(User.COL_STATUS))));
				lockedAccounts.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(r != null)
					r.close();
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return lockedAccounts;
	}

	// unlock account
	public static boolean unlockAccount(String idnumber) {
		boolean result = false;

		String query = "\nUPDATE " + User.TABLE_USER
				+ " SET "
				+ User.COL_STATUS + "  = ?\n"
				+ " WHERE " + User.COL_IDNUMBER + " = ?";

		ArrayList<Object> input = new ArrayList<>();
		input.add(UserStatus.ACTIVATED + "");
		input.add(idnumber);

		Query q = Query.getInstance();

		try {
			result = q.runInsertUpdateDelete(query, input);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
}