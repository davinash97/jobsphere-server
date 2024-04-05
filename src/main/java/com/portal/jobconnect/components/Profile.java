package com.portal.jobconnect.components;

import org.springframework.stereotype.Component;

@Component
public class Profile {

	// Common
	public String name;
	public String accountType;

	// Employees
	public int age;
	public long savedJobs;
	public int experience;
	public long appliedJobs;
	public String expertise;

	// Employers
	public long numOfApplicants;
	public String organizationName;

	public Profile() {
	};

	// For Employers
	public Profile(String accountType, String name, String organizationName) {
		this.name = name;
		this.accountType = accountType;
		this.organizationName = organizationName;
	}

	// For Employees
	public Profile(String accountType, String name, int age, String expertise, int experience) {
		this.age = age;
		this.name = name;
		this.accountType = accountType;
		this.expertise = expertise;
		this.experience = experience;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String setName(String id, String name) {
		this.name = name;
		return this.name;
	}

	public void setSavedJobs(long savedJobs) {
		this.savedJobs = savedJobs;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	public void setAppliedJobs(long appliedJobs) {
		this.appliedJobs = appliedJobs;
	}

	public void setNumOfApplicants(long numOfApplicants) {
		this.numOfApplicants = numOfApplicants;
	}

	public void setOrganizationName(String id, String organizationName) {
		this.organizationName = organizationName;
	}
}
