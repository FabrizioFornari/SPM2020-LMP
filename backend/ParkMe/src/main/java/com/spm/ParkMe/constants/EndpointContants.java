package com.spm.ParkMe.constants;

public final class EndpointContants {
	
	//authentication controller
	public static final String AUTH_ENDPOINT = "/api/auth";
	public static final String LOGIN_ENDPOINT = "/login";
	
	//driver controller
	public static final String DRIVER_ENDPOINT = "/api/driver";
	public static final String DRIVER_REGISTRATION_ENDPOINT = "/registration";
	public static final String DRIVER_HANDICAP_PERMITS_ENDPOINT = "/requestHandicapPermits";
	
	//modification controller
	public static final String MODIFICATION_ENDPOINT = "/api/modification";
	public static final String EMAIL_MODIFICATION_ENDPOINT = "/email";
	public static final String PASSWORD_MODIFICATION_ENDPOINT = "/password";
	public static final String PHONE_MODIFICATION_ENDPOINT = "/phone";
	public static final String PLATE_VEHICLE_MODIFICATION_ENDPOINT = "/plateAndVehicleType";
	
	//admin controller
	public static final String ADMIN_ENDPOINT = "/api/admin";
	public static final String VIGILANT_REGISTRATION_ENDPOINT = "/registration/vigilant";
	public static final String PARKING_MANAGER_REGISTRATION_ENDPOINT = "/registration/parkingManager";
	public static final String ADMIN_GET_HANDICAP_PERMITS_ENDPOINT= "/handicapPermits";
}
