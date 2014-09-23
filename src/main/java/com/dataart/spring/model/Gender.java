package com.dataart.spring.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple type representing human gender (sex).
 * @author Witold Mescheryakov
 *
 */
public enum Gender {
	MALE		('M', "gender.male"),
	FEMALE		('F', "gender.female"),
	UNKNOWN		('U', "gender.unknown");

	private final char value;

	// Reverse-lookup map
	private static final Map<Character, Gender> lookup = new HashMap<Character, Gender>();

	static {
		for (Gender gender : Gender.values())
			lookup.put(gender.getValue(), gender);
	}

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

	public static Gender get(Character value) {
		return lookup.get(value);
	}

}
