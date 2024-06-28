package com.portal.jobsphere.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.portal.jobsphere.exception.NotFound;
import com.portal.jobsphere.model.Profile;
import com.portal.jobsphere.model.ResponseObject;
import com.portal.jobsphere.service.ProfileService;
import com.portal.jobsphere.utils.Constants;

@RestController
public class ProfileController implements Constants {

	private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);

	@Autowired
	private ProfileService profileService;

	private final NotFound notFound = new NotFound();
	
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
			Profile profile = profileService.createProfile(name, gender, role, email, phone);
			if (profile != null) {
				logger.debug("Successfully profile created for Id:\t{}", profile.getProfileId());
				return readProfile(profile.getProfileId());
			}
			return null;

		} catch (Exception e) {
			response = new ResponseObject<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "bad",
					"internal error occurred");
			logger.error("Error occurred at: {}", e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@GetMapping(DEFAULT_PROFILE_URI + "/read")
	public ResponseEntity<ResponseObject<?>> readProfile(UUID profileId) {
		try {
			if (profileId.toString().length() != 36) {
				response = new ResponseObject<>(HttpStatus.BAD_REQUEST.value(), "bad", "invalid id");
				return ResponseEntity.badRequest().body(response);
			}

			Profile result = profileService.readProfile(profileId);
			response = (result == null)
					? notFound.profile(profileId)
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

	@PutMapping(DEFAULT_PROFILE_URI + "/update")
	public ResponseEntity<ResponseObject<?>> updateProfile(
			@RequestParam UUID profileId,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String first_name,
			@RequestParam(required = false) String last_name,
			@RequestParam(required = false) String role,
			@RequestParam(required = false) String gender,
			@RequestParam(required = false) String email,
			@RequestParam(required = false) Long phone,
			@RequestParam(required = false) Integer num_of_Posts,
			@RequestParam(required = false) Long num_of_applicants,
			@RequestParam(required = false) String organization) {

		try {
			if (name == null && role == null && gender == null && email == null && organization == null) {
				response = new ResponseObject<>(HttpStatus.BAD_REQUEST.value(), "bad",
						"At least one of name, role, gender, email, or organization must be provided.");
				return ResponseEntity.badRequest().body(response);
			}

			if (profileService.updateProfile(profileId, name, first_name, last_name, gender, role, email, phone, num_of_Posts, num_of_applicants, organization)) {
				logger.debug("Successfully profile updated for Id:\t{}", profileId);
			}

			return readProfile(profileId);
		} catch (Exception e) {
			response = new ResponseObject<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "bad",
					"internal error occurred");
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@DeleteMapping(DEFAULT_PROFILE_URI + "/delete")
	public ResponseEntity<ResponseObject<?>> deleteProfile(@RequestParam UUID profileId) {
		try {
			response = new ResponseObject<>(HttpStatus.OK.value(), "ok",
					profileService.deleteProfile(profileId));
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
