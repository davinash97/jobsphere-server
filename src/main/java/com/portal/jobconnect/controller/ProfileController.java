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
import com.portal.jobconnect.model.Profile;
import com.portal.jobconnect.model.ResponseObject;
import com.portal.jobconnect.service.ProfileService;
import com.portal.jobconnect.utils.Constants;

@RestController
public class ProfileController implements Constants {

	private final ProfileService profile = new ProfileService();

	private static final Logger logger = LoggerFactory.getLogger(ServerPortLister.class);

	private ResponseObject<?> response;

	@PostMapping(DEFAULT_PROFILE_URI + "/create")
	public ResponseEntity<ResponseObject<?>> createProfile(
			@RequestParam String name,
			@RequestParam(required = false) String accountType,
			@RequestParam(required = false) String email,
			@RequestParam(required = false) long phone) {
		try {
			if (name.isEmpty() || accountType.isEmpty()) {
				response = new ResponseObject<String>(HttpStatus.BAD_REQUEST.value(), "bad", "Bad Request");
				return ResponseEntity.ok(response);
			}

			UUID profileId = UUID.randomUUID();

			if (profile.createProfile(profileId.toString(), name, accountType, email, phone)) {
				logger.info("Successfully profile created for Id:\t" + profileId.toString());
			}

			return readProfile(profileId.toString());
		} catch (Exception e) {
			response = new ResponseObject<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "bad",
					"internal error occured");
			logger.error("Error: ", e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@GetMapping(DEFAULT_PROFILE_URI + "/read")
	public ResponseEntity<ResponseObject<?>> readProfile(String profileId) {
		try {
			if (profileId.length() != 36) {
				response = new ResponseObject<String>(HttpStatus.BAD_REQUEST.value(), "bad", "invalid id");
				return ResponseEntity.badRequest().body(response);
			}
			Profile result = profile.readProfile(profileId);
			response = (result == null)
					? new ResponseObject<Profile>(HttpStatus.BAD_REQUEST.value(), "bad", result)
					: new ResponseObject<Profile>(HttpStatus.OK.value(), "ok", result);
			logger.info(response.toString());
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response = new ResponseObject<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "ok",
					"internal error occured");
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@PutMapping(DEFAULT_PROFILE_URI + "/update")
	public ResponseEntity<ResponseObject<?>> updateProfile(
			String profileId,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String accountType,
			@RequestParam(required = false) String email,
			@RequestParam(required = false) long phone,
			@RequestParam(required = false) int numOfPosts,
			@RequestParam(required = false) long numOfApplicants,
			@RequestParam(required = false) String organizationName) {

		try {
			if (!profile.updateProfile(profileId, name, accountType, email, phone, numOfPosts, numOfApplicants,
					organizationName)) {
				response = new ResponseObject<String>(HttpStatus.BAD_REQUEST.value(), "bad", "bad request");
				return ResponseEntity.badRequest().body(response);
			}
			logger.info(response.toString());
			return readProfile(profileId);
		} catch (Exception e) {
			response = new ResponseObject<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "ok",
					"Error:\t" + "internal error occured");
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@DeleteMapping(DEFAULT_PROFILE_URI + "/delete")
	public ResponseEntity<ResponseObject<?>> deleteProfile(@RequestParam String profileId) {
		try {
			response = new ResponseObject<Boolean>(HttpStatus.OK.value(), "ok",
					profile.deleteProfile(profileId));
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
