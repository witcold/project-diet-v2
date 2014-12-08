package com.dataart.spring.utils;

import java.util.Arrays;

import com.dataart.spring.model.Gender;

/**
 * Basal metabolic rate
 * @author Witold Mescheryakov
 */
public final class BMR {

	private BMR() {
		throw new AssertionError();
	}

	/**
	 * Harris-Benedict equation (original)
	 * 1918-1919
	 * @param isMale <code>true</code> for Male, <code>false</code> for Female
	 * @param height in cm
	 * @param weight in kg
	 * @param age in years
	 * @return daily energy expenditure
	 */
	public static float calculateHB(boolean isMale, int height, float weight, int age) {
		if (isMale) {
			return 66.4730f + (13.7516f * weight) + (5.0033f * height) - (6.7550f * age);
		}
		else {
			return 655.0955f + (9.5634f * weight) + (1.8496f * height) - (4.6756f * age);
		}
	}

	/**
	 * Harris-Benedict equation (revised by Roza and Shizgal)
	 * 1984
	 * @param isMale <code>true</code> for Male, <code>false</code> for Female
	 * @param height in cm
	 * @param weight in kg
	 * @param age in years
	 * @return daily energy expenditure
	 */
	public static float calculateHBR(boolean isMale, int height, float weight, int age) {
		if (isMale) {
			return 88.362f + (13.397f * weight) + (4.799f * height) - (5.677f * age);
		}
		else {
			return 447.593f + (9.247f * weight) + (3.098f * height) - (4.330f * age);
		}
	}

	/**
	 * Mifflin St Jeor equation
	 * @param isMale <code>true</code> for Male, <code>false</code> for Female
	 * @param height in cm
	 * @param weight in kg
	 * @param age in years
	 * @return daily energy expenditure
	 */
	public static float calculateM(boolean isMale, int height, float weight, int age) {
		int s;
		if (isMale) {
			s = 5;
		}
		else {
			s = -161;
		}
		return (10 * weight) + (6.25f * height) - (5 * age) + s;
	}

	/**
	 * Katch-McArdle formula
	 * @param isMale <code>true</code> for Male, <code>false</code> for Female
	 * @param height in cm
	 * @param weight in kg
	 * @param age in years
	 * @return resting daily energy expenditure
	 */
	public static float calculateKM(boolean isMale, int height, float weight, int age) {
		float lbm = LBM.calculate(isMale, height, weight);
		return 370 + (21.6f * lbm);
	}

	/**
	 * Cunningham formula
	 * @param isMale <code>true</code> for Male, <code>false</code> for Female
	 * @param height in cm
	 * @param weight in kg
	 * @param age in years
	 * @return daily energy expenditure
	 */
	public static float calculateC(boolean isMale, int height, float weight, int age) {
		float lbm = LBM.calculate(isMale, height, weight);
		return 500 + (22 * lbm);
	}

	/**
	 * Median formula
	 * @param gender
	 * @param age in years
	 * @param height in cm
	 * @param weight in kg
	 * @return daily energy expenditure
	 */
	public static int calculate(Gender gender, int age, int height, float weight) {
		float[] list = new float[5];
		if (gender == Gender.MALE || gender == Gender.FEMALE) { // Simple case
			boolean isMale = (gender == Gender.MALE);
			list[0] = BMR.calculateHB(isMale, height, weight, age);
			list[1] = BMR.calculateHBR(isMale, height, weight, age);
			list[2] = BMR.calculateM(isMale, height, weight, age);
			list[3] = BMR.calculateKM(isMale, height, weight, age);
			list[4] = BMR.calculateC(isMale, height, weight, age);
		} else { // Other gender? Well, let's calculate arithmetic mean
			list[0] = 0.5f * (BMR.calculateHB(true, height, weight, age) + BMR.calculateHB(false, height, weight, age));
			list[1] = 0.5f * (BMR.calculateHBR(true, height, weight, age) + BMR.calculateHBR(false, height, weight, age));
			list[2] = 0.5f * (BMR.calculateM(true, height, weight, age) + BMR.calculateM(false, height, weight, age));
			list[3] = 0.5f * (BMR.calculateKM(true, height, weight, age) + BMR.calculateKM(false, height, weight, age));
			list[4] = 0.5f * (BMR.calculateC(true, height, weight, age) + BMR.calculateC(false, height, weight, age));
		}
		Arrays.sort(list);
		return (int) list[2];
	}

}
