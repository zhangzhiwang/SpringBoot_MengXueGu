package com.asiainfo.config;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置
 *
 * @author zhangzhiwang
 * @date Jun 15, 2020 6:36:20 PM
 */
@Configuration
public class MyBatisConfig {
	@Bean
	public ConfigurationCustomizer configurationCustomizer() {
		return new ConfigurationCustomizer() {

			@Override
			public void customize(org.apache.ibatis.session.Configuration configuration) {
				// 设置数据库字段名下划线转驼峰
				configuration.setMapUnderscoreToCamelCase(true);
			}};
	}
}
