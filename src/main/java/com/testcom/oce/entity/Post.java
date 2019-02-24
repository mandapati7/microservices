package com.testcom.oce.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Post {

	@Id
	@GeneratedValue
	private int postId;

	private String comment;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;

	public Post() {
		super();
	}

	public Post(int postId, String comment, User user) {
		super();
		this.postId = postId;
		this.comment = comment;
		this.user = user;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
