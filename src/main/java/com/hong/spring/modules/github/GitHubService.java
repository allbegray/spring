package com.hong.spring.modules.github;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import com.hong.spring.modules.github.model.GitHubUser;
import com.hong.spring.modules.github.model.GitHubUserDetail;

@Component
public class GitHubService {

	private static final String USER_URL = "https://api.github.com/users/{user}";
	private static final String FOLLOWERS_URL = "https://api.github.com/users/{user}/followers";

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private AsyncRestTemplate asyncRestTemplate;

	public ResponseEntity<GitHubUser[]> followers(String user) throws InterruptedException, ExecutionException {
		return restTemplate.getForEntity(FOLLOWERS_URL, GitHubUser[].class, user);
	}

	public ListenableFuture<ResponseEntity<GitHubUserDetail>> user(String user) throws InterruptedException, ExecutionException {
		ListenableFuture<ResponseEntity<GitHubUserDetail>> gitHubUser = asyncRestTemplate.getForEntity(USER_URL, GitHubUserDetail.class, user);
		return gitHubUser;
	}

}
