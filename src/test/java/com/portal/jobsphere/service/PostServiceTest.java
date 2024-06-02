package com.portal.jobsphere.service;

import com.portal.jobsphere.model.Post;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

public class PostServiceTest {

	private PostService postService;

	UUID postId = UUID.randomUUID();
	String title;
	String description;
	String location;

	@Before
	public void setUp() {
		postService = new PostService();
	}

	@Test
	public void testCreatePost_EmptyParameters() {

		title = "";
		description = "";
		location = "";

		boolean result = postService.createPost(postId, title, description, location);

		assertFalse(result);
		assertNull(postService.readPost(postId));
	}

	@Test
	public void testCreatePost() {
		title = "test title";
		description = "test description";
		location = "test location";
		boolean result = postService.createPost(postId, title, description, location);

		assertTrue(result);
		assertNotNull(postService.readPost(postId));
	}

	@Test
	public void testCreatePost_Success() {

		title = "Test Title";
		description = "Test Description";
		location = "Test Location";

		boolean result = postService.createPost(postId, title, description, location);

		assertTrue(result);
		assertNotNull(postService.readPost(postId));
	}

	@Test
	public void testReadExistingPost() {

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
		Post readPost = postService.readPost(postId);

		assertNull(readPost);
	}

	@Test
	public void testUpdatePost() {
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
		title = "Test Title";
		description = "Test Description";
		location = "Test Location";

		assertFalse(postService.updatePost(postId, title, description, location));
	}

	@Test
	public void testDeletePost() {
		title = "Test Title";
		description = "Test Description";
		location = "Test Location";

		postService.createPost(postId, title, description, location);
		assertTrue(postService.deletePost(postId));
	}

	@Test
	public void testDeletePost_NonExisting() {
		postId = UUID.randomUUID();

		assertFalse(postService.deletePost(postId));
	}
}
