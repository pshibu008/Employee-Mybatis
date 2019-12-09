package com.shivsashi.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class EmployeeInterceptor implements HandlerInterceptor
{
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	{
		String user = (String) request.getAttribute("user");
		return user == null;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
	{
		LOGGER.info("POST HANDLE");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
	{
		LOGGER.info("AFTER COMPLETION");
	}

}
