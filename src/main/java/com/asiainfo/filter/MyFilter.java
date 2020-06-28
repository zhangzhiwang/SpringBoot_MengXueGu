package com.asiainfo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 自定义过滤器
 *
 * @author zhangzhiwang
 * @date Jun 15, 2020 10:24:11 AM
 */
public class MyFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("MyFilter init...");
		String initParameter = filterConfig.getInitParameter("url");
		System.out.println("MyFilter.init() initParameter = " + initParameter);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("过滤器的主逻辑");
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("MyFilter destroy...");
	}

}
