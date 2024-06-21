package com.portal.jobsphere.model;

import java.util.List;
import java.util.UUID;

import com.portal.jobsphere.enums.Gender;
import com.portal.jobsphere.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "profile")
public class Profile {

	// Common
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID profileId;

	private String name;
	private Gender gender;
	private Role role;
	private String email;
	private Long phone;

	// Employee
	@Column(name = "saved_jobs")
	private Long savedJobs;

	private Integer experience;

	@Column(name = "applied_jobs")
	private Long appliedJobs;

	private String expertise;

	// Employer
	@Column(name = "all_posts")
	@ElementCollection(fetch = FetchType.EAGER)
	private List<UUID> allPosts;

	@Column(name = "total_posts")
	private Integer numOfPosts;

	@Column(name = "total_applicants")
	private Long numOfApplicants;

	@Column(name = "organization")
	private String organizationName;

	public Profile() {}

	// Common
	public Profile(
		String name,
		Gender gender,
		Role role,
		String email,
		Long phone
	) {
		this.name = name;
		this.gender = gender;
		this.role = role;
		this.email = email;
		this.phone = phone;
		this.numOfPosts = 0;
	}

	public UUID getProfileId() {
		return profileId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public Long getSavedJobs() {
		return savedJobs;
	}

	public void setSavedJobs(Long savedJobs) {
		this.savedJobs = savedJobs;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	public Long getAppliedJobs() {
		return appliedJobs;
	}

	public void setAppliedJobs(Long appliedJobs) {
		this.appliedJobs = appliedJobs;
	}

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	public Integer getNumOfPosts() {
		return numOfPosts;
	}

	public void setNumOfPosts(Integer numOfPosts) {
		this.numOfPosts = numOfPosts;
	}

	public Long getNumOfApplicants() {
		return numOfApplicants;
	}

	public void setNumOfApplicants(Long numOfApplicants) {
		this.numOfApplicants = numOfApplicants;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public List<UUID> getAllPosts() {
		return allPosts;
	}
}
