package com.portal.jobconnect.model;

import java.time.LocalDateTime;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Table;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Component
@Entity
@Table(name = "postsDb")
public class Posts {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String location;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private long numOfApplicants;

	@CreatedDate
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	private LocalDateTime updatedAt;

	@Column(nullable = false)
	private List<String> idsOfAppliedApplicants;

	public Posts() {
	};

	public Posts(String id, String description) {
		this.id = id;
		this.description = description;
	}

	public Posts(String id, String title, String description, String location) {
		this.id = id;
		this.title = title;
		this.location = location;
		this.description = description;
	};

	public String getId() {
		return id;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public long getNumOfApplicants() {
		return numOfApplicants;
	}

	public void setNumOfApplicants(long numOfApplicants) {
		this.numOfApplicants = numOfApplicants;
	}

	public List<String> getIdsOfAppliedApplicants() {
		return idsOfAppliedApplicants;
	}

	public void setIdsOfAppliedApplicants(List<String> idsOfAppliedApplicants) {
		this.idsOfAppliedApplicants = idsOfAppliedApplicants;
	}
}
