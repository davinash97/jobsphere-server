package com.portal.jobsphere.service;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.jobsphere.model.Post;
import com.portal.jobsphere.repository.PostRepository;

@Service
@Transactional
public class PostService {

	private static final Logger logger = LoggerFactory.getLogger(PostService.class);

	@Autowired
	private ProfileService profileService;

	@Autowired
	private PostRepository postRepository;

	public Post createPost(UUID profileId, String title, String description, List<String> requirements, List<String> responsibilities, String location) {

		try {
			if (title == null || description == null || location == null
					|| title.isEmpty() || description.isEmpty() || location.isEmpty()) {
				return null;
			}

			if (profileService.profileIdExists(profileId)) {
				Post post = new Post(profileId, title, description, requirements, responsibilities, location);
				postRepository.save(post);
				return readPost(post.getPostId());
			}

			return null;

		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	public Post readPost(UUID postId) {
		return (Post) postRepository.findById(postId).orElse(null);
	}

	public boolean updatePost(UUID profileId, UUID postId, String title, String description, List<String> requirements, List<String> responsibilities, String location) {

		try {
			if (profileService.profileIdExists(profileId)) {
				return false;
			}

			Post existingPost = postRepository.findById(postId).orElse(null);

			if (existingPost == null) {
				return false;
			}

			if (title != null) {
				existingPost.setTitle(title);
			}

			if (description != null) {
				existingPost.setDescription(description);
			}

			if (requirements.isEmpty()) {
				existingPost.setRequirements(requirements);
			}

			if (responsibilities.isEmpty()) {
				existingPost.setResponsibilities(responsibilities);
			}

			if (location != null) {
				existingPost.setLocation(location);
			}

			existingPost.setUpdatedAt();
			postRepository.save(existingPost);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	public boolean deletePost(UUID profileId, UUID postId) {
		if (postIdExists(profileId, postId)) {
			postRepository.deleteById(postId);
			return true;
		}
		return false;
	}

	// Others
	@Cacheable("posts")
	public List<Post> fetchAll() {
		return postRepository.findAll();
	}
	
	public boolean postIdExists(UUID profileId, UUID postId) {
		if (profileService.profileIdExists(profileId)) {
			return postRepository.existsById(postId);
		}
		return false;
	}
}
