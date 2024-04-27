package com.portal.jobconnect.profile.employer;

import org.springframework.stereotype.Component;

import com.portal.jobconnect.model.Post;

import java.util.HashMap;

@Component
public class Employer {
	private Post posts;

	HashMap<String, Object> profileMap = new HashMap<String, Object>();
	HashMap<String, Object> employerMap = new HashMap<String, Object>();

	public Employer() {
	};

	public boolean createPost(String employerId, String title, String description, String location) {
		if (employerMap.containsKey(employerId)) {
			return employerMap.containsKey(employerId); // Chances are too low to reach here but still
		}

		posts = new Post(employerId, title, description, location);

		employerMap.put(employerId, posts);

		return true;
	}

	public Post readPost(String employerId) {
		if (!employerMap.containsKey(employerId))
			return null;

		return (Post) employerMap.get(employerId);
	}

	public boolean updatePost(String employerId, String title, String description, String location) {

		if (!employerMap.containsKey(employerId))
			return false;

		Post existingPosts = readPost(employerId);

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

		return true;
	}

	public boolean deletePost(String employerId) {
		if (!employerMap.containsKey(employerId))
			return false;
		employerMap.remove(employerId);
		return true;
	}

	public Post totalPosts(String employerId) {
		if (!employerMap.containsKey(employerId))
			return new Post(employerId, "doesn't exist");
		return new Post(employerId, Integer.toString(employerMap.size()));
	}
}
