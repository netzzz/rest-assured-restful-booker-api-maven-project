package pojo.requests.createupdatebooking;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingDatesInCreateBookingRequestBody {
	@JsonProperty("checkin")
	private String checkIn;
	
	@JsonProperty("checkout")
	private String checkOut;
	
	public BookingDatesInCreateBookingRequestBody(){
	}
	
	public BookingDatesInCreateBookingRequestBody(String checkIn, String checkOut) {
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
		return "\t\"bookingdates\":{\n\t\t\"checkin\":\"" + this.getCheckIn() + 
				"\",\n\t\t\"checkout\":\"" + this.getCheckOut() + "\"\n\t}";
	}
}
