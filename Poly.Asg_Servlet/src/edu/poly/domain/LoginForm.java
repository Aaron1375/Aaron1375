package edu.poly.domain;

public class LoginForm {
	private String username, password;
	private boolean remember;
	
	
	
	public LoginForm() {
		
	}

	public LoginForm(String usernane, String password, boolean remember) {
		this.username = username;
		this.password = password;
		this.remember = remember;
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String usernane) {
		this.username = usernane;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isRemember() {
		return remember;
	}
	public void setRemember(boolean remember) {
		this.remember = remember;
	}

	
}
