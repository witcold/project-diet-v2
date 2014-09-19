package com.dataart.spring.utils;

/**
 * Simple type representing human gender (sex).
 * @author Witold Mescheryakov
 *
 */
public enum Gender {
	MALE		('M', "gender.male"),
	FEMALE		('F', "gender.female"),
	UNKNOWN		('U', "gender.unknown");

	private char value;

	private String description;

	public char getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}

	private Gender (char newValue, String newDescription) {
		this.value = newValue;
		this.description = newDescription;
	}
}
