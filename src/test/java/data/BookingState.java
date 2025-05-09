package data;

public enum BookingState {
	CREATED("Created"), 
	UPDATED("Updated"), 
	PARTIALLY_UPDATED("Partially Updated");

	private final String state;

	BookingState(String state) {
		this.state = state;
	}

	public String getState() {
		return this.state;
	}
}
