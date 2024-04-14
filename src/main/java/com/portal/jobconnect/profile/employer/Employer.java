package com.portal.jobconnect.profile.employer;

import org.springframework.stereotype.Component;

import com.portal.jobconnect.posts.Posts;

import java.util.HashMap;


@Component
public class Employer {
	private Posts posts;

	HashMap<String, Object> profileMap = new HashMap<String, Object>();
	HashMap<String, Object> employerMap = new HashMap<String, Object>();

	public Employer() {
	};

	public Posts createPost(String employerId, String title, String description, String location) {
		if (employerMap.containsKey(employerId)) {
			return new Posts(employerId, "Already exists"); // Chances are too low to reach here but still
		}

		posts = new Posts(employerId, title, description, location);

		employerMap.put(employerId, posts);

		return posts;
	}

	public Posts readPost(String employerId) {
		if (!employerMap.containsKey(employerId))
			return null;

		return (Posts) employerMap.get(employerId);
	}

	public Posts updatePost(String employerId, String title, String description, String location) {

		if (!employerMap.containsKey(employerId))
			return new Posts(employerId, null);

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

		} catch (Exception e) {
			return new Posts(employerId, "Error at " + e.getMessage());
		}
		return (Posts) employerMap.get(employerId);
	}

	public Posts deletePost(String employerId) {
		if (!employerMap.containsKey(employerId))
			return new Posts(employerId, "doesn't exist");
		employerMap.remove(employerId);
		return new Posts(employerId, "deleted successfully");
	}

	public Posts totalPosts(String employerId) {
		if (!employerMap.containsKey(employerId))
			return new Posts(employerId, "doesn't exist");
		return new Posts(employerId, Integer.toString(employerMap.size()));
	}
}
