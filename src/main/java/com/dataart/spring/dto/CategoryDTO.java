package com.dataart.spring.dto;

import java.io.Serializable;

import com.dataart.spring.model.Category;

/**
 * Localized variant of {@link Category}.
 * @author vmeshcheryakov
 *
 */
public class CategoryDTO implements Serializable {

	private long id;

	private long parentId;

	private String name;

	public CategoryDTO() {
	}

	public CategoryDTO(Category category, String language) {
		this.id = category.getId();
		this.parentId = category.getId();
		this.name = category.getName(language);
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
