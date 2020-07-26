package co.simplon.masterpiece.dtos;

import javax.validation.constraints.Pattern;

public class UserDto {

// disabled in dev
//	@Pattern(regexp = "[a-zA-Z][0-9]{6}")
	private String login;

	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{5,}$")
	private String password;

	public UserDto(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPwd(String password) {
		this.password = password;
	}

}
