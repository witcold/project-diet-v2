/**
 * 
 */
package com.dataart.spring.dto;

import java.util.Date;

/**
 * @author vmeshcheryakov
 *
 */
public class CaloriesDTO {

	private Date date;

	private int calories;

	public CaloriesDTO() {
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

}
