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
	 * @param isMale <code>true</code> for Male, <code>false</code> for Female
	 * @param height in cm
	 * @param weight in kg
	 * @return lean body mass
	 */
	public static float calculate (boolean isMale, int height, float weight) {
		if (isMale) {
			return (0.32810f * weight) + (0.33929f * height) - 29.5336f;
		}
		else {
			return (0.29569f * weight) + (0.41813f * height) - 43.2933f;
		}
	}
}
