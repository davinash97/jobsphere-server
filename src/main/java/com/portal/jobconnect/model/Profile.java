package com.portal.jobconnect.model;

import org.springframework.stereotype.Component;

@Component
public class Profile {

	// Common
	private String name;
	private String id;
	private String accountType;
	private String email;
	private long phone;

	// Employee
	private long savedJobs;
	private int experience;
	private long appliedJobs;
	private String expertise;

	// Employer
	private int numOfPosts;
	private long numOfApplicants;
	private String organizationName;

	// Common
	public Profile(String id, String name, String accountType, String email, long phone) {
		this.id = id;
		this.name = name;
		this.accountType = accountType;
		this.email = email;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhone() {
		return phone;
	}

	public long setPhone(long phone) {
		return phone;
	}

	public long getSavedJobs() {
		return savedJobs;
	}

	public void setSavedJobs(long savedJobs) {
		this.savedJobs = savedJobs;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public long getAppliedJobs() {
		return appliedJobs;
	}

	public void setAppliedJobs(long appliedJobs) {
		this.appliedJobs = appliedJobs;
	}

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	public int getNumOfPosts() {
		return numOfPosts;
	}

	public void setNumOfPosts(int numOfPosts) {
		this.numOfPosts = numOfPosts;
	}

	public long getNumOfApplicants() {
		return numOfApplicants;
	}

	public void setNumOfApplicants(long numOfApplicants) {
		this.numOfApplicants = numOfApplicants;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

}
