package com.portal.jobsphere.executor;

import com.portal.jobsphere.service.PostService;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class Execute {
	private final PostService postService;
	private final ExecutorService executorService;

	public Execute(PostService postService, ExecutorService executorService) {
		this.postService = postService;
		this.executorService = executorService;
	}

	@EventListener(ContextRefreshedEvent.class)
	public void executeFetchAll() {
		try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
				executor.submit( () -> postService.fetchAll());
		}
	}

}
