package com.portal.jobsphere.model;

import java.util.List;
import java.util.UUID;

import com.portal.jobsphere.enums.Gender;
import com.portal.jobsphere.enums.Role;

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

	private String first_name;
	private String last_name;
	private String name;
	private Gender gender;
	private Role role;
	private String email;
	private Long phone;

	// Employee
	private Long saved_jobs;
	private Integer experience;
	private Long applied_jobs;
	private String expertise;

	// Employer
	@ElementCollection(fetch = FetchType.EAGER)
	private List<UUID> all_posts;
	private Integer total_posts;
	private Long total_applicants;
	private String organization;

	public Profile() {
	}

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
		this.total_posts = 0;
	}

	public String getFirstName() {
		return first_name;
	}

	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}

	public String getLastName() {
		return last_name;
	}

	public void setLastName(String last_name) {
		this.last_name = last_name;
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
		return saved_jobs;
	}

	public void setSavedJobs(Long savedJobs) {
		this.saved_jobs = savedJobs;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	public Long getAppliedJobs() {
		return applied_jobs;
	}

	public void setAppliedJobs(Long applied_jobs) {
		this.applied_jobs = applied_jobs;
	}

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	public Integer getNumOfPosts() {
		return total_posts;
	}

	public void setNumOfPosts(Integer total_posts) {
		this.total_posts = total_posts;
	}

	public Long getNumOfApplicants() {
		return total_applicants;
	}

	public void setNumOfApplicants(Long total_applicants) {
		this.total_applicants = total_applicants;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganizationName(String organization) {
		this.organization = organization;
	}

	public List<UUID> getAllPosts() {
		return all_posts;
	}
}
