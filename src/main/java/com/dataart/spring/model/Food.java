package com.dataart.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author vmeshcheryakov
 *
 */
@Entity
@Table(name = "foods")
public class Food {

	@Column(name = "food_id")
	@Id
	private long id;

	@Column(name = "category_id")
	private long categoryId;

	@Column(name = "name_en")
	private String nameEn;

	@Column(name = "name_ru")
	private String nameRu;

	@Column(name = "calories")
	private int calories;

	@Column(name = "proteins")
	private int proteins;

	@Column(name = "fats")
	private int fats;

	@Column(name = "carbohydrates")
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
