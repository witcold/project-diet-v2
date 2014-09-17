package com.dataart.spring.utils;

/**
 * Basal metabolic rate
 * @author Witold Mescheryakov
 */
public class BMR {
	/**
	 * Harris-Benedict equation (original)
	 * 1918-1919
	 * @return daily energy expenditure
	 */
	public static float calculateHB(boolean male, int height, float weight, int age) {
		if (male) {
			return 66.4730f + (13.7516f * weight) + (5.0033f * height) - (6.7550f * age);
		}
		else {
			return 655.0955f + (9.5634f * weight) + (1.8496f * height) - (4.6756f * age);
		}
	}

	/**
	 * Harris-Benedict equation (revised by Roza and Shizgal)
	 * 1984
	 * @return daily energy expenditure
	 */
	public static float calculateHBR(boolean male, int height, float weight, int age) {
		if (male) {
			return 88.362f + (13.397f * weight) + (4.799f * height) - (5.677f * age);
		}
		else {
			return 447.593f + (9.247f * weight) + (3.098f * height) - (4.330f * age);
		}
	}

	/**
	 * Mifflin St Jeor equation
	 * @return daily energy expenditure
	 */
	public static float calculateM(boolean male, int height, float weight, int age) {
		int s;
		if (male) {
			s = 5;
		}
		else {
			s = -161;
		}
		return (10 * weight) + (6.25f * height) - (5 * age) + s;
	}

	/**
	 * Katch-McArdle formula
	 * @return resting daily energy expenditure
	 */
	public static float calculateKM(boolean male, int height, float weight, int age) {
		float lbm = LBM.calculate(male, height, weight);
		return 370 + (21.6f * lbm);
	}

	/**
	 * Cunningham formula
	 * @return daily energy expenditure
	 */
	public static float calculateC(boolean male, int height, float weight, int age) {
		float lbm = LBM.calculate(male, height, weight);
		return 500 + (22 * lbm);
	}
}
