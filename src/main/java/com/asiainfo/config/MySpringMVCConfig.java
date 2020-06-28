package com.asiainfo.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.asiainfo.interceptor.LoginInterceptor;

/**
 * 自定义Spring MVC控制类
 *
 * @author zhangzhiwang
 * @date Jun 11, 2020 1:12:20 PM
 */
//@Configuration // 标明该类是一个配置类，相当于MVC中的xml配置文件
@Import({USConfig.class})// 导入另一个配置文件，相当于MVC中在一个xml文件中导入另一个xml文件，即<import/>标签
public class MySpringMVCConfig {
	@Bean
	public WebMvcConfigurer webMvcConfigurer() {
		return new MyWebMvcConfigurer();
	}

	static class MyWebMvcConfigurer implements WebMvcConfigurer {
		/**
		 * 相当于sping mvc中的配置：
		 * <mvc:interceptors>
        		<mvc:interceptor>
	            	<!-- 拦截所有的请求，这个必须写在前面，也就是写在【不拦截】的上面 -->
		            <mvc:mapping path="/**" />
		            <!-- 但是排除下面这些，也就是不拦截请求 -->
		            <mvc:exclude-mapping path="/helloController/test4" />
		            <mvc:exclude-mapping path="/helloController/test3" />
		            <mvc:exclude-mapping path="/helloController/notLogin" />
		            <bean class="com.asiainfo.interceptor.LoginInterceptor.LoginInterceptor()" />
        		</mvc:interceptor>
    	</mvc:interceptors>
		 */
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(new LoginInterceptor())
			.addPathPatterns("/**")// 指定要拦截哪些请求
			.excludePathPatterns("/helloController/login", "/helloController/test3", "/helloController/notLogin");// 不拦截哪些请求
		}

		@Override
		public void configurePathMatch(PathMatchConfigurer configurer) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addFormatters(FormatterRegistry registry) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addCorsMappings(CorsRegistry registry) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void configureViewResolvers(ViewResolverRegistry registry) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Validator getValidator() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MessageCodesResolver getMessageCodesResolver() {
			// TODO Auto-generated method stub
			return null;
		}
	}
}
