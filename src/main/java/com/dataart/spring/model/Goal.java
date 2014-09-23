package com.dataart.spring.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author vmeshcheryakov
 *
 */
@SuppressWarnings("serial")
public class Goal implements Serializable {

	private long userId;

	private Date date;

	private float weight;

	public Goal() {
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Goal [userId=" + userId + ", date=" + date + ", weight=" + weight + "]";
	}

}