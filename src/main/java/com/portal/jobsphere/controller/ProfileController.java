package com.portal.jobsphere.controller;

import com.portal.jobsphere.model.Profile;
import com.portal.jobsphere.model.ResponseObject;
import com.portal.jobsphere.service.ProfileService;
import com.portal.jobsphere.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@SuppressWarnings("ALL")
@RestController
public class ProfileController implements Constants {

	private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);

	@Autowired
	private ProfileService profileService;

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
					? new ResponseObject<>(HttpStatus.BAD_REQUEST.value(), "bad", "id doesn't exist")
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

			if (profileService.updateProfile(profileId, name, gender, role, email, phone, numOfPosts, numOfApplicants, organizationName)) {
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
