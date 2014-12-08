package com.dataart.spring.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author vmeshcheryakov
 *
 */
@Entity
@Table(name = "categories")
public class Category {

	@Column(name = "category_id")
	@Id
	private long id;

	@Column(name = "parent_id")
	private long parentId;

	@Column(name = "name_en")
	private String nameEn;

	@Column(name = "name_ru")
	private String nameRu;

	@JsonIgnore
	@OneToMany(mappedBy = "parentId")
	private List<Category> subcategories;

	public Category() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
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

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public void setNameRu(String nameRu) {
		this.nameRu = nameRu;
	}

	public List<Category> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<Category> subcategories) {
		this.subcategories = subcategories;
	}

}
