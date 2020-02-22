package co.simplon.masterpiece.dtos;

import javax.validation.constraints.Pattern;

public class UserDto {

	@Pattern(regexp = "[a-zA-Z][0-9]{6}", message = "Please enter valid SESAME id. Ex.:a001122")
	private String sesameId;

	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{5,}$", message = "Password must be at least 5 caracters long, contain upper and lower cases and at least one digit.")
	private String pwd;

	public UserDto(String sesameId, String pwd) {
		this.sesameId = sesameId;
		this.pwd = pwd;
	}

	public String getSesameId() {
		return sesameId;
	}

	public void setSesameId(String sesameId) {
		this.sesameId = sesameId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
