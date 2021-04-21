package co.simplon.masterpiece.errorHandling;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueUserLoginValidator.class)
public @interface UniqueUserLogin {

	String message() default "A user with provided login already exists";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
