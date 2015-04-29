package com.hong.spring.modules.board.support;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.Subscribe;
import com.hong.spring.domains.Board;

@Component
public class BoardListener {

	@Autowired
	private AsyncEventBus asyncEventBus;

	@PostConstruct
	public void registerHandler() {
		asyncEventBus.register(this);
	}

	@PreDestroy
	public void unRegisterHandler() {
		asyncEventBus.unregister(this);
	}

	@Subscribe
	@AllowConcurrentEvents
	public void handlerBoard1(Board board) {
		System.out.println("보드 리스너1");
	}

	@Subscribe
	@AllowConcurrentEvents
	public void handlerBoard2(Board board) {
		System.out.println("보드 리스너2");
	}

}
