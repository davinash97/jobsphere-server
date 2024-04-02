package com.portal.jobconnect;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
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

import com.portal.jobconnect.UserAccounts.Employer.Employer;
import com.portal.jobconnect.UserAccounts.Employee.Employee;

@RestController
@SpringBootApplication
public class JobconnectApplication {

	private final String DEFAULT_URI = "/v1";
	private final String DEFAULT_EMPLOYER_URI = DEFAULT_URI + "/employer";
	private final String DEFAULT_EMPLOYEE_URI = DEFAULT_URI + "/employee";

	// Initializing the Employer & Employee Class
	private final Employer employer;
	private final Employee employee;

	public JobconnectApplication(Employer employer, Employee employee) {
		this.employer = employer;
		this.employee = employee;
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

	// for Employer

	@PostMapping(DEFAULT_EMPLOYER_URI + "/create")
	public String createEmployee(@RequestParam(required = true) String name,
			@RequestParam(required = true) String company) {
		try {
			if (name.isBlank() || name.isEmpty() || company.isEmpty() || company.isBlank()) {
				return "name & company needs arguments";
			} else {
				employer.setName(name);
				employer.setCompany(company);
				return "Success";
			}
		} catch (IllegalArgumentException e) {
			return "Some Error Occured, we'll get back to you!" + e.getMessage();
		} catch (Exception e) {
			return "An unexpected error occured: " + e.getMessage();
		}
	}

	@GetMapping(DEFAULT_EMPLOYER_URI + "/read")
	public String readEmployee(@RequestParam(required = true) String name) {
		try {
			if (name.isBlank() || name.isEmpty()) {
				return "name & company needs arguments";
			} else {
				// employer.getCompany(name);
				return "Success";
			}
		} catch (IllegalArgumentException e) {
			return "Some Error Occured, we'll get back to you!" + e.getMessage();
		} catch (Exception e) {
			return "An unexpected error occured: " + e.getMessage();
		}
	}

	// Name
	// @PostMapping(DEFAULT_EMPLOYER_URI + "/name/set")
	// public String setEmployerName(@RequestParam(required = true) String name) {
	// try {
	// if (name.isBlank() || name.isEmpty()) {
	// return "Name cannot be Empty";
	// } else {
	// employer.setName(name);
	// return "Success";
	// }
	// } catch (IllegalArgumentException e) {
	// return "Some Error Occured, we'll get back to you!" + e.getMessage();
	// } catch (Exception e) {
	// return "An unexpected error occured: " + e.getMessage();
	// }
	// }

	// @GetMapping(DEFAULT_EMPLOYER_URI + "/name/get")
	// public String getEmployerName() {
	// return employer.getName();
	// }

	// Company
	@PostMapping(DEFAULT_EMPLOYER_URI + "/company/set")
	public String setEmployerCompany(@RequestParam(required = true) String company) {
		try {
			if (company.isBlank() || company.isEmpty()) {
				return "This field cannot be empty";
			} else {
				employer.setCompany(company);
				return "Success";
			}
		} catch (IllegalArgumentException e) {
			return "Some Error Occured, we'll get back to you!" + e.getMessage();
		} catch (Exception e) {
			return "An unexpected error occured: " + e.getMessage();
		}
	}

	@GetMapping(DEFAULT_EMPLOYER_URI + "/company/get")
	public String getEmployerCompany() {
		return employer.getName();
	}

	// For Employees

	// Name
	@PostMapping(DEFAULT_EMPLOYEE_URI + "/name/set")
	public String setEmployeeName(@RequestParam(required = true) String name) {
		try {
			if (name.isBlank() || name.isEmpty()) {
				return "Name cannot be Empty";
			} else {
				employee.setName(name);
				return "Success";
			}
		} catch (IllegalArgumentException e) {
			return "Some Error Occured, we'll get back to you!" + e.getMessage();
		} catch (Exception e) {
			return "An unexpected error occured: " + e.getMessage();
		}
	}

	@GetMapping(DEFAULT_EMPLOYEE_URI + "/name/get")
	public String getEmployeeName() {
		return employee.getName();
	}

	// Expertise
	@PostMapping(DEFAULT_EMPLOYEE_URI + "/expertise/set")
	public String setEmployeeExpertise(@RequestParam(required = true) String expertise) {
		try {
			if (expertise.isBlank() || expertise.isEmpty()) {
				return "This field cannot be empty";
			} else {
				employee.setExpertise(expertise);
				return "Success";
			}
		} catch (IllegalArgumentException e) {
			return "Some Error Occured, we'll get back to you!" + e.getMessage();
		} catch (Exception e) {
			return "An unexpected error occured: " + e.getMessage();
		}
	}

	@GetMapping(DEFAULT_EMPLOYEE_URI + "/expertise/get")
	public String getEmployeeExpertise() {
		return employee.getExpertise();
	}

	// Experience
	@PostMapping(DEFAULT_EMPLOYEE_URI + "/experience/set")
	public String setEmployeeExperience(@RequestParam(required = true) String expertise) {
		try {
			if (expertise.isBlank() || expertise.isEmpty()) {
				return "This field cannot be empty";
			} else {
				employee.setExperience(Integer.parseInt(expertise));
				return "Success";
			}
		} catch (IllegalArgumentException e) {
			return "Some Error Occured, we'll get back to you!" + e.getMessage();
		} catch (Exception e) {
			return "An unexpected error occured: " + e.getMessage();
		}
	}

	@GetMapping(DEFAULT_EMPLOYEE_URI + "/experience/get")
	public String getEmployeeExperience() {
		return Integer.toString(employee.getExperience());
	}

	// Posts
	@PostMapping(DEFAULT_EMPLOYER_URI + "/post/create")
	public ResponseEntity<String> employerCreatePost(
			@RequestParam(required = true) String title,
			@RequestParam(required = true) String description,
			@RequestParam(required = true) String location) {
		UUID uuid = UUID.randomUUID();
		employer.createPost(uuid.toString(), title, description, location);
		return ResponseEntity.ok("Success");
	}

	@GetMapping(DEFAULT_EMPLOYER_URI + "/post/read")
	public ResponseEntity<String> employerReadPost(String employerId) {
		String result = employer.readPost(employerId);
		System.out.println(result);
		return ResponseEntity.ok(result);
	}

	@PutMapping(DEFAULT_EMPLOYER_URI + "/post/update")
	public ResponseEntity<String> employerUpdatePost(
			@RequestParam(required = true) String employerId,
			@DefaultValue(value = "") String title,
			@DefaultValue(value = "") String description,
			@DefaultValue(value = "") String location) {
		employer.updatePost(employerId, title, description, location);
		return ResponseEntity.ok("Success");
	}

	@DeleteMapping(DEFAULT_EMPLOYER_URI + "/post/delete")
	public ResponseEntity<String> employerDeletePost(@RequestParam(required = true) String employerId) {
		try{
			employer.deletePost(employerId);
		} catch (Exception e) {
			return ResponseEntity.ok(e.getMessage());
		}
		return ResponseEntity.ok("Success");
	}
}