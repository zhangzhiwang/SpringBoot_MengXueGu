package com.asiainfo.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.asiainfo.filter.MyFilter;
import com.asiainfo.listener.MyListener;
import com.asiainfo.servlet.MyServlet;

/**
 * servlet配置类——注册自定义的servlet三大组件
 *
 * @author zhangzhiwang
 * @date Jun 15, 2020 9:55:09 AM
 */
@Configuration
public class MyServletConfig {
	/**
	 * 注册servlet
	 * 相当于web.xml的以下配置：
	 * <servlet>
        <servlet-name>myServlet</servlet-name>
        <servlet-class>com.asiainfo.servlet.MyServlet</servlet-class>
        <init-param>
            <param-name>servletName</param-name>
            <param-value>aaa</param-value>
        </init-param>
    </servlet>
    <servlet-mapping>
        <servlet-name>myServlet</servlet-name>
        <url-pattern>/myServlet</url-pattern>
    </servlet-mapping>
	 * 
	 * @return
	 * @author zhangzhiwang
	 * @date Jun 15, 2020 10:20:02 AM
	 */
	@Bean
	public ServletRegistrationBean myServlet() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
		servletRegistrationBean.setServlet(new MyServlet());
		servletRegistrationBean.setUrlMappings(Arrays.asList("/myServlet"));
		servletRegistrationBean.setLoadOnStartup(2);
		Map<String, String> initParameters = new HashMap();
		initParameters.put("servletName", "aaa");
		servletRegistrationBean.setInitParameters(initParameters);
		return servletRegistrationBean;
	}
	
	/**
	 * 注册过滤器
	 * 相当于web.xml的以下配置：
	 * <filter>
			<filter-name>myFilter</filter-name>
			<filter-class>com.asiainfo.filter.MyFilter.MyFilter()</filter-class>
			<init-param>
				<param-name>url</param-name>
				<param-value>localhost</param-value>
			</init-param>
		</filter>
		<filter-mapping>
			<filter-name>myFilter</filter-name>
			<url-pattern>/*</url-pattern>
		</filter-mapping>
		
	 * @return
	 * @author zhangzhiwang
	 * @date Jun 15, 2020 10:27:21 AM
	 */
	@Bean
	public FilterRegistrationBean myFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new MyFilter());
		filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));// 奇怪：拦截器Interceptor要拦截嗦有请求的话UrlPatterns要写成/**，但是过滤器要拦截所有请求的话UrlPatterns要写成/*，写成/**不起作用，why？
		Map<String, String> initParameters = new HashMap();
		initParameters.put("url", "localhost");
		filterRegistrationBean.setInitParameters(initParameters);
		return filterRegistrationBean;
	}
	
	/**
	 * 注册监听器
	 * 相当于web.xml的以下配置：
	 * <listener>
			<listen-class>com.asiainfo.listener.MyListener.MyListener()</listen-class>
		</listener>
	 * 
	 * @return
	 * @author zhangzhiwang
	 * @date Jun 15, 2020 11:20:08 AM
	 */
	@Bean
	public ServletListenerRegistrationBean myListener() {
		ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
		servletListenerRegistrationBean.setListener(new MyListener());
		return servletListenerRegistrationBean;
	}
	
	/**
	 * 在Spring MVC中，web.xml必须配置DispatcherServlet，而在spb中DispatcherServlet默认已经配置好了，配置的方式详见DispatcherServletAutoConfiguration。</p>
	 * 其实spb就是把Spring MVC（以后简称mvc）的配置文件用代码的方式取代了，从而实现零配置。把一些约定的东西在标注为@Configuration的类里面写死了，如果你不按约定的来可以进行自定义，这就是“约定大于配置”。
	 */
}
