package com.portal.jobsphere.controller;

import com.portal.jobsphere.utils.Constants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class HomeController implements Constants {

	@GetMapping("/")
	public RedirectView redirectView() {
		return new RedirectView(DEFAULT_URI);
	}

	@GetMapping(DEFAULT_URI)
	public String version() {
		return VERSION;
	}

	@GetMapping(DEFAULT_EMPLOYEE_URI)
	public RedirectView redirectEmployeeView() {
		return new RedirectView(DEFAULT_URI);
	}

	@GetMapping(DEFAULT_EMPLOYER_URI)
	public RedirectView redirectEmployerView() {
		return new RedirectView(DEFAULT_URI);
	}
}
