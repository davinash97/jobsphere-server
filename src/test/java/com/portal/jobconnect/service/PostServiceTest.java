package com.portal.jobconnect.service;

import com.portal.jobconnect.model.Post;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

public class PostServiceTest {

	private PostService postService;

	String postId;
	String title;
	String description;
	String location;

	@Before
	public void setUp() {
		postService = new PostService();
	}

	@Test
	public void testCreatePost_EmptyParameters() {

		postId = "";
		title = "";
		description = "";
		location = "";

		boolean result = postService.createPost(postId, title, description, location);

		assertFalse(result);
		assertNull(postService.readPost(postId));
	}

	@Test
	public void testCreatePost() {
		postId = "1";
		title = "test title";
		description = "test description";
		location = "test location";
		boolean result = postService.createPost(postId, title, description, location);

		assertTrue(result);
		assertNotNull(postService.readPost(postId));
	}

	@Test
	public void testCreatePost_Success() {

		postId = "1";
		title = "Test Title";
		description = "Test Description";
		location = "Test Location";

		boolean result = postService.createPost(postId, title, description, location);

		assertTrue(result);
		assertNotNull(postService.readPost(postId));
	}

	@Test
	public void testReadExistingPost() {

		postId = "1";
		title = "Test Title";
		description = "Test Description";
		location = "Test Location";

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
		postId = "1";
		Post readPost = postService.readPost(postId);

		assertNull(readPost);
	}

	@Test
	public void testUpdatePost() {
		postId = "1";
		title = "Test Title";
		description = "Test Description";
		location = "Test Location";

		postService.createPost(postId, title, description, location);
		Post readPost = postService.readPost(postId);

		assertTrue(postService.updatePost(postId, title, description, "updated location"));
		assertEquals(readPost.getLocation(), "updated location");
	}

	@Test
	public void testUpdatePost_NonExisting() {
		postId = "1";
		title = "Test Title";
		description = "Test Description";
		location = "Test Location";

		assertFalse(postService.updatePost(postId, title, description, location));
	}

	@Test
	public void testDeletePost() {
		postId = "1";
		title = "Test Title";
		description = "Test Description";
		location = "Test Location";

		postService.createPost(postId, title, description, location);
		assertTrue(postService.deletePost(postId));
	}

	@Test
	public void testDeletePost_NonExisting() {
		postId = "1";

		assertFalse(postService.deletePost(postId));
	}
}
