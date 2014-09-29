package com.dataart.spring.dto;

import java.io.Serializable;
import java.util.Date;

import com.dataart.spring.model.Gender;
import com.dataart.spring.model.User;

/**
 * @author vmeshcheryakov
 *
 */
public class SignUpDTO implements Serializable {

	private long id;

	private String login;

	private String password;

	private String passwordConfirm;

	private String firstName;

	private String lastName;

	private Gender gender;

	private Date birthDate;

	private String countryId;

	private int height;

	private float weight;

	private float activityLevel;

	public SignUpDTO() {
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getActivityLevel() {
		return activityLevel;
	}

	public void setActivityLevel(float activityLevel) {
		this.activityLevel = activityLevel;
	}

	public User getUser() {
		User user = new User();
		user.setId(id);
		user.setLogin(login);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setGender(gender);
		user.setBirthDate(birthDate);
		user.setCountryId(countryId);
		user.setHeight(height);
		user.setActivityLevel(activityLevel);
		return user;
	}

}
