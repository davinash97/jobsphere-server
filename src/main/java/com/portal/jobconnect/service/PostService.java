package com.portal.jobconnect.service;

import com.portal.jobconnect.model.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class PostService {

    private static final Logger logger = LoggerFactory.getLogger(PostService.class);
	HashMap<String, Object> postMap = new HashMap<>();

	public boolean createPost(String postId, String title, String description, String location) {

		try {
			if (postId == null || title == null || description == null || location == null
					|| postId.isEmpty() || title.isEmpty() || description.isEmpty() || location.isEmpty())
				return false;

			if (postMap.containsKey(postId)) {
				return postMap.containsKey(postId); // Chances are too low to reach here but still
			}

            Post post = new Post(postId, title, description, location);

			postMap.put(postId, post);

			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	public Post readPost(String postId) {
		if (!postMap.containsKey(postId))
			return null;

		return (Post) postMap.get(postId);
	}

	public boolean updatePost(String postId, String title, String description, String location) {

		if (!postMap.containsKey(postId))
			return false;

		try {
			Post existingPosts = readPost(postId);
			if (title != null)
				existingPosts.setTitle(title);

			if (description != null)
				existingPosts.setDescription(description);

			if (location != null)
				existingPosts.setLocation(location);

			postMap.put(postId, existingPosts);

			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	public boolean deletePost(String postId) {
		if (!postMap.containsKey(postId))
			return false;
		postMap.remove(postId);
		return true;
	}
}
