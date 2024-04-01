package com.portal.jobconnect;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@SpringBootApplication
public class JobconnectApplication {

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
		return new RedirectView("/v1");
	}

	@GetMapping("/v1")
	public String version() {
		return "0.0.1";
	}
}
