package com.asiainfo.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 自定义监听器
 *
 * @author zhangzhiwang
 * @date Jun 15, 2020 10:56:09 AM
 */
public class MyListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		System.out.println("监听器初始化");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
