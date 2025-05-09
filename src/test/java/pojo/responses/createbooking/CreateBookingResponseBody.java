package pojo.responses.createbooking;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateBookingResponseBody {
	@JsonProperty("bookingid")
	int bookingId;
	
	@JsonProperty("booking")
	BookingInCreateBookingResponseBody booking;
	
	public CreateBookingResponseBody(){
		
	}
	
	public CreateBookingResponseBody(int bookingId, BookingInCreateBookingResponseBody booking) {
		this.bookingId = bookingId;
		this.booking = booking;
	}
	
	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public BookingInCreateBookingResponseBody getBooking() {
		return booking;
	}

	public void setBooking(BookingInCreateBookingResponseBody booking) {
		this.booking = booking;
	}

	@Override
	public String toString() {
		return "{\n\t\"bookingid\":"+this.bookingId+",\n\t\"booking\":"+this.booking.toString();
	}
}
