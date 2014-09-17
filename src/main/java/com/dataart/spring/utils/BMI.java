package com.dataart.spring.utils;

/**
 * Body mass index
 * @author Witold Mescheryakov
 */
public class BMI {
	/**
	 * Quetelet formula
	 * 1869
	 * @return body mass index
	 */
	public static float calculate(int height, float weight) {
		float h = 0.01f * height;
		if (h > 0) {
			return weight / (h * h);
		}
		else {
			return 0;
		}
	}
}
