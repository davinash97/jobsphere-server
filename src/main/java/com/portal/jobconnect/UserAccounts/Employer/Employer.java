package com.portal.jobconnect.UserAccounts.Employer;

import org.springframework.stereotype.Component;

import java.util.HashMap;

import com.google.gson.JsonObject;

@Component
public class Employer {
	private String name;
	private String company;

	HashMap<String, Object> employerMap = new HashMap<String, Object>();

	public Employer() {
	};

	public Employer(String name, String company) {
		this.name = name;
		this.company = company;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompany() {
		return this.company;
	}

	public String createPost(String employerId, String title, String description, String location) {
		if(employerMap.containsKey(employerId))
			return "Already exists"; // Chances are too low to reach here but still
		JsonObject employerData = new JsonObject();
		employerData.addProperty("title", title);
		employerData.addProperty("description", description);
		employerData.addProperty("location", location);
		employerMap.put(employerId, employerData.toString());
		System.out.println("Created with UUID:\t" + employerId);
		return "Success: " + employerId;
	}

	public Object readPost(String employerId) {
		if (employerMap.containsKey(employerId)) {
			Object result = employerMap.get(employerId);
			System.out.println(result);
			return result;
		} else
			return "Doesn't exist";
	}

	public String updatePost(String employerId, String title, String description, String location) {

		if (!employerMap.containsKey(employerId))
			return "Doesn't exist";

		JsonObject employerData = new JsonObject();

		if (title.isEmpty()) {
			employerData.addProperty("title", employerData.get(title).toString());
		} else {
			employerData.addProperty("title", title);
		}

		if (description.isEmpty()) {
			employerData.addProperty("description", employerData.get(description).toString());
		} else {
			employerData.addProperty("description", description);
		}

		if (location.isEmpty()) {
			employerData.addProperty("location", employerData.get(location).toString());
		} else {
			employerData.addProperty("location", location);
		}

		employerMap.put(employerId, employerData.toString());
		System.out.println("Updated:\t" + employerId);
		return "Success";
	}

	public String deletePost(String employerId) {
		if (!employerMap.containsKey(employerId))
			return "Doesn't exist";
		employerMap.remove(employerId);
		return "Success";
	}
}