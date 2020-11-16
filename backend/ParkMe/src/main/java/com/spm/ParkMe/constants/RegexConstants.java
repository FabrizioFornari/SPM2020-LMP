package com.spm.ParkMe.constants;

public final class RegexConstants {
	public static final String MAIL_REGEX = "^[0-9a-zA-Z]+([0-9a-zA-Z]*[-._+])*[0-9a-zA-Z]+@[0-9a-zA-Z]+([-.][0-9a-zA-Z]+)*([0-9a-zA-Z]*[.])[a-zA-Z]{2,6}$";
	public static final String PHONE_REGEX = "^((00|\\+)39[\\. ]??)??3\\d{2}[\\. ]??\\d{6,7}$";
	public static final String SSN_REGEX = "^([A-Za-z]{6}[0-9lmnpqrstuvLMNPQRSTUV]{2}[abcdehlmprstABCDEHLMPRST]{1}[0-9lmnpqrstuvLMNPQRSTUV]{2}[A-Za-z]{1}[0-9lmnpqrstuvLMNPQRSTUV]{3}[A-Za-z]{1})|([0-9]{11})$";
	public static final String PLATE_REGEX = "[A-Za-z]{2}[0-9]{3}[A-Za-z]{2}";
}
