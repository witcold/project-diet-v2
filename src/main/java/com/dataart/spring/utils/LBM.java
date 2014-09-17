package com.dataart.spring.utils;

/**
 * Lean body mass
 * @author Witold Mescheryakov
 *
 */
public class LBM {
	/**
	 * Hume formula
	 * 1966
	 * @return lean body mass
	 */
	public static float calculate (boolean male, int height, float weight) {
		if (male) {
			return (0.32810f * weight) + (0.33929f * height) - 29.5336f;
		}
		else {
			return (0.29569f * weight) + (0.41813f * height) - 43.2933f;
		}
	}
}
