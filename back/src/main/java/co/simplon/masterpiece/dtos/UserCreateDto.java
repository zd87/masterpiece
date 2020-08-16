package co.simplon.masterpiece.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserCreateDto {

	@NotBlank
	private String firstname;

	@NotBlank
	private String lastname;

// disabled in dev
//	@Pattern(regexp = "[a-zA-Z][0-9]{6}")
	private String login;

	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{5,}$")
	private String password;

	public UserCreateDto(@NotBlank String firstname, @NotBlank String lastname, String login,
			@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{5,}$") String password) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.login = login;
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	public void setPassword(String password) {
		this.password = password;
	}

}
