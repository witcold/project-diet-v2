package com.dataart.spring.validators;

import org.hibernate.validator.internal.constraintvalidators.EmailValidator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dataart.spring.dto.SignUpDTO;

/**
 * @author vmeshcheryakov
 *
 */
public class SignUpValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SignUpDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "login", "login.empty");
		ValidationUtils.rejectIfEmpty(errors, "password", "password.empty");
		SignUpDTO user = (SignUpDTO) target;
		String login = user.getLogin();
		EmailValidator validator = new EmailValidator();
		if (!validator.isValid(login, null)) {
			errors.rejectValue("login", "login.notvalid");
		}
		String password = user.getPassword();
		String passwordConfirm = user.getPasswordConfirm();
		if (password.length() < 4) {
			errors.rejectValue("password", "password.toosmall");
		}
		if (!password.equals(passwordConfirm)) {
			errors.rejectValue("password", "password.notequal");
		}
	}

}
