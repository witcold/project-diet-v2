package com.dataart.spring.dto;

import java.util.Date;

import com.dataart.spring.model.Diary;

/**
 * Localized variant of {@link Diary}
 * @author vmeshcheryakov
 *
 */
public class DiaryDTO {

	private Date timestamp;

	private FoodDTO food;

	private float weight;

	public DiaryDTO() {
	}

	public DiaryDTO(Diary diary, String language) {
		this.timestamp = diary.getTimestamp();
		this.food = new FoodDTO(diary.getFood(), language);
		this.weight = diary.getWeight();
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public FoodDTO getFood() {
		return food;
	}

	public void setFood(FoodDTO food) {
		this.food = food;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

}
