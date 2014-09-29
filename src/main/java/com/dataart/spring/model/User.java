package com.dataart.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

/**
 * @author vmeshcheryakov
 *
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

	@Column(name = "user_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String login;

	private String password;

	@Transient
	private String passwordConfirm;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Type(type = "com.dataart.spring.utils.GenderType")
	private Gender gender;

	@Column(name = "birth_date")
	private Date birthDate;

	@Column(name = "country_id")
	private String countryId;

	private int height;

	@Column(name = "activity_level")
	private float activityLevel;

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

	public float getActivityLevel() {
		return activityLevel;
	}

	public void setActivityLevel(float activityLevel) {
		this.activityLevel = activityLevel;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", gender=" + gender + ", birthDate=" + birthDate
				+ ", countryId=" + countryId + ", height=" + height
				+ ", activityLevel=" + activityLevel + "]";
	}

	public void clone(User object) {
		this.id = object.id;
		this.login = object.login;
		this.password = object.password;
		this.firstName = object.firstName;
		this.lastName = object.lastName;
		this.gender = object.gender;
		this.birthDate = object.birthDate;
		this.countryId = object.countryId;
		this.height = object.height;
		this.activityLevel = object.activityLevel;
	}

}
