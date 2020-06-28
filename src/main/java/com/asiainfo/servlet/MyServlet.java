package com.asiainfo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义servlet</p>
 * 注册servlet三大组件：Servlet、Filter、Listener。传统的Spring MVC依赖外部的中间件才能启动运行，所以在Spring MVC里面注册三大组件需要在web.xml里面进行配置注册，</p>
 * 而spb是内嵌的tomcat容器，没有web.xml，所以spb提供了代码配置的方式来注册自定义的三大组件，效果等同于web.xml配置。
 *
 * @author zhangzhiwang
 * @date Jun 15, 2020 9:23:41 AM
 */
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doGet业务逻辑");
		String initParameter = getServletConfig().getInitParameter("servletName");
		System.out.println("initParameter = " + initParameter);
		response.getWriter().write("MyServlet.doGet()");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 所有doPost请求全部转到doGet上面
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
