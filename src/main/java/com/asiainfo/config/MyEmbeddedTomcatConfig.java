package com.asiainfo.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * spb内嵌servlet容器（默认内嵌tomcat）配置修改
 * 修改spb内嵌的tomcat配置有两种方式：
 * 1、通过全局配置文件application.properties(yml)指定某些属性进行修改
 * 2、通过配置类编码进行修改，如果配置类里面的属性和全局配置文件里面的属性冲突那么配置类的优先级高
 * 说白了就是将tomcat安装路径/conf下的配置全部用定制器（Customizer）编码实现
 * 
 * @author zhangzhiwang
 * @date Jun 15, 2020 11:59:03 AM
 */
//@Configuration
public class MyEmbeddedTomcatConfig {
	/**
	 * 内嵌Servlet容器定制器，用于修改内嵌容器的配置
	 * 
	 * @return
	 * @author zhangzhiwang
	 * @date Jun 15, 2020 12:02:47 PM
	 */
	@Bean
	public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer(){
		return new EmbeddedServletContainerCustomizer() {

			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				// TODO Auto-generated method stub
				container.setPort(8081);
				container.setContextPath("/zzw");
			}};
	}
}
