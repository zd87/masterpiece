package co.simplon.masterpiece.errorHandling;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import co.simplon.masterpiece.dtos.ServerDto;
import co.simplon.masterpiece.services.ServerService;

public class UniqueServerFullNameValidator
		implements ConstraintValidator<UniqueServerFullName, ServerDto> {

	@Autowired
	private ServerService serverService;

	@Override
	public boolean isValid(ServerDto serverDto, ConstraintValidatorContext context) {
		return serverService.uniqueServerFullName(serverDto.getFullName(), serverDto.getId());
	}
}