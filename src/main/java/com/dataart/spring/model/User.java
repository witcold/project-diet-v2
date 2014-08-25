/**
 * 
 */
package com.dataart.spring.model;

/**
 * @author vmeshcheryakov
 *
 */
public class User {

	private String login;

	private String password;

	private String passwordConfirm;

	private String firstName;

	private String lastName;

	public User() {
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "UserBean [login=" + login + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
