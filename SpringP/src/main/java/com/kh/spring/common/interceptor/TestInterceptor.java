package com.kh.spring.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ch.qos.logback.classic.Logger;

public class TestInterceptor extends HandlerInterceptorAdapter{
	// 뷰에서 요청 -->filter--> DispatcherServlet -- 인터셉터 --> Controller --> Service --> Dao --> DB
	//								        <-- 인터셉터 --  Controller <-- Service <-- Dao <-- DB

	
//	로그를 찍기위해 추가해야함
	private static final Logger logger = (Logger) LoggerFactory.getLogger(TestInterceptor.class);


//filter : 요청했을 때 전역에서 인코딩이나, 보안처리 등 같은 어떤 기능을 딱 한 번 처리해야할 때 실행
//인터셉터 : Controller에 요청하기 전 / 요청 처리후 반환하기 전 실행

	//DispatcherServlet : 이 컨트롤러를 호출하기 전에 수행
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.debug("=============preHandle==============");
		logger.debug(request.getRequestURI());
		return super.preHandle(request, response, handler);
	}

	// Controller에서 DispatcherServlet으로 리턴되는 순간에 실행
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.debug("=============postHandle==============");
		super.postHandle(request, response, handler, modelAndView);
	}

	// 최종 결과를 생성하는 일을 포함한 모든 작업이 완료된 후 리소스를 반환하기 전에 수행 (모든 작업이 끝난 후 ) 
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.debug("=============afterCompletion==============");
		super.afterCompletion(request, response, handler, ex);
	}

	
	
}
