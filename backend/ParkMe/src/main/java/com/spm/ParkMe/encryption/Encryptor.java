package com.spm.ParkMe.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryptor {

	public static String encryptPassword(String password) {
		try {
			byte[] digest = digest(password);
			StringBuilder sb = new StringBuilder();
	        for(int i=0; i< digest.length ;i++)
	        {
	            sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        String generatedPassword = sb.toString();
			return (generatedPassword);
		}
		catch(NoSuchAlgorithmException e){
			System.out.println(e);
			return null;
		}
	}
	
	static byte[] digest(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		return md.digest();
	}
}
