package org.anirudh.redquark.todoapp.model;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/* The @JsonIgnoreProperties annotation is used to ignore createdAt field during deserialization. 
 * We don’t want clients to send the createdAt value. If they send a value for this field, we’ll simply ignore it */
@Document(collection = "todos")
@JsonIgnoreProperties(value = { "createdAt" }, allowGetters = true)
public class Todo {

	@Id
	private String id;

	@NotBlank
	@Size(max = 100)
	@Indexed(unique = true) /* creates a unique index on title field */
	private String title;

	private boolean completed = false;

	private Date createdAt = new Date();

	/**
	 * default constructor
	 */
	public Todo() {
		super();
	}

	/**
	 * @param title
	 */
	public Todo(String title) {
		super();
		this.title = title;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the completed
	 */
	public boolean isCompleted() {
		return completed;
	}

	/**
	 * @param completed
	 *            the completed to set
	 */
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt
	 *            the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Todo[id=%s, title='%s', completed='%s']", id, title, completed);
	}

}
