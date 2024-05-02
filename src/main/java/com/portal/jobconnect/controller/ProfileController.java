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

import com.portal.jobconnect.model.Profile;
import com.portal.jobconnect.model.ResponseObject;
import com.portal.jobconnect.service.ProfileService;
import com.portal.jobconnect.utils.Constants;

@RestController
public class ProfileController implements Constants {

	private final ProfileService profile = new ProfileService();

	private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);

	private ResponseObject<?> response;

	@PostMapping(DEFAULT_PROFILE_URI + "/create")
	public ResponseEntity<ResponseObject<?>> createProfile(
			@RequestParam String name,
			@RequestParam(required = false, defaultValue = "SEEKER") String role,
			@RequestParam(required = false, defaultValue = "UNDEFINED") String gender,
			@RequestParam(required = false) String email,
			@RequestParam(required = false) Long phone) {
		try {
			if (name.isEmpty() || role.isEmpty() || gender.isEmpty()) {
				response = new ResponseObject<>(HttpStatus.BAD_REQUEST.value(), "bad", "bad request");
				return ResponseEntity.ok(response);
			}

			UUID profileId = UUID.randomUUID();

			if (profile.createProfile(profileId.toString(), name, gender, role, email, phone)) {
                logger.info("Successfully profile created for Id:\t{}", profileId);
			}

			return readProfile(profileId.toString());
		} catch (Exception e) {
			response = new ResponseObject<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "bad",
					"internal error occurred");
			logger.error("Error occurred at: {}", e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@GetMapping(DEFAULT_PROFILE_URI + "/read")
	public ResponseEntity<ResponseObject<?>> readProfile(String profileId) {
		try {
			if (profileId.length() != 36) {
				response = new ResponseObject<>(HttpStatus.BAD_REQUEST.value(), "bad", "invalid id");
				return ResponseEntity.badRequest().body(response);
			}

			Profile result = profile.readProfile(profileId);
			response = (result == null)
					? new ResponseObject<>(HttpStatus.BAD_REQUEST.value(), "bad", "id doesn't exist")
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

	@PutMapping(DEFAULT_PROFILE_URI + "/update")
	public ResponseEntity<ResponseObject<?>> updateProfile(
			@RequestParam String profileId,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String role,
			@RequestParam(required = false) String gender,
			@RequestParam(required = false) String email,
			@RequestParam(required = false) Long phone,
			@RequestParam(required = false) Integer numOfPosts,
			@RequestParam(required = false) Long numOfApplicants,
			@RequestParam(required = false) String organizationName) {

		try {
			if (name == null && role == null && gender == null && email == null && organizationName == null) {
				response = new ResponseObject<>(HttpStatus.BAD_REQUEST.value(), "bad",
						"At least one of name, role, gender, email, or organization must be provided.");
				return ResponseEntity.badRequest().body(response);
			}	

			profile.updateProfile(profileId, name, gender, role, email, phone, numOfPosts, numOfApplicants,
					organizationName);

			return readProfile(profileId);
		} catch (Exception e) {
			response = new ResponseObject<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "bad",
					"internal error occurred");
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@DeleteMapping(DEFAULT_PROFILE_URI + "/delete")
	public ResponseEntity<ResponseObject<?>> deleteProfile(@RequestParam String profileId) {
		try {
			response = new ResponseObject<>(HttpStatus.OK.value(), "ok",
					profile.deleteProfile(profileId));
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
