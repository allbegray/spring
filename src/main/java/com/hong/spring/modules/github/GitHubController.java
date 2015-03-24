package com.hong.spring.modules.github;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.async.DeferredResult;

import com.hong.spring.modules.github.model.GitHubUser;

@Controller
@RequestMapping("/github")
public class GitHubController {

	@Autowired
	private GitHubService gitHubService;

	@RequestMapping("/users/{user}")
	public DeferredResult<ResponseEntity<?>> user(@PathVariable String user) throws InterruptedException, ExecutionException {

		DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();

		ListenableFuture<ResponseEntity<GitHubUser>> future = gitHubService.user(user);
		future.addCallback(new ListenableFutureCallback<ResponseEntity<GitHubUser>>() {

			@Override
			public void onFailure(Throwable t) {
				ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
				deferredResult.setResult(responseEntity);
			}

			@Override
			public void onSuccess(ResponseEntity<GitHubUser> result) {
				ResponseEntity<GitHubUser> responseEntity = new ResponseEntity<>(result.getBody(), HttpStatus.OK);
				deferredResult.setResult(responseEntity);
			}

		});

		return deferredResult;

	}

}
