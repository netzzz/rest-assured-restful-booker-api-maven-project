package pojo.responses;

public class AuthenticationResponseBody {
	private String token;
	
	public AuthenticationResponseBody() {
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getToken() {
		return this.token;
	}
}
