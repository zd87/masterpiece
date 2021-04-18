package co.simplon.masterpiece.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto {

	@NotBlank
	@Size(max = 45)
	private String firstname;

	@NotBlank
	@Size(max = 255)
	private String lastname;

	@NotBlank
	@Pattern(regexp = "[a-zA-Z][0-9]{6}") /* A letter followed by 6 digits */
	private String login;

	/* minimum 7 characters, at least one capital letter, at least one digit */
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{7,}$")
	private String password;

}
