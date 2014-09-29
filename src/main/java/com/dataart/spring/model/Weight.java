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
@Table(name = "weights")
public class Weight implements Serializable {

	@Column(name = "user_id")
	@Id
	private long userId;

	@Column(name = "date")
	@Id
	private Date date;

	@Column(name = "weight")
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
		return "Weight [userId=" + userId + ", date=" + date + ", weight=" + weight + "]";
	}

}
