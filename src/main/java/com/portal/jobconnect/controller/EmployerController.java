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

import com.portal.jobconnect.application.ServerPortLister;
import com.portal.jobconnect.model.Posts;
import com.portal.jobconnect.model.ResponseObject;
import com.portal.jobconnect.service.EmployerService;
import com.portal.jobconnect.utils.Constants;

@RestController
public class EmployerController implements Constants {

	private final EmployerService employer = new EmployerService();

	private static final Logger logger = LoggerFactory.getLogger(ServerPortLister.class);

	private ResponseObject<?> response;

	// Posts
	@PostMapping(DEFAULT_EMPLOYER_URI + "/post/create")
	public ResponseEntity<ResponseObject<?>> employerCreatePost(
			@RequestParam String title,
			@RequestParam String description,
			@RequestParam String location) {
		try {
			if (title.isEmpty() || description.isEmpty() || location.isEmpty()) {
				response = new ResponseObject<String>(HttpStatus.BAD_REQUEST.value(), "bad", "Bad Request");
				return ResponseEntity.ok(response);
			}

			UUID uuid = UUID.randomUUID();

			if (employer.createPost(uuid.toString(), title, description, location)) {
				logger.info("Successfully post created for Id:\t" + uuid.toString());
			}
			return employerReadPost(uuid.toString());
		} catch (Exception e) {
			response = new ResponseObject<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "bad",
					"internal error occured");
			logger.error("Error: ", e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@GetMapping(DEFAULT_EMPLOYER_URI + "/post/read")
	public ResponseEntity<ResponseObject<?>> employerReadPost(String employerId) {
		try {
			if (employerId.length() != 36) {
				response = new ResponseObject<String>(HttpStatus.BAD_REQUEST.value(), "bad", "invalid id");
				return ResponseEntity.badRequest().body(response);
			}
			Posts result = employer.readPost(employerId);
			response = (result == null)
					? new ResponseObject<Posts>(HttpStatus.BAD_REQUEST.value(), "bad", result)
					: new ResponseObject<Posts>(HttpStatus.OK.value(), "ok", result);
			logger.info(response.toString());
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response = new ResponseObject<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "ok",
					"internal error occured");
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@PutMapping(DEFAULT_EMPLOYER_URI + "/post/update")
	public ResponseEntity<ResponseObject<?>> employerUpdatePost(
			@RequestParam String employerId,
			@RequestParam(required = false) String title,
			@RequestParam(required = false) String description,
			@RequestParam(required = false) String location) {

		try {
			if (title == null && description == null && location == null) {
				response = new ResponseObject<String>(HttpStatus.BAD_REQUEST.value(), "bad",
						"At least one of title, description, or location must be provided.");
				return ResponseEntity.badRequest().body(response);
			}

			if (!employer.updatePost(employerId, title, description, location)) {
				response = new ResponseObject<String>(HttpStatus.BAD_REQUEST.value(), "bad", "bad request");
				return ResponseEntity.badRequest().body(response);
			}
			logger.info(response.toString());
			return employerReadPost(employerId);
		} catch (Exception e) {
			response = new ResponseObject<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "ok",
					"Error:\t" + "internal error occured");
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@DeleteMapping(DEFAULT_EMPLOYER_URI + "/post/delete")
	public ResponseEntity<ResponseObject<?>> employerDeletePost(@RequestParam String employerId) {
		try {
			response = new ResponseObject<Boolean>(HttpStatus.OK.value(), "ok",
					employer.deletePost(employerId));
			logger.info(response.toString());
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response = new ResponseObject<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "bad",
					"internal error occured");
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

}
