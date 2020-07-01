package com.asiainfo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.asiainfo.actuator.MyEndpoint;

@Configuration
public class MyEndpointConfig {
	
	@Bean
	public MyEndpoint myEndpoint() {
		return new MyEndpoint();
	}
}
