package com.portal.jobconnect.service;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.portal.jobconnect.model.Post;

public class PostServiceTest {

	private PostService postService;

	String postId = "1";
	String title = "Test Title";
	String description = "Test Description";
	String location = "Test Location";

	@Before
	public void setUp() {
		postService = new PostService();
	}

	@Test
	public void testCreatePost_EmptyParameters() {

		String postId = "";
		String title = "";
		String description = "";
		String location = "";

		boolean result = postService.createPost(postId, title, description, location);

		assertFalse(result);
		assertNull(postService.readPost(postId));
	}

	@Test
	public void testCreatePost() {
		boolean result = postService.createPost(postId, title, description, location);

		assertTrue(result);
		assertNotNull(postService.readPost(postId));
	}

	@Test
	public void testCreatePost_Success() {

		String postId = "1";
		String title = "Test Title";
		String description = "Test Description";
		String location = "Test Location";

		boolean result = postService.createPost(postId, title, description, location);

		assertTrue(result);
		assertNotNull(postService.readPost(postId));
	}

	@Test
	public void testReadExistingPost() {

		String postId = "1";
		String title = "Test Title";
		String description = "Test Description";
		String location = "Test Location";

		postService.createPost(postId, title, description, location);

		Post readPost = postService.readPost(postId);

		assertNotNull(readPost);
		assertEquals(postId, readPost.getPostId());
		assertEquals(title, readPost.getTitle());
		assertEquals(description, readPost.getDescription());
		assertEquals(location, readPost.getLocation());
	}

	@Test
	public void testReadNonExistingPost() {
		String postId = "1";
		Post readPost = postService.readPost(postId);

		assertNull(readPost);
	}

	@Test
	public void testUpdatePost() {
		String postId = "1";
		String title = "Test Title";
		String description = "Test Description";
		String location = "Test Location";

		postService.createPost(postId, title, description, location);
		Post readPost = postService.readPost(postId);

		assertTrue(postService.updatePost(postId, title, description, "updated location"));
		assertEquals(readPost.getLocation(), "updated location");
	}

	@Test
	public void testUpdatePost_NonExisting() {
		String postId = "1";
		String title = "Test Title";
		String description = "Test Description";
		String location = "Test Location";

		assertFalse(postService.updatePost(postId, title, description, location));
	}
	
	@Test
	public void testDeletePost() {
		String postId = "1";
		String title = "Test Title";
		String description = "Test Description";
		String location = "Test Location";

		postService.createPost(postId, title, description, location);
		assertTrue(postService.deletePost(postId));
	}

	@Test
	public void testDeletePost_NonExisting() {
		String postId = "1";

		assertFalse(postService.deletePost(postId));
	}
}
