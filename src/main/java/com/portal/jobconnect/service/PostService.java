package com.portal.jobconnect.service;

import java.util.HashMap;

import com.portal.jobconnect.model.Post;

public class PostService {
	private Post post;

	HashMap<String, Object> postMap = new HashMap<String, Object>();

	public boolean createPost(String postId, String title, String description, String location) {
		if (postMap.containsKey(postId)) {
			return postMap.containsKey(postId); // Chances are too low to reach here but still
		}

		post = new Post(postId, title, description, location);

		postMap.put(postId, post);

		return true;
	}

	public Post readPost(String postId) {
		if (!postMap.containsKey(postId))
			return null;

		return (Post) postMap.get(postId);
	}

	public boolean updatePost(String postId, String title, String description, String location) {

		if (!postMap.containsKey(postId))
			return false;

		Post existingPosts = readPost(postId);

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

		postMap.put(postId, existingPosts);

		return true;
	}

	public boolean deletePost(String postId) {
		if (!postMap.containsKey(postId))
			return false;
		postMap.remove(postId);
		return true;
	}

	public Post totalPosts(String postId) {
		if (!postMap.containsKey(postId))
			return new Post(postId, "doesn't exist");
		return new Post(postId, Integer.toString(postMap.size()));
	}
}
