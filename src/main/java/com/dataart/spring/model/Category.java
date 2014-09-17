/**
 * 
 */
package com.dataart.spring.model;

import java.io.Serializable;

/**
 * @author vmeshcheryakov
 *
 */
@SuppressWarnings("serial")
public class Category implements Serializable {

	private long id;

	private long parentId;

	private String name;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
