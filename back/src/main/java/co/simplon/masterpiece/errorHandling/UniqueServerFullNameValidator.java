package co.simplon.masterpiece.errorHandling;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import co.simplon.masterpiece.services.ServerService;

public class UniqueServerFullNameValidator implements ConstraintValidator<UniqueServerFullName, String> {

	@Autowired
	private ServerService serverService;

	@Override
	public boolean isValid(String fullName, ConstraintValidatorContext context) {
		return serverService.uniqueServerFullName(fullName);
	}
}
