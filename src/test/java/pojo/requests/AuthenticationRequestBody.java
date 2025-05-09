package pojo.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import data.AuthenticationData;

public class AuthenticationRequestBody {
	@JsonProperty("username")
	private String username;
	
	@JsonProperty("password")
	private String password;
	
	public AuthenticationRequestBody() {
	}
	
	public AuthenticationRequestBody(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public AuthenticationRequestBody(AuthenticationData authenticationData) {
		this.username = authenticationData.getUsername();
		this.password = authenticationData.getPassword();
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString(){
		return "{\n\t\"username\":\""+this.username+"\",\n\t\"password\":\""+this.password+"\n}"; 
	}
}
