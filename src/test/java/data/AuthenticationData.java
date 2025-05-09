package data;

import helperfunctions.EncryptionUtil;

public class AuthenticationData {
	private String username;
	private String password;
	
	public AuthenticationData(String username, String encryptedPassword) throws Exception {
		this.username = username;
		this.password = EncryptionUtil.decrypt(encryptedPassword);
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
