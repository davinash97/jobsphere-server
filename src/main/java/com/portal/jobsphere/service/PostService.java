package com.portal.jobsphere.service;

import com.portal.jobsphere.model.Post;

import com.portal.jobsphere.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class PostService {

	private static final Logger logger = LoggerFactory.getLogger(PostService.class);

	@Autowired
	private PostRepository postRepository;

	public Post createPost(String title, String description, String location) {

		try {
			if (title == null || description == null || location == null
					|| title.isEmpty() || description.isEmpty() || location.isEmpty())
				return null;

			Post post = new Post(title, description, location);

			postRepository.save(post);

			return readPost(post.getPostId());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	public Post readPost(UUID postId) {
		return (Post) postRepository.findById(postId).orElse(null);
	}

	public boolean updatePost(UUID postId, String title, String description, String location) {

		try {
			Post existingPost = postRepository.findById(postId).orElse(null);

			if(existingPost == null)
				return false;

			if (title != null)
				existingPost.setTitle(title);

			if (description != null)
				existingPost.setDescription(description);

			if (location != null)
				existingPost.setLocation(location);

			existingPost.setUpdatedAt();
			postRepository.save(existingPost);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	public boolean deletePost(UUID postId) {
		if (!postRepository.existsById(postId))
			return false;
		postRepository.deleteById(postId);
//		postRepository.delete();
		return true;
	}
}
