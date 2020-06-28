package com.asiainfo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登陆拦截器
 *
 * @author zhangzhiwang
 * @date Jun 10, 2020 7:19:36 PM
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("已进入拦截器");
		Object loginName = request.getSession().getAttribute("loginName");
		if(loginName == null) {// 没有登陆过
			// 转发
			// 重定向和转发的区别：重定向不可以带参数，转发可以带参数；重定向是浏览器的行为，即重定向会使浏览器发送的url地址发生变化，而转发是服务器的行为，转发不会使浏览器的url地址发生变化
			request.setAttribute("msg", "尚未登陆，请登录！");
			request.getRequestDispatcher("/helloController/notLogin").forward(request, response);
			return false;
		}
		
		// 已登陆，放行
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
