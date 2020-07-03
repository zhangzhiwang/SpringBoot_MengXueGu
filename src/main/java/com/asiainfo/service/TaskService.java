package com.asiainfo.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 定时任务
 *
 * @author zhangzhiwang
 * @date Jun 29, 2020 10:43:08 AM
 */
//@Service
public class TaskService {
	private String name;

	public TaskService() {
		super();
	}

	public TaskService(String name) {
		super();
		this.name = name;
	}

	// @Scheduled(fixedRate = 1000)
	public void hello() {
		System.out.println("hello");
	}

	@Override
	public String toString() {
		return "TaskService [name=" + name + "]";
	}
}
