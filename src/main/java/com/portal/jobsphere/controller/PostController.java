package com.portal.jobsphere.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.portal.jobsphere.exception.CustomException;
import com.portal.jobsphere.model.Post;
import com.portal.jobsphere.model.ResponseObject;
import com.portal.jobsphere.service.PostService;
import com.portal.jobsphere.utils.Constants;

@RestController
public class PostController implements Constants {

	private static final Logger logger = LoggerFactory.getLogger(
			PostController.class
	);

	@Autowired
	private PostService postService;

	private final CustomException exception = new CustomException();
	
	private ResponseObject<?> response;
	
	@PostMapping(DEFAULT_POST_URI + "/{profileId}")
	public ResponseEntity<ResponseObject<?>> createPost(
			@PathVariable UUID profileId,
			@RequestParam String title,
			@RequestParam String description,
			@RequestParam List<String> requirements,
			@RequestParam List<String> responsibilities,
			@RequestParam String location
	) {
		try {
			if (title.isEmpty() || description.isEmpty() || location.isEmpty()) {
				response = new ResponseObject<>(
						HttpStatus.BAD_REQUEST.value(),
						"bad",
						"Bad Request"
				);
				return ResponseEntity.badRequest().body(response);
			}

			Post post = postService.createPost(
					profileId,
					title,
					description,
					requirements,
					responsibilities,
					location
			);
			if (post != null) {
				return readPost(post.getPostId());
			}

			return ResponseEntity.badRequest().body(exception.notFound(profileId));
		} catch (Exception e) {
			response = new ResponseObject<>(
					HttpStatus.INTERNAL_SERVER_ERROR.value(),
					"bad",
					"internal error occurred"
			);
			logger.error("Error occurred at:{}", e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@GetMapping(DEFAULT_POST_URI)
	public ResponseEntity<ResponseObject<?>> readPost(UUID postId) {
		try {
			if (postId.toString().length() != 36) {
				response = new ResponseObject<>(
						HttpStatus.BAD_REQUEST.value(),
						"bad",
						"invalid id"
				);
				return ResponseEntity.badRequest().body(response);
			}

			Post result = postService.readPost(postId);
			response = (result == null)
					? exception.notFound(postId)
					: new ResponseObject<>(HttpStatus.OK.value(), "ok", result);

			logger.debug(response.toString());
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response = new ResponseObject<>(
					HttpStatus.INTERNAL_SERVER_ERROR.value(),
					"bad",
					"internal error occurred"
			);
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@PutMapping(DEFAULT_POST_URI + "/{profileId}")
	public ResponseEntity<ResponseObject<?>> updatePost(
			@PathVariable UUID profileId,
			@RequestParam UUID postId,
			@RequestParam(required = false) String title,
			@RequestParam(required = false) String description,
			@RequestParam List<String> requirements,
			@RequestParam List<String> responsibilities,
			@RequestParam(required = false) String location
	) {
		try {
			if (title == null
					&& description == null
					&& location == null
					&& requirements == null
					&& responsibilities == null) {
				response = new ResponseObject<>(
						HttpStatus.BAD_REQUEST.value(),
						"bad",
						"At least one of title, description, requirements, responsibilities or location must be provided."
				);
				return ResponseEntity.badRequest().body(response);
			}

			if (postService.updatePost(
					profileId,
					postId,
					title,
					description,
					responsibilities,
					requirements,
					location
			)) {
				logger.debug("Successfully updated post with Id:\t{}", postId);
				return readPost(postId);
			} else {
				response = new ResponseObject<>(
						HttpStatus.BAD_REQUEST.value(),
						"bad",
						"either that profile Id is invalid, or doesn't exist."
				);
				return ResponseEntity.badRequest().body(response);
			}
		} catch (Exception e) {
			response = new ResponseObject<>(
					HttpStatus.INTERNAL_SERVER_ERROR.value(),
					"bad",
					"internal error occurred"
			);
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@DeleteMapping(DEFAULT_POST_URI + "/{profileId}")
	public ResponseEntity<ResponseObject<?>> deletePost(
			@PathVariable UUID profileId,
			@RequestParam UUID postId
	) {
		try {
			response = (postService.deletePost(profileId, postId))
					? new ResponseObject<>(HttpStatus.OK.value(), "ok")
					: exception.notFound(postId);
			logger.debug(response.toString());
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response = new ResponseObject<>(
					HttpStatus.INTERNAL_SERVER_ERROR.value(),
					"bad",
					"internal error occurred"
			);
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	// Other
	@GetMapping(DEFAULT_POST_URI)
	public ResponseEntity<ResponseObject<?>> getAllPosts() {
		response = new ResponseObject<>(HttpStatus.OK.value(), "ok", postService.fetchAll());

		return ResponseEntity.ok(response);
	}

}
