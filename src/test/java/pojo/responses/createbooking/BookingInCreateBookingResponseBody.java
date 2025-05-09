package pojo.responses.createbooking;

import com.fasterxml.jackson.annotation.JsonProperty;

import pojo.requests.createupdatebooking.BookingDatesInCreateBookingRequestBody;

public class BookingInCreateBookingResponseBody {
	@JsonProperty("firstname")
	private String firstName;
	
	@JsonProperty("lastname")
	private String lastName;
	
	@JsonProperty("totalprice")
	private double totalPrice; 
	
	@JsonProperty("depositpaid")
	private boolean isDepositPaid; 
	
	@JsonProperty("bookingdates")
	private BookingDatesInCreateBookingRequestBody bookingDates;
	
	@JsonProperty("additionalneeds")
	private String additionalNeeds;
	
	public BookingInCreateBookingResponseBody() {
	}
	
	public BookingInCreateBookingResponseBody(String firstName, String lastName, double totalPrice, boolean isDepositPaid,
			BookingDatesInCreateBookingRequestBody bookingDates, String additionalNeeds) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.totalPrice = totalPrice;
		this.isDepositPaid = isDepositPaid;
		this.bookingDates = bookingDates;
		this.additionalNeeds = additionalNeeds;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public boolean isDepositPaid() {
		return isDepositPaid;
	}
	
	public void setDepositPaid(boolean isDepositPaid) {
		this.isDepositPaid = isDepositPaid;
	}
	
	public BookingDatesInCreateBookingRequestBody getBookingDates() {
		return bookingDates;
	}
	
	public void setBookingDates(BookingDatesInCreateBookingRequestBody bookingDates) {
		this.bookingDates = bookingDates;
	}
	
	public String getAdditionalNeeds() {
		return additionalNeeds;
	}
	
	public void setAdditionalNeeds(String additionalNeeds) {
		this.additionalNeeds = additionalNeeds;
	}
	
	@Override
	public String toString() {
		return "{\n\t\"firstname\":\""+firstName+"\",\n\t\"lastname\":\""+lastName+
				"\",\n\t\"totalprice\":"+totalPrice+",\n\t\"depositpaid\":"+isDepositPaid+",\n"
				+ this.bookingDates.toString()+",\n\t\"additionalneeds\":\""+additionalNeeds+"\"\n}";
	}
}
