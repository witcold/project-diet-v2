package com.dataart.spring.model;

import java.io.Serializable;

/**
 * @author vmeshcheryakov
 *
 */
public class Category implements Serializable {

	private long id;

	private long parentId;

	private String nameEn;

	private String nameRu;

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

}
