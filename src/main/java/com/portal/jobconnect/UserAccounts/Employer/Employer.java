package com.portal.jobconnect.UserAccounts.Employer;

import org.springframework.stereotype.Component;

import com.portal.jobconnect.Posts.Posts;
import com.portal.jobconnect.Utils.GenerateUUID;

@Component
public class Employer {
	private String name;
	private String company;
	private String employerId = GenerateUUID.generateId();
	
	private final Posts posts;
	
	private Employer(Posts posts) {
		this.posts = posts;
	}

	public void createPost(String title, String description, String location) {
		posts.setTitle(title);
		posts.setLocation(location);
		posts.setDescription(description);
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
}
