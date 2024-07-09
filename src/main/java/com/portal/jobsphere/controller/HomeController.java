package com.portal.jobsphere.controller;

import com.portal.jobsphere.utils.Constants;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class HomeController implements Constants {

	@RequestMapping("/")
	public RedirectView redirectView() {
		return new RedirectView(DEFAULT_URI);
	}

	@RequestMapping(DEFAULT_URI)
	public String version() {
		return VERSION;
	}

	@RequestMapping(DEFAULT_EMPLOYEE_URI)
	public RedirectView redirectEmployeeView() {
		return new RedirectView(DEFAULT_URI);
	}

	@RequestMapping(DEFAULT_EMPLOYER_URI)
	public RedirectView redirectEmployerView() {
		return new RedirectView(DEFAULT_URI);
	}
}
