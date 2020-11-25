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
	public static final String ADMIN_GET_ALL_HANDICAP_PERMITS_ENDPOINT= "/handicapPermits/all";
	public static final String ADMIN_GET_NOT_PROCESSED_HANDICAP_PERMITS_ENDPOINT= "/handicapPermits/notProcessed";
	public static final String ADMIN_GET_PROCESSED_HANDICAP_PERMITS_ENDPOINT= "/handicapPermits/processed";
	public static final String ADMIN_SET_HANDICAP_PERMITS_ENDPOINT= "/setting/handicapPermits";
	
	//Parking Manager Lot controller
	public static final String PARKING_MANAGER_ENDPOINT="/api/parkingManager";
	public static final String PARKING_MANAGER_CREATE_PARKINGLOT_ENDPOINT="/parkingLot/create";
	public static final String PARKING_MANAGER_DELETE_PARKINGLOT_ENDPOINT="/parkingLot/delete";
	public static final String PARKING_MANAGER_GET_ALL_PARKINGLOT_ENDPOINT="/parkingLots/all";
	public static final String PARKING_MANAER_GET_ALL_PARKINGLOTS_STREET_ENDPOINT=  "/parkingLots/getStreet";
	public static final String PARKING_MANAGER_UPDATE_PARKINGLOT_ENDPOINT= "/parkingLot/update";
}
