package pojo.responses.createbooking;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingDatesInCreateBookingResponseBody {
	@JsonProperty("checkin")
	private String checkIn;
	
	@JsonProperty("checkout")
	private String checkOut;
	
	public BookingDatesInCreateBookingResponseBody(){
	}
	
	public BookingDatesInCreateBookingResponseBody(String checkIn, String checkOut) {
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	
	public String getCheckIn() {
		return checkIn;
	}
	
	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}
	
	public String getCheckOut() {
		return checkOut;
	}
	
	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}
	
	@Override
	public String toString() {
		return "\"bookingdates\":{\n\t\"checkin\":\"" + this.getCheckIn() + 
				"\",\n\t\"checkout\":\"" + this.getCheckOut() + "\"\n\t}";
	}
}
