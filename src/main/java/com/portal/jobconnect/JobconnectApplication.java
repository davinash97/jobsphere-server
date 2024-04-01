package com.portal.jobconnect;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping(DEFAULT_URI)
	public String version() {
		return "0.0.1";
	}

	// for Employer

	// Name
	@GetMapping(DEFAULT_EMPLOYER_URI + "/name/set")
	public String setEmployerName(@RequestParam(required = true) String name) {
		try {
			if (name.isBlank() || name.isEmpty()) {
				return "Name cannot be Empty";
			} else {
				employer.setName(name);
				return "Success";
			}
		} catch (IllegalArgumentException e) {
			return "Some Error Occured, we'll get back to you!" + e.getMessage();
		} catch (Exception e) {
			return "An unexpected error occured: " + e.getMessage();
		}
	}

	@GetMapping(DEFAULT_EMPLOYER_URI + "/name/get")
	public String getEmployerName() {
		return employer.getName();
	}

	// Company
	@GetMapping(DEFAULT_EMPLOYER_URI + "/company/set")
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
	@GetMapping(DEFAULT_EMPLOYEE_URI + "/name/set")
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
	@GetMapping(DEFAULT_EMPLOYEE_URI + "/expertise/set")
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
	@GetMapping(DEFAULT_EMPLOYEE_URI + "/experience/set")
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
}
