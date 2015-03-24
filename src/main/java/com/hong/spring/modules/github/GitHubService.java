package com.hong.spring.modules.github;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

import com.hong.spring.modules.github.model.GitHubUser;

@Component
public class GitHubService {

	private static final String USER_URL = "https://api.github.com/users/{user}";

	@Autowired
	private AsyncRestTemplate asyncRestTemplate;

	public ListenableFuture<ResponseEntity<GitHubUser>> user(String user) throws InterruptedException, ExecutionException {
		ListenableFuture<ResponseEntity<GitHubUser>> gitHubUser = asyncRestTemplate.getForEntity(USER_URL, GitHubUser.class, user);
		return gitHubUser;
	}

}
