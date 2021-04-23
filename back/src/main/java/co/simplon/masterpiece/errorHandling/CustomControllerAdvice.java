package co.simplon.masterpiece.errorHandling;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomControllerAdvice extends ResponseEntityExceptionHandler {

	/**
	 * Custom validation error response
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		List<FieldError> errors = ex.getBindingResult().getFieldErrors();
		List<ValidationError> validationErrors = new ArrayList<>();
		for (FieldError error : errors) {
			validationErrors.add(new ValidationError(error.getField(), error.getCode()));
		}
		List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
		for (ObjectError error : globalErrors) {
			validationErrors.add(new ValidationError(error.getObjectName(), error.getCode()));
		}

		return handleExceptionInternal(ex, validationErrors, headers, status, request);
	}

}
