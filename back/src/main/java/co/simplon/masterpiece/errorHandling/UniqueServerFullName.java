package co.simplon.masterpiece.errorHandling;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueServerFullNameValidator.class)
public @interface UniqueServerFullName {

	String message() default "A server with provided full name already exists";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
