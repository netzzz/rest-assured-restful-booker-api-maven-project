package tests;

import io.restassured.response.Response;
import pojo.requests.AuthenticationRequestBody;
import pojo.requests.PartiallyUpdateBookingRequestBody;
import pojo.requests.createupdatebooking.BookingDatesInCreateBookingRequestBody;
import pojo.requests.createupdatebooking.CreateBookingRequestBody;

import org.testng.annotations.Test;

import data.AuthenticationData;
import data.BookingState;
import data.FirstNameLastName;
import data.HttpResponseStatusCodes;
import testmethods.RestfulBookerTestMethods;


public class RestfulBookerTests {
	@Test
	public void restfulBookerEndToEndValidation() throws Exception{
		AuthenticationData authenticationData = new AuthenticationData("admin", "5AJY0WtOurmyT0rFD61A7w==");
		AuthenticationRequestBody authenticationRequestBody = new AuthenticationRequestBody(authenticationData);
		Response authentication = RestfulBookerTestMethods.sendAuthenticationRequest(authenticationRequestBody);
		RestfulBookerTestMethods.validateStatusCodeOfResponse("Authentication", authentication, 
				HttpResponseStatusCodes.OK);
		String authenticationToken = RestfulBookerTestMethods.getAuthenticationTokenFromResponse(authentication);

		FirstNameLastName firstNameAndLastNameInCreatedBooking = new FirstNameLastName("Johnsy","Johnson");
		BookingDatesInCreateBookingRequestBody bookingDates = new BookingDatesInCreateBookingRequestBody("2018-01-01", "2018-01-05");
		CreateBookingRequestBody createBookingRequestBody = new CreateBookingRequestBody(firstNameAndLastNameInCreatedBooking, 
				202.5, true, bookingDates, "Breakfast");
		Response createBooking = RestfulBookerTestMethods.createBooking(createBookingRequestBody);
		RestfulBookerTestMethods.validateStatusCodeOfResponse("Create Booking", createBooking, 
				HttpResponseStatusCodes.OK);
		int createdBookingId = RestfulBookerTestMethods.getIdOfCreatedBooking(createBooking);

		Response getBookingById = RestfulBookerTestMethods.getBookingById(createdBookingId);
		RestfulBookerTestMethods.validateStatusCodeOfResponse("Get Booking by Id", getBookingById, 
				HttpResponseStatusCodes.OK);
		RestfulBookerTestMethods.validateFirstNameAndLastNameForBooking(getBookingById, BookingState.CREATED, 
				firstNameAndLastNameInCreatedBooking);

		FirstNameLastName firstNameAndLastNameInUpdatedBooking = new FirstNameLastName("Nick","Milsen");
		CreateBookingRequestBody updateBookingRequestBody = new CreateBookingRequestBody(firstNameAndLastNameInUpdatedBooking, 
				202.5, true, bookingDates, "Breakfast");
		Response updateBookingUsingPut = RestfulBookerTestMethods.updateBooking(createdBookingId,
				authenticationToken, updateBookingRequestBody);
		RestfulBookerTestMethods.validateStatusCodeOfResponse("Update Booking Using Put", updateBookingUsingPut, 
				HttpResponseStatusCodes.OK);

		Response getBookingByIdAfterUpdate = RestfulBookerTestMethods.getBookingById(createdBookingId);
		RestfulBookerTestMethods.validateStatusCodeOfResponse("Get Updated Booking by Id", getBookingByIdAfterUpdate, 
				HttpResponseStatusCodes.OK);
		RestfulBookerTestMethods.validateFirstNameAndLastNameForBooking(getBookingByIdAfterUpdate, BookingState.UPDATED,
				firstNameAndLastNameInUpdatedBooking);

		FirstNameLastName firstNameLastNameInPartiallyUpdatedBooking = new FirstNameLastName("Mike","Perez");
		PartiallyUpdateBookingRequestBody partiallyUpdateBookingRequestBody = 
				new PartiallyUpdateBookingRequestBody(firstNameLastNameInPartiallyUpdatedBooking);
		Response partiallyUpdateBookingUsingPatch = RestfulBookerTestMethods
				.partiallyUpdateBooking(createdBookingId, authenticationToken, 
						partiallyUpdateBookingRequestBody);
		RestfulBookerTestMethods.validateStatusCodeOfResponse("Partially Update Booking Using Patch", 
				partiallyUpdateBookingUsingPatch, HttpResponseStatusCodes.OK);

		Response getBookingByIdAfterPartialUpdate = RestfulBookerTestMethods.getBookingById(createdBookingId);
		RestfulBookerTestMethods.validateStatusCodeOfResponse("Get Partially Updated Booking by Id", 
				getBookingByIdAfterPartialUpdate, HttpResponseStatusCodes.OK);
		RestfulBookerTestMethods.validateFirstNameAndLastNameForBooking(getBookingByIdAfterPartialUpdate, 
				BookingState.PARTIALLY_UPDATED, firstNameLastNameInPartiallyUpdatedBooking);

		Response deleteBooking = RestfulBookerTestMethods.deleteBooking(createdBookingId, authenticationToken);
		RestfulBookerTestMethods.validateStatusCodeOfResponse("Delete Booking", deleteBooking, 
				HttpResponseStatusCodes.CREATED);

		Response getBookingByAfterDelete = RestfulBookerTestMethods.getBookingById(createdBookingId);
		RestfulBookerTestMethods.validateStatusCodeOfResponse("Get Deleted Booking", getBookingByAfterDelete, 
				HttpResponseStatusCodes.NOTFOUND);
	}
}
