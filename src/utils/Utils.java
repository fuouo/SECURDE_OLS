package utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {

	public static Date addDays(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days);
		return c.getTime();
	}

	public static Date addMonth(Date date, int months) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, months);
		return c.getTime();
	}

	public static Date subtractMonth(Date date, int months) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, -months);
		return c.getTime();
	}

	public static String convertDateJavaToStringDB(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int day = c.get(Calendar.DAY_OF_MONTH);

		return year + "-" + ((month < 10) ? "0" + month : month) + "-" + ((day < 10) ? "0" + day : day);
	}
	
	public static String dateToReadableString(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		String month = c.getDisplayName(Calendar.MONTH, Calendar.LONG_FORMAT, Locale.getDefault());
		return month + " " + c.get(Calendar.DAY_OF_MONTH) + ", " + c.get(Calendar.YEAR);
	}

	public static Date convertStringToDate(String strDate) {

		String[] splitDate = strDate.split("-");
		int year = Integer.parseInt(splitDate[0]);
		int month = Integer.parseInt(splitDate[1])-1;
		int day = Integer.parseInt(splitDate[2]);

		Calendar c = Calendar.getInstance();
		c.set(year, month, day);

		return c.getTime();
	}

	public static int convertCurrTimetoInteger() {
		Calendar c = Calendar.getInstance();

		int h = c.get(Calendar.HOUR_OF_DAY) * 100;
		int m = c.get(Calendar.MINUTE);

		return h + m;
	}

	public static int[] getTimeSlots() {
		int[] timeSlots = new int[27];
		int start = 700;
		timeSlots[0] = start;

		for(int i = 1; i < timeSlots.length; i ++) {
			timeSlots[i] = start + ((i % 2 == 0) ? 70 : 30);
			start = timeSlots[i];
		}

		return timeSlots;

	}

	public static String get_SHA_256_SecureString(String stringToHash, String salt){
		String generatedString = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(salt.getBytes("UTF-8"));
			byte[] bytes = md.digest(stringToHash.getBytes("UTF-8"));
			StringBuilder sb = new StringBuilder();
			for(int i=0; i< bytes.length ;i++){
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedString = sb.toString();
		} 
		catch (NoSuchAlgorithmException | UnsupportedEncodingException e){
			e.printStackTrace();
		}
		return generatedString;
	}

}
