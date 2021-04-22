package co.simplon.masterpiece.errorHandling;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import co.simplon.masterpiece.services.UserService;

public class UniqueUserLoginValidator implements ConstraintValidator<UniqueUserLogin, String> {

	@Autowired
	private UserService userService;

	@Override
	public boolean isValid(String login, ConstraintValidatorContext context) {
		return userService.uniqueUserLogin(login);
	}
}
