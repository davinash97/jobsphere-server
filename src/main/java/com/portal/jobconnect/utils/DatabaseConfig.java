package com.portal.jobconnect.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings("ALL")
@Configuration
public class DatabaseConfig {

	@Value("${spring.datasource.url}")
	public String dbUrl;

	@Value("${spring.datasource.username}")
	public String dbUsername;

	@Value("${spring.datasource.password}")
	public String dbPassword;

}
