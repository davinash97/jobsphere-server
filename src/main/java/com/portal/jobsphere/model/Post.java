package com.portal.jobsphere.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.portal.jobsphere.utils.Constants;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "post")
public class Post implements Constants {

	@Column(nullable = false)
	private UUID profile_id;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID post_id;

	private String title;
	private String location;
	private String description;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> requirements;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> responsibilities;

	private Integer num_of_applicants;

	@CreatedDate
	@Column(nullable = false, updatable = false)
	private LocalDateTime created_at;

	@LastModifiedDate
	private LocalDateTime updated_at;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<UUID> ids_of_applicants;

	public Post() {}

	public Post(
		UUID profile_id,
		String title,
		String description,
		List<String> requirements,
		List<String> responsibilities,
		String location
	) {
		this.profile_id = profile_id;
		this.title = title;
		this.description = description;
		this.requirements = requirements;
		this.responsibilities = responsibilities;
		this.location = location;
		this.num_of_applicants = 0;
		this.created_at = LocalDateTime.now();
		this.updated_at = LocalDateTime.now();
		this.ids_of_applicants = new ArrayList<>();
	}

	public UUID getProfileId() {
		return profile_id;
	}

	public UUID getPostId() {
		return post_id;
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
		return created_at.format(formatter);
	}

	public String getUpdatedAt() {
		return updated_at.format(formatter);
	}

	public void setUpdatedAt() {
		updated_at = LocalDateTime.now();
	}

	public Integer getNumOfApplicants() {
		return num_of_applicants;
	}

	public void setNumOfApplicants(Integer num_of_applicants) {
		this.num_of_applicants = num_of_applicants;
	}

	public List<UUID> getIdsOfAppliedApplicants() {
		return ids_of_applicants;
	}

	public void setIdsOfAppliedApplicants(List<UUID> ids_of_applicants) {
		this.ids_of_applicants = ids_of_applicants;
	}

	public List<String> getRequirements() {
		return requirements;
	}

	public void setRequirements(List<String> requirements) {
		this.requirements = requirements;
	}

	public List<String> getResponsibilities() {
		return responsibilities;
	}

	public void setResponsibilities(List<String> responsibilities) {
		this.responsibilities = responsibilities;
	}
}
