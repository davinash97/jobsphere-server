package com.portal.jobsphere.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.portal.jobsphere.utils.Constants;

@Entity
@Table(name="post")
public class Post implements Constants {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID postId;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String location;

	@Column(nullable = false)
	private String description;

	@Column(name = "num_of_applicants")
	private Integer numOfApplicants;

	@CreatedDate
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Column(name="updated_at")
	private LocalDateTime updatedAt;

	@Column(name = "total_applicants", nullable = false)
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> idsOfAppliedApplicants;

	public Post() {
	}

	public Post(String title, String description, String location) {
		this.title = title;
		this.location = location;
		this.description = description;
		this.numOfApplicants = 0;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
		this.idsOfAppliedApplicants = new ArrayList<>();
	}

	public UUID getPostId() {
		return postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatedAt() {
		return createdAt.format(formatter);
	}

	public String getUpdatedAt() {
		return updatedAt.format(formatter);
	}

	public void setUpdatedAt() {
		updatedAt = LocalDateTime.now();
	}

	public Integer getNumOfApplicants() {
		return numOfApplicants;
	}

	public void setNumOfApplicants(Integer numOfApplicants) {
		this.numOfApplicants = numOfApplicants;
	}

	public List<String> getIdsOfAppliedApplicants() {
		return idsOfAppliedApplicants;
	}

	public void setIdsOfAppliedApplicants(List<String> idsOfAppliedApplicants) {
		this.idsOfAppliedApplicants = idsOfAppliedApplicants;
	}
}
