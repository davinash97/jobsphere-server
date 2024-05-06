package com.portal.jobconnect.utils;

import java.time.format.DateTimeFormatter;

public interface Constants {
	String VERSION = "0.0.1";
	String DEFAULT_URI = "/v1";
	String DEFAULT_PROFILE_URI = DEFAULT_URI + "/profile";
	String DEFAULT_POST_URI = DEFAULT_URI + "/post";
	String DEFAULT_EMPLOYER_URI = DEFAULT_URI + "/employer";
	String DEFAULT_EMPLOYEE_URI = DEFAULT_URI + "/employee";

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
}
