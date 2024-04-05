package com.portal.jobconnect;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.portal.jobconnect.components.Profile;
import com.portal.jobconnect.components.ResponseObject;
import com.portal.jobconnect.posts.Posts;
import com.portal.jobconnect.profile.employee.Employee;
import com.portal.jobconnect.profile.employer.Employer;

@RestController
@SpringBootApplication
public class JobconnectApplication<T> {

	private final String DEFAULT_URI = "/v1";
	private final String DEFAULT_EMPLOYER_URI = DEFAULT_URI + "/employer";
	private final String DEFAULT_EMPLOYEE_URI = DEFAULT_URI + "/employee";

	// Initializing the Employer & Employee Class
	private final Employer employer;
	public final Employee employee;
	private ResponseObject<?> response;
	private final Profile profile;

	public JobconnectApplication(Employer employer, Employee employee, ResponseObject<?> response, Profile profile) {
		this.employer = employer;
		this.employee = employee;
		this.response = response;
		this.profile = profile;
	}

	public static void main(String[] args) {
		SpringApplication.run(JobconnectApplication.class, args);
	}

	@Component
	public static class ServerPortListener implements ApplicationListener<WebServerInitializedEvent> {

		@Value("${server.port}")
		private String serverPort;

		@Override
		public void onApplicationEvent(@NonNull WebServerInitializedEvent event) {
			System.out.println("Application Loaded at http://localhost:" + serverPort);
		}
	}

	@GetMapping("/")
	public RedirectView redirectView() {
		return new RedirectView(DEFAULT_URI);
	}

	@GetMapping(DEFAULT_EMPLOYEE_URI)
	public RedirectView redirectEmployeeView() {
		return new RedirectView(DEFAULT_URI);
	}

	@GetMapping(DEFAULT_EMPLOYER_URI)
	public RedirectView redirectEmployerView() {
		return new RedirectView(DEFAULT_URI);
	}

	@GetMapping(DEFAULT_URI)
	public String version() {
		return "0.0.1";
	}

	// Account Creation
	// Employer
	@PostMapping(DEFAULT_EMPLOYER_URI + "/create")
	public ResponseEntity<ResponseObject<?>> createProfileEmployers(String accountType,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String organizationName) {
		try {
			if (accountType.isEmpty() || name.isEmpty() || organizationName.isEmpty()) {
				response = new ResponseObject<String>(HttpStatus.BAD_REQUEST.value(), "bad", "can't be empty");
				return ResponseEntity.badRequest().body(response);
			}
			if (accountType.toLowerCase().equals("employer")) {
				UUID uuid = UUID.randomUUID();
				employer.createProfile(uuid.toString(), accountType, name, organizationName);
			} else {
				response = new ResponseObject<String>(HttpStatus.BAD_REQUEST.value(), "bad",
						accountType + " account type not supported");
				return ResponseEntity.badRequest().body(response);
			}
			response = new ResponseObject<Profile>(HttpStatus.OK.value(), "ok", profile);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response = new ResponseObject<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "bad",
					"Some Error Occured");
			System.out.println("Error occured at " + e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	// Employee
	// @PostMapping(DEFAULT_EMPLOYEE_URI + "/create")
	// public ResponseEntity<ResponseObject<?>> createProfileEmployers(String accountType,
	// 		@RequestParam(required = false) String name,
	// 		@RequestParam(required = false) int age,
	// 		@RequestParam(required = false) String expertise,
	// 		@RequestParam(required = false) int experience) {
	// 	try {
	// 		if (accountType.isEmpty() || name.isEmpty() || expertise.isEmpty()) {
	// 			response = new ResponseObject<String>(HttpStatus.BAD_REQUEST.value(), "bad", "can't be empty");
	// 			return ResponseEntity.badRequest().body(response);
	// 		}
	// 		if (accountType.toLowerCase().equals("employee")) {
	// 			profile = new Profile(accountType, name, age, expertise, experience);
	// 		} else {
	// 			response = new ResponseObject<String>(HttpStatus.BAD_REQUEST.value(), "bad",
	// 					accountType + " account type not supported");
	// 			return ResponseEntity.badRequest().body(response);
	// 		}
	// 		response = new ResponseObject<Profile>(HttpStatus.OK.value(), "ok", profile);
	// 		return ResponseEntity.ok(response);
	// 	} catch (Exception e) {
	// 		response = new ResponseObject<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "bad",
	// 				"Some Error Occured");
	// 		System.out.println("Error occured at " + e.getMessage());
	// 		return ResponseEntity.badRequest().body(response);
	// 	}
	// }

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

			response = new ResponseObject<Posts>(HttpStatus.OK.value(), "ok",
					employer.createPost(uuid.toString(), title, description, location));
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response = new ResponseObject<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "bad",
					"Error:\t" + e.getMessage());
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
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response = new ResponseObject<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "ok",
					"Error:\t" + e.getMessage());
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
			Posts result = employer.updatePost(employerId, title, description, location);
			response = (result == null)
					? new ResponseObject<Posts>(HttpStatus.BAD_REQUEST.value(), "bad", result)
					: new ResponseObject<Posts>(HttpStatus.OK.value(), "ok", result);

		} catch (Exception e) {
			response = new ResponseObject<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "ok",
					"Error:\t" + e.getMessage());
		}
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(DEFAULT_EMPLOYER_URI + "/post/delete")
	public ResponseEntity<ResponseObject<?>> employerDeletePost(@RequestParam String employerId) {
		try {
			response = new ResponseObject<Posts>(HttpStatus.OK.value(), "ok",
					employer.deletePost(employerId));
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response = new ResponseObject<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "bad",
					"Error:\t" + e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
}