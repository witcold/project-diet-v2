package com.dataart.spring.utils;

/**
 * @author Witold Mescheryakov
 *
 */
public enum Sex {
	Male ((byte) 1),
	Female ((byte) 2);

	private byte value;

	public byte getValue() {
		return value;
	}

	private Sex (byte newValue) {
		this.value = newValue;
	}
}
