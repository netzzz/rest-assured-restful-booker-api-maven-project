package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import testmethods.RestfulBookerTestMethods;

public class RestfulBookerTests {

	@Test
	public void restfulBookerValidation() {
		Response authentication = RestfulBookerTestMethods.sendAuthenticationRequest("admin", "password123");
		RestfulBookerTestMethods.validateAuthenticationResponse(authentication);
		String authenticationToken = RestfulBookerTestMethods.getAuthenticationToken(authentication);

		Response createBooking = RestfulBookerTestMethods.createBooking("Johnsy", "Johnson", 202.5, 
				true, "2018-01-01", "2018-01-05", "Breakfast");
		RestfulBookerTestMethods.validateCreateBooking(createBooking);
		int createdBookingId = RestfulBookerTestMethods.getCreatedBookingId(createBooking);

		Response getBookingById = RestfulBookerTestMethods.getBookingById(createdBookingId);
		RestfulBookerTestMethods.validateGetBookingById(getBookingById, "Johnsy", "Johnson");

		Response updateBookingUsingPut = RestfulBookerTestMethods.updateBookingUsingPut(createdBookingId,
				authenticationToken, "Nick", "Milsen", 210.5, false, "2018-01-02", "2018-01-06", "Lunch");
		RestfulBookerTestMethods.validateUpdateBookingUsingPut(updateBookingUsingPut);

		Response getBookingByIdAfterUpdate = RestfulBookerTestMethods.getBookingById(createdBookingId);
		RestfulBookerTestMethods.validateGetBookingById(getBookingByIdAfterUpdate, "Nick", "Milsen");

		Response partiallyUpdateBookingUsingPatch = RestfulBookerTestMethods
				.partiallyUpdateBookingUsingPatch(createdBookingId, authenticationToken, "Peter", "Perez");
		RestfulBookerTestMethods.validatePartiallyUpdateBookingUsingPatch(partiallyUpdateBookingUsingPatch);

		Response getBookingByIdAfterPartialUpdate = RestfulBookerTestMethods.getBookingById(createdBookingId);
		RestfulBookerTestMethods.validateGetBookingById(getBookingByIdAfterPartialUpdate, "Peter", "Perez");

		Response deleteBooking = RestfulBookerTestMethods.deleteBooking(createdBookingId, authenticationToken);
		RestfulBookerTestMethods.validateDeleteBooking(deleteBooking);

		Response getBookingByAfterDelete = RestfulBookerTestMethods.getBookingById(createdBookingId);
		RestfulBookerTestMethods.validateGetDeletedBooking(getBookingByAfterDelete);
	}
}
