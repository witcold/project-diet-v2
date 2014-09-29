package com.dataart.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author vmeshcheryakov
 *
 */
@Entity
@Table(name = "diaries")
public class Diary implements Serializable {

	@Column(name = "user_id")
	@Id
	private long userId;

	@Column(name = "food_id")
	@Id
	private long foodId;

	@Column(name = "timestamp")
	@Id
	private Date timestamp;

	@Column(name = "weight")
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
