package com.portal.jobconnect.profile.employer;

import org.springframework.stereotype.Component;

import com.portal.jobconnect.posts.Posts;

import java.util.HashMap;

@Component
public class Employer {
	private String name;
	private String company;

	Posts posts;

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
		if (employerMap.containsKey(employerId))
			return "Already exists"; // Chances are too low to reach here but still

		// Posts
		posts = new Posts(title, description, location);

		employerMap.put(employerId, posts);
		System.out.println("Created with UUID:\t" + employerId);
		return "Success: " + employerId;
	}

	public Posts readPost(String employerId) {
		if (!employerMap.containsKey(employerId))
			return null;

		return (Posts) employerMap.get(employerId);
	}

	public String updatePost(String employerId, String title, String description, String location) {

		if (!employerMap.containsKey(employerId))
			return "Doesn't exist";

		try {

			Posts existingPosts = (Posts) employerMap.get(employerId);

			if (title != null)
				existingPosts.setTitle(title);
			else
				existingPosts.getTitle();

			if (description != null)
				existingPosts.setDescription(description);
			else
				existingPosts.getDescription();

			if (location != null)
				existingPosts.setLocation(location);
			else
				existingPosts.getLocation();

			employerMap.put(employerId, existingPosts);
			System.out.println("Updated:\t" + employerId);

		} catch (Exception e) {
			return "Error at " + e.getMessage();
		}
		return "Success";
	}

	public String deletePost(String employerId) {
		if (!employerMap.containsKey(employerId))
			return "Doesn't exist";
		employerMap.remove(employerId);
		return "Success";
	}
}
