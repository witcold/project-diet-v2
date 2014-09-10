/**
 * 
 */
package com.dataart.spring.model;

import java.util.Date;

/**
 * @author vmeshcheryakov
 *
 */
public class Diary {

	private long userId;

	private long foodId;

	private Date timestamp;

	private float weight;

	public Diary() {
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getFoodId() {
		return foodId;
	}

	public void setFoodId(long foodId) {
		this.foodId = foodId;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

}
