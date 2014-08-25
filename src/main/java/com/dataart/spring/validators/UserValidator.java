/**
 * 
 */
package com.dataart.spring.validators;

import org.hibernate.validator.internal.constraintvalidators.EmailValidator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dataart.spring.model.User;

/**
 * @author vmeshcheryakov
 *
 */
public class UserValidator implements Validator {


	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "login", "login.empty");
		ValidationUtils.rejectIfEmpty(errors, "password", "password.empty");
		User user = (User) target;
		String login = user.getLogin();
		EmailValidator validator = new EmailValidator();
		if (!validator.isValid(login, null)) {
			errors.rejectValue("login", "login.notvalid", "Login not valid, possible mask: 'email@domain.td'");
		}
		String password = user.getPassword();
		if (password.length() < 4) {
			errors.rejectValue("password", "password.toosmall", "Password must be at least 4 character tall");
		}
		String passwordConfirm = user.getPasswordConfirm();
		if (passwordConfirm != null && !password.equals(passwordConfirm)) {
			errors.rejectValue("login", "login.notequal", "Passwords not equal");
		}
	}

}
