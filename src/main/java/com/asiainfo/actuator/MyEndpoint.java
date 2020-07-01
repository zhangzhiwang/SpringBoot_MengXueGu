package com.asiainfo.actuator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;

/**
 * 自定义actuator的endpoint
 *
 * @author zhangzhiwang
 * @date Jul 1, 2020 3:31:07 PM
 */
@Endpoint(id = "myEndpoint")
public class MyEndpoint {
	@ReadOperation
	public Map<String, String> test() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "1");
		return map;
	}
}