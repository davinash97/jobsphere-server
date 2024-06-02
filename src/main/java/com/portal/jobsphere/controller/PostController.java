package com.portal.jobsphere.controller;

import com.portal.jobsphere.model.Post;
import com.portal.jobsphere.model.ResponseObject;
import com.portal.jobsphere.service.PostService;
import com.portal.jobsphere.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@SuppressWarnings("unused")
@RestController
public class PostController implements Constants {

	private static final Logger logger = LoggerFactory.getLogger(PostController.class);
	private final PostService post = new PostService();
	private ResponseObject<?> response;

	@PostMapping(DEFAULT_POST_URI + "/create")
	public ResponseEntity<ResponseObject<?>> createPost(
			@RequestParam String title,
			@RequestParam String description,
			@RequestParam String location) {
		try {
			if (title.isEmpty() || description.isEmpty() || location.isEmpty()) {
				response = new ResponseObject<>(HttpStatus.BAD_REQUEST.value(), "bad", "Bad Request");
				return ResponseEntity.ok(response);
			}

			UUID uuid = UUID.randomUUID();

			if (post.createPost(uuid, title, description, location)) {
				logger.debug("Successfully created post with Id:\t{}", uuid);
			}
			return readPost(uuid.toString());
		} catch (Exception e) {
			response = new ResponseObject<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "bad",
					"internal error occurred");
			logger.error("Error occurred at:{}", e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@GetMapping(DEFAULT_POST_URI + "/read")
	public ResponseEntity<ResponseObject<?>> readPost(String postId) {
		try {
			if (postId.length() != 36) {
				response = new ResponseObject<>(HttpStatus.BAD_REQUEST.value(), "bad", "invalid id");
				return ResponseEntity.badRequest().body(response);
			}
			Post result = post.readPost(UUID.fromString(postId));
			response = (result == null)
					? new ResponseObject<>(HttpStatus.NOT_FOUND.value(), "bad", "id doesn't exist")
					: new ResponseObject<>(HttpStatus.OK.value(), "ok", result);
			logger.debug(response.toString());
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response = new ResponseObject<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "bad",
					"internal error occurred");
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@PutMapping(DEFAULT_POST_URI + "/update")
	public ResponseEntity<ResponseObject<?>> updatePost(
			@RequestParam UUID postId,
			@RequestParam(required = false) String title,
			@RequestParam(required = false) String description,
			@RequestParam(required = false) String location) {

		try {
			if (title == null && description == null && location == null) {
				response = new ResponseObject<>(HttpStatus.BAD_REQUEST.value(), "bad",
						"At least one of title, description, or location must be provided.");
				return ResponseEntity.badRequest().body(response);
			}

			if (post.updatePost(postId, title, description, location)) {
				logger.debug("Successfully updated post with Id:\t{}", postId);
			}
			return readPost(postId.toString());
		} catch (Exception e) {
			response = new ResponseObject<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "bad",
					"internal error occurred");
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@DeleteMapping(DEFAULT_POST_URI + "/delete")
	public ResponseEntity<ResponseObject<?>> deletePost(@RequestParam UUID postId) {
		try {
			response = (post.deletePost(postId))
					? new ResponseObject<>(HttpStatus.OK.value(), "ok")
					: new ResponseObject<>(HttpStatus.NOT_FOUND.value(), "not found");
			logger.debug(response.toString());
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response = new ResponseObject<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "bad",
					"internal error occurred");
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
}
