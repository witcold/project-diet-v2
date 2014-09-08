/**
 * 
 */
package com.dataart.spring.model;

import java.util.Date;

/**
 * @author vmeshcheryakov
 *
 */
public class Weight {

	private long userId;

	private Date date;

	private float weight;

	public Weight() {
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
		return "Weight [userId=" + userId + ", date=" + date + ", weight="
				+ weight + "]";
	}

	public Object[] getData() {
		return new Object[] {date.getTime(), weight};
	}

}
