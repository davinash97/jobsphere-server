package com.portal.jobsphere.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unused")
public class ServerPortListener implements ApplicationListener<WebServerInitializedEvent> {
	private static final Logger logger = LoggerFactory.getLogger(ServerPortListener.class);

	@Value("${server.port}")
	private String serverPort;

	@Override
	public void onApplicationEvent(@NonNull WebServerInitializedEvent event) {
		logger.debug("Application Loaded at http://localhost:{}", serverPort);
	}
}
