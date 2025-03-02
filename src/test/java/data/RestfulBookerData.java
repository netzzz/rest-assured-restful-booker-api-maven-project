package data;

public class RestfulBookerData {
	public static String authenticationRequestBody(String username, String password) {
		return "{\r\n"
				+ "    \"username\" : \""+username+"\",\r\n"
				+ "    \"password\" : \""+password+"\"\r\n"
				+ "}";
	}
	
	public static String createUpdateBookingRequestBody(String firstName, String lastName, double totalPrice, 
			boolean isDepositPaid, String checkIn, String checkOut, String additionalNeeds) {
		return "{\r\n"
				+ "    \"firstname\" : \""+firstName+"\",\r\n"
				+ "    \"lastname\" : \""+lastName+"\",\r\n"
				+ "    \"totalprice\" : "+totalPrice+",\r\n"
				+ "    \"depositpaid\" : "+isDepositPaid+",\r\n"
				+ "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \""+checkIn+"\",\r\n"
				+ "        \"checkout\" : \""+checkOut+"\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\" : \""+additionalNeeds+"\"\r\n"
				+ "}";
	}
	
	
	public static String partialUpdateBookingRequestBody(String firstName, String lastName) {
		return "{\r\n"
				+ "    \"firstname\" : \""+firstName+"\",\r\n"
				+ "    \"lastname\" : \""+lastName+"\"\r\n"
				+ "}";
	}
}
