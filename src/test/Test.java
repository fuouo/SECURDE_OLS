package test;

import utils.Utils;

public class Test {
	
	public static void main(String[] args) {
		String idnumber = "11427493";
		
		String encrypted = Utils.get_SHA_256_SecureString(idnumber, "");
		
		System.out.println(encrypted);
	}

}
