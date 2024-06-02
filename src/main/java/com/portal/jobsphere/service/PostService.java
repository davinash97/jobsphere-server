package com.portal.jobsphere.service;

import com.portal.jobsphere.model.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
public class PostService {

	private static final Logger logger = LoggerFactory.getLogger(PostService.class);
	HashMap<UUID, Object> postMap = new HashMap<>();

	public boolean createPost(UUID postId, String title, String description, String location) {

		try {
			if (postId == null || title == null || description == null || location == null
					|| postId.toString().isEmpty() || title.isEmpty() || description.isEmpty() || location.isEmpty())
				return false;

			if (postMap.containsKey(postId)) {
				return postMap.containsKey(postId); // Chances too low to reach here but still
			}
			Post post = new Post(postId, title, description, location);

			postMap.put(postId, post);

			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	public Post readPost(UUID postId) {
		if (!postMap.containsKey(postId))
			return null;

		return (Post) postMap.get(postId);
	}

	public boolean updatePost(UUID postId, String title, String description, String location) {

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
			existingPosts.setUpdatedAt();
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	public boolean deletePost(UUID postId) {
		if (!postMap.containsKey(postId))
			return false;
		postMap.remove(postId);
		return true;
	}
}
