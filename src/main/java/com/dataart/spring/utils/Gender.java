package com.dataart.spring.utils;

/**
 * Simple type representing human gender (sex).
 * @author Witold Mescheryakov
 *
 */
public enum Gender {
	MALE		('M'),
	FEMALE		('F'),
	UNKNOWN		('U');

	private char value;

	public char getValue() {
		return value;
	}

	private Gender (char newValue) {
		this.value = newValue;
	}
}
