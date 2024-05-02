package com.portal.jobconnect.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.portal.jobconnect.model.Post;
import com.portal.jobconnect.model.ResponseObject;
import com.portal.jobconnect.service.PostService;
import com.portal.jobconnect.utils.Constants;

@RestController
public class PostController implements Constants {

	private final PostService post = new PostService();

	private static final Logger logger = LoggerFactory.getLogger(PostController.class);

	private ResponseObject<?> response;

	@PostMapping(DEFAULT_EMPLOYER_URI + "/post/create")
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

			if (post.createPost(uuid.toString(), title, description, location)) {
                logger.info("Successfully created post with Id:\t{}", uuid);
			}
			return readPost(uuid.toString());
		} catch (Exception e) {
			response = new ResponseObject<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "bad",
					"internal error occurred");
			logger.error("Error occurred at:{}", e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@GetMapping(DEFAULT_EMPLOYER_URI + "/post/read")
	public ResponseEntity<ResponseObject<?>> readPost(String postId) {
		try {
			if (postId.length() != 36) {
				response = new ResponseObject<>(HttpStatus.BAD_REQUEST.value(), "bad", "invalid id");
				return ResponseEntity.badRequest().body(response);
			}
			Post result = post.readPost(postId);
			response = (result == null)
					? new ResponseObject<>(HttpStatus.NOT_FOUND.value(), "bad", "id doesn't exist")
					: new ResponseObject<>(HttpStatus.OK.value(), "ok", result);
			logger.info(response.toString());
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response = new ResponseObject<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "bad",
					"internal error occurred");
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@PutMapping(DEFAULT_EMPLOYER_URI + "/post/update")
	public ResponseEntity<ResponseObject<?>> updatePost(
			@RequestParam String postId,
			@RequestParam(required = false) String title,
			@RequestParam(required = false) String description,
			@RequestParam(required = false) String location) {

		try {
			if (title == null && description == null && location == null) {
				response = new ResponseObject<>(HttpStatus.BAD_REQUEST.value(), "bad",
						"At least one of title, description, or location must be provided.");
				return ResponseEntity.badRequest().body(response);
			}

			post.updatePost(postId, title, description, location);
			return readPost(postId);
		} catch (Exception e) {
			response = new ResponseObject<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "bad",
					"internal error occurred");
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@DeleteMapping(DEFAULT_EMPLOYER_URI + "/post/delete")
	public ResponseEntity<ResponseObject<?>> deletePost(@RequestParam String postId) {
		try {
			response = (post.deletePost(postId))
					? new ResponseObject<>(HttpStatus.OK.value(), "ok")
					: new ResponseObject<>(HttpStatus.NOT_FOUND.value(), "not found");
			logger.info(response.toString());
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response = new ResponseObject<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "bad",
					"internal error occurred");
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
}
