package com.dataart.spring.dto;

import com.dataart.spring.model.Food;

/**
 * Localized variant of {@link Food}.
 * @author vmeshcheryakov
 *
 */
public class FoodDTO {

	private long id;

	private long categoryId;

	private String name;

	private int calories;

	private int proteins;

	private int fats;

	private int carbohydrates;

	public FoodDTO() {
	}

	public FoodDTO(Food food, String language) {
		this.id = food.getId();
		this.categoryId = food.getCategoryId();
		this.name = food.getName(language);
		this.calories = food.getCalories();
		this.proteins = food.getProteins();
		this.fats = food.getFats();
		this.carbohydrates = food.getCarbohydrates();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public int getProteins() {
		return proteins;
	}

	public void setProteins(int proteins) {
		this.proteins = proteins;
	}

	public int getFats() {
		return fats;
	}

	public void setFats(int fats) {
		this.fats = fats;
	}

	public int getCarbohydrates() {
		return carbohydrates;
	}

	public void setCarbohydrates(int carbohydrates) {
		this.carbohydrates = carbohydrates;
	}

}
