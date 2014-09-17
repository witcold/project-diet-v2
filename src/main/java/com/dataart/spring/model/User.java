/**
 * 
 */
package com.dataart.spring.model;

/**
 * @author vmeshcheryakov
 *
 */
public class User {

	private long id;

	private String login;

	private String password;

	private String passwordConfirm;

	private String firstName;

	private String lastName;

//	private Sex sex;
//
//	private short yearOfBirth;
//
//	private short countryId;
//
//	private short currentHeight; // in centimeters
//
//	private float currentWeight; // in kilograms

	public User() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
		return "User [id=" + id + ", login=" + login + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	public void clone(User object) {
		this.firstName = object.firstName;
		this.id = object.id;
		this.lastName = object.lastName;
		this.login = object.login;
		this.password = object.password;
	}
}
