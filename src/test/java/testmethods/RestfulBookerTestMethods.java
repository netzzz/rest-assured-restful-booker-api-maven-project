package testmethods;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import pojo.requests.AuthenticationRequestBody;
import pojo.requests.PartiallyUpdateBookingRequestBody;
import pojo.requests.createupdatebooking.CreateBookingRequestBody;
import pojo.responses.AuthenticationResponseBody;
import pojo.responses.createbooking.BookingInCreateBookingResponseBody;
import pojo.responses.createbooking.CreateBookingResponseBody;

import org.testng.Assert;

import data.BookingState;
import data.FirstNameLastName;
import data.HttpResponseStatusCodes;
import helperfunctions.HelperFunctions;


public class RestfulBookerTestMethods {
	private static String baseURI = "https://restful-booker.herokuapp.com";

	public static Response sendAuthenticationRequest(AuthenticationRequestBody authenticationRequestBody) {
		RestAssured.baseURI = baseURI;
		
		return given().header("Content-Type", "application/json")
				.body(authenticationRequestBody).when().post("auth").then()
				.extract().response();
	}
	
	public static String getAuthenticationTokenFromResponse(Response authentication) {
		AuthenticationResponseBody authenticationResponseBody = authentication.as(AuthenticationResponseBody.class);
		return authenticationResponseBody.getToken();
	}

	public static Response createBooking(CreateBookingRequestBody createBookingRequestBody) {
		RestAssured.baseURI = baseURI;
		
		return given().header("Content-Type", "application/json")
				.body(createBookingRequestBody)
				.when().post("booking").then().extract().response();
	}

	public static int getIdOfCreatedBooking(Response createBooking) {
		CreateBookingResponseBody createBookingResponseBody = createBooking.as(CreateBookingResponseBody.class);
		return createBookingResponseBody.getBookingId();
	}


	public static Response getBookingById(int bookingId) {
		return given().pathParam("id", bookingId).when().get("booking/{id}").then().extract().response();
	}


	public static Response updateBooking(int bookingId, String authenticationToken, 
			CreateBookingRequestBody updateBookingRequestBody) {
		
		return given().pathParam("id", bookingId).header("Content-Type", "application/json")
				.header("Cookie", "token=" + authenticationToken)
				.body(updateBookingRequestBody)
				.when().put("booking/{id}").then().extract().response();
	}


	public static Response partiallyUpdateBooking(int bookingId, String authenticationToken, 
			PartiallyUpdateBookingRequestBody partialUpdateBookingRequestBody) {
		
		return given().pathParam("id", bookingId).header("Content-Type", "application/json")
				.header("Cookie", "token=" + authenticationToken)
				.body(partialUpdateBookingRequestBody).when()
				.patch("booking/{id}").then().extract().response();
	}


	public static Response deleteBooking(int bookingId, String authenticationToken) {
		return given().pathParam("id", bookingId).header("Content-Type", "application/json")
				.header("Cookie", "token=" + authenticationToken).when().delete("booking/{id}").then().extract()
				.response();
	}


	public static void validateStatusCodeOfResponse(String requestName, Response response, 
			HttpResponseStatusCodes expectedResponseStatusCode) {
		Assert.assertEquals(response.getStatusCode(), expectedResponseStatusCode.getCode(),
				String.format("Status Code for %s Request is %d as Not Expected", requestName, response.getStatusCode()));
		HelperFunctions.logToReportAndLog4j(String.format("Status Code for %s Request is %d as Expected", requestName,
				expectedResponseStatusCode.getCode()));
	}
	
	public static void validateFirstNameAndLastNameForBooking(Response getBookingResponse, BookingState bookingState, 
			FirstNameLastName firstNameLastName) {
		BookingInCreateBookingResponseBody booking = getBookingResponse.as(BookingInCreateBookingResponseBody.class);
		String firstName = firstNameLastName.getFirstName();
		String lastName = firstNameLastName.getLastName();
		
		String firstNameFromRetrievedBooking = booking.getFirstName();
		Assert.assertEquals(firstNameFromRetrievedBooking, firstName, String.format(
				"'firstname' in %s Booking is '%s' as Not Expected", bookingState.getState(), firstNameFromRetrievedBooking));
		HelperFunctions.logToReportAndLog4j(String.format("'firstname' in %s Booking is '%s' as Expected", 
				bookingState.getState(), firstName));

		String lastNameFromRetrievedBooking = booking.getLastName();
		Assert.assertEquals(lastNameFromRetrievedBooking, lastName, 
				String.format("'lastname' in %s Booking is '%s' as Not Expected", bookingState.getState(), 
						lastNameFromRetrievedBooking));
		HelperFunctions.logToReportAndLog4j(String.format("'lastname' in %s Booking is '%s' as Expected", 
				bookingState.getState(), lastName));
	}
}
