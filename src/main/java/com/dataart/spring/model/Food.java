package com.dataart.spring.model;

import java.io.Serializable;

/**
 * @author vmeshcheryakov
 *
 */
public class Food implements Serializable {

	private long id;

	private long categoryId;

	private String nameEn;

	private String nameRu;

	private int calories;

	private int proteins;

	private int fats;

	private int carbohydrates;

	public Food() {
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

	public String getName(String language) {
		if (language.equalsIgnoreCase("en")) {
			return nameEn;
		}
		if (language.equalsIgnoreCase("ru")) {
			return nameRu;
		}
		return null;
	}

	public String getNameEn() {
		return nameEn;
	}

	public String getNameRu() {
		return nameRu;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public void setNameRu(String nameRu) {
		this.nameRu = nameRu;
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
