package co.simplon.masterpiece.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import co.simplon.masterpiece.services.UserService;

public class UniqueUserValidator implements ConstraintValidator<UniqueUser, String> {

	private UserService service;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return service.uniqueUserLogin(value);
	}

}
