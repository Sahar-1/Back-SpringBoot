package Esprit.PiDev.Entity.RequestApiForm;

import javax.validation.constraints.NotBlank;

public class RequestLogin {
	 
	private String email;

	@NotBlank
	private String password;

 

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}