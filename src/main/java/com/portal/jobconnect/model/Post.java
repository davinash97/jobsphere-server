package com.portal.jobconnect.model;

import com.portal.jobconnect.utils.Constants;
import javax.persistence.Table;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@SuppressWarnings("ALL")
@Component
@Entity
@Table(name = "postsDb")
public class Post implements Constants {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String postId;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String location;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private Long numOfApplicants;

	@CreatedDate
	@Column(nullable = false, updatable = false)
	private String createdAt;

	@LastModifiedDate
	private String updatedAt;

	@Column(nullable = false)
	private List<String> idsOfAppliedApplicants;

	public Post() {
    }

	public Post(String postId, String title, String description, String location) {
		this.postId = postId;
		this.title = title;
		this.location = location;
		this.description = description;
		createdAt = LocalDateTime.now().format(formatter);
    }

	public String getPostId() {
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
		return createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt() {
		updatedAt = LocalDateTime.now().format(formatter);
	}

	public Long getNumOfApplicants() {
		return numOfApplicants;
	}

	public void setNumOfApplicants(Long numOfApplicants) {
		this.numOfApplicants = numOfApplicants;
	}

	public List<String> getIdsOfAppliedApplicants() {
		return idsOfAppliedApplicants;
	}

	public void setIdsOfAppliedApplicants(List<String> idsOfAppliedApplicants) {
		this.idsOfAppliedApplicants = idsOfAppliedApplicants;
	}
}
