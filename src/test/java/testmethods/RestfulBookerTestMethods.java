package testmethods;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.Assert;

import data.HttpResponseStatusCodes;
import data.RestfulBookerData;
import helperfunctions.HelperFunctions;

public class RestfulBookerTestMethods {
	private static final Logger logger = LogManager.getLogger(RestfulBookerTestMethods.class.getName());
	private static String baseURI = "https://restful-booker.herokuapp.com";

	// --------------------------------
	// Authentication Methods

	public static Response sendAuthenticationRequest(String username, String password) {
		RestAssured.baseURI = baseURI;

		return given().header("Content-Type", "application/json")
				.body(RestfulBookerData.authenticationRequestBody(username, password)).when().post("auth").then()
				.extract().response();
	}

	public static void validateAuthenticationResponse(Response authentication) {
		Assert.assertEquals(authentication.getStatusCode(), HttpResponseStatusCodes.OK.getCode(), String
				.format("Authentication Response Status Code is %d as Not Expected", authentication.getStatusCode()));
		logger.info(String.format("Authentication Response Status Code is %d as Expected",
				HttpResponseStatusCodes.OK.getCode()));
	}

	public static String getAuthenticationToken(Response authentication) {
		JsonPath authenticationResponseJson = HelperFunctions.convertRestAssuredResponseToJson(authentication);
		return authenticationResponseJson.getString("token");
	}

	// --------------------------------
	// Create Booking Methods

	public static Response createBooking(String firstName, String lastName, double totalPrice, boolean isDepositPaid,
			String checkIn, String checkOut, String additionalNeeds) {
		RestAssured.baseURI = baseURI;

		return given().header("Content-Type", "application/json")
				.body(RestfulBookerData.createUpdateBookingRequestBody(firstName, lastName, totalPrice, isDepositPaid,
						checkIn, checkOut, additionalNeeds))
				.when().post("booking").then().extract().response();
	}

	public static void validateCreateBooking(Response createBooking) {
		Assert.assertEquals(createBooking.getStatusCode(), HttpResponseStatusCodes.OK.getCode(), String
				.format("Create Booking Response Status Code is '%d' as Not Expected", createBooking.getStatusCode()));
		logger.info(String.format("Create Booking Response Status Code is '%d' as Expected",
				HttpResponseStatusCodes.OK.getCode()));
	}

	public static int getCreatedBookingId(Response createBooking) {
		JsonPath createBookingResponseJson = HelperFunctions.convertRestAssuredResponseToJson(createBooking);
		return createBookingResponseJson.getInt("bookingid");
	}

	// --------------------------------
	// Get Booking by Id Methods

	public static Response getBookingById(int bookingId) {
		return given().pathParam("id", bookingId).when().get("booking/{id}").then().extract().response();
	}

	public static void validateGetBookingById(Response getBookingById, String firstName, String lastName) {
		Assert.assertEquals(getBookingById.getStatusCode(), HttpResponseStatusCodes.OK.getCode(), String.format(
				"Get Booking By Id Response Status Code is '%d' as Not Expected", getBookingById.getStatusCode()));
		logger.info(String.format("Get Booking By Id Response Status Code is '%d' as Expected",
				HttpResponseStatusCodes.OK.getCode()));

		JsonPath getBookingByIdJson = HelperFunctions.convertRestAssuredResponseToJson(getBookingById);

		String firstNameFromRetrievedBooking = getBookingByIdJson.getString("firstname");
		Assert.assertEquals(firstNameFromRetrievedBooking, firstName, String.format(
				"'firstname' in Booking retrieved By Id is '%s' as Not Expected", firstNameFromRetrievedBooking));
		logger.info(String.format("'firstname' in Booking retrieved By Id is '%s' as Expected", firstName));

		String lastNameFromRetrievedBooking = getBookingByIdJson.getString("lastname");
		Assert.assertEquals(lastNameFromRetrievedBooking, lastName, String
				.format("'lastname' in Booking retrieved By Id is '%s' as Not Expected", lastNameFromRetrievedBooking));
		logger.info(String.format("'lastname' in Booking retrieved By Id is '%s' as Expected", lastName));
	}

	// --------------------------------
	// Update Booking using Put Methods

	public static Response updateBookingUsingPut(int bookingId, String authenticationToken, String firstName,
			String lastName, double totalPrice, boolean isDepositPaid, String checkIn, String checkOut,
			String additionalNeeds) {
		return given().pathParam("id", bookingId).header("Content-Type", "application/json")
				.header("Cookie", "token=" + authenticationToken)
				.body(RestfulBookerData.createUpdateBookingRequestBody(firstName, lastName, totalPrice, isDepositPaid,
						checkIn, checkOut, additionalNeeds))
				.when().put("booking/{id}").then().extract().response();
	}

	public static void validateUpdateBookingUsingPut(Response updateBookingUsingPutResponse) {
		Assert.assertEquals(updateBookingUsingPutResponse.getStatusCode(), HttpResponseStatusCodes.OK.getCode(),
				String.format("Update Booking Using Put Status Code is '%d' as Not Expected",
						updateBookingUsingPutResponse.getStatusCode()));
		logger.info(String.format("Update Booking Using Put Status Code is '%d' as Expected",
				HttpResponseStatusCodes.OK.getCode()));
	}

	// --------------------------------
	// Partially Update Booking using Patch Methods

	public static Response partiallyUpdateBookingUsingPatch(int bookingId, String authenticationToken, String firstName,
			String lastName) {
		return given().pathParam("id", bookingId).header("Content-Type", "application/json")
				.header("Cookie", "token=" + authenticationToken)
				.body(RestfulBookerData.partialUpdateBookingRequestBody(firstName, lastName)).when()
				.patch("booking/{id}").then().extract().response();
	}

	public static void validatePartiallyUpdateBookingUsingPatch(Response partiallyUpdateBookingUsingPatchResponse) {
		Assert.assertEquals(partiallyUpdateBookingUsingPatchResponse.getStatusCode(),
				HttpResponseStatusCodes.OK.getCode(),
				String.format("Partially Update Booking Using Patch Status Code is '%d' as Not Expected",
						partiallyUpdateBookingUsingPatchResponse.getStatusCode()));
		logger.info(String.format("Partially Update Booking Using Patch Status Code is '%d' as Expected",
				HttpResponseStatusCodes.OK.getCode()));
	}

	// --------------------------------
	// Delete Booking Methods

	public static Response deleteBooking(int bookingId, String authenticationToken) {
		return given().pathParam("id", bookingId).header("Content-Type", "application/json")
				.header("Cookie", "token=" + authenticationToken).when().delete("booking/{id}").then().extract()
				.response();
	}

	public static void validateDeleteBooking(Response deleteBooking) {
		Assert.assertEquals(deleteBooking.getStatusCode(), HttpResponseStatusCodes.CREATED.getCode(), String
				.format("Delete Booking Response Status Code is '%d' as Not Expected", deleteBooking.getStatusCode()));
		logger.info(String.format("Delete Booking Response Status Code is '%d' as Expected",
				HttpResponseStatusCodes.CREATED.getCode()));
	}

	public static void validateGetDeletedBooking(Response getDeletedBooking) {
		Assert.assertEquals(getDeletedBooking.getStatusCode(), HttpResponseStatusCodes.NOTFOUND.getCode(),
				String.format("Get Deleted Booking Response Status Code is '%d' as Not Expected",
						getDeletedBooking.getStatusCode()));
		logger.info(String.format("Get Deleted Booking Response Status Code is '%d' as Expected",
				HttpResponseStatusCodes.NOTFOUND.getCode()));
	}
}
