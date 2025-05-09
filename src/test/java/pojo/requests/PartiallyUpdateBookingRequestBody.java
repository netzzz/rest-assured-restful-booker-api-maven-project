package pojo.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import data.FirstNameLastName;

public class PartiallyUpdateBookingRequestBody {
	@JsonProperty("firstname")
	private String firstName;

	@JsonProperty("lastname")
	private String lastName;

	public PartiallyUpdateBookingRequestBody() {
	}

	public PartiallyUpdateBookingRequestBody(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public PartiallyUpdateBookingRequestBody(FirstNameLastName firstNameLastName) {
		this.firstName = firstNameLastName.getFirstName();
		this.lastName = firstNameLastName.getLastName();
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

	@Override
	public String toString() {
		return "{\n\t\"firstname\":\"" + firstName + "\",\n\t\"lastname\":\"" + lastName + "\"\n}";
	}
}
