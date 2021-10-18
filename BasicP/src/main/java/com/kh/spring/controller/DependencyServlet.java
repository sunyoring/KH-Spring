package com.kh.spring.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.kh.spring.di.model.vo.Singer;
import com.kh.spring.di.model.vo.SingerMgt;

/**
 * Servlet implementation class DependencyServlet
 */
@WebServlet("/dependency.do")
public class DependencyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DependencyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* IOC & DI
		 * 
		 * 개발자가 직접 new하여 객체를 생성하던 방식에서 개발자의 부담을 덜어주며, 보다 편리하게 개발이 가능하도록 
		 * 객체를 생성, 수정, 삭제(소멸)을 스프링이 대신 할 수 있도록 구현한 기술
		 * */
		/*
		
		//1. 일반 클래스 생성 - 버전1 에서 버전2로 내용을 변경해야 하는 경우가 생김, 이를 결합도가 높다고 표현하며  유지보수가 어렵다.
						// 개발자가 new를 통해 직접 생성
		
		// Twice singer = new Twice();
		
		BlackPink singer = new BlackPink();
		singer.compose();
		singer.sing();
		
		//2. 인터페이스 사용하여 결합도 낮추기  - 개발자가 new를 통해 직접 생성
		
		Singer singer2 = new BlackPink();
		singer2.compose();
		singer2.dance();
		singer2.sing();
		
		
		//3. 전략 디자인 패턴을 이용하여 결합도 낮추기 - 직접 new를 통해 생성하지 않았음
		
		Singer singer3 = (Singer)BeanFactory.getBean(request.getParameter("bean"));
		singer3.compose();
		singer3.dance();
		singer3.sing();
		
		
		*/
		
		//4. 스프링 컨테이너를 사용한 객체 생성하기 (xml방식) - 
		
		AbstractApplicationContext cntx = new GenericXmlApplicationContext("/sample-context.xml");
		
		Singer singer = (cntx.getBean("singerMgt",SingerMgt.class)).getSinger();
		
		/* 두 가지 방법 다 가능하지만 id값만 적을 때는 형변환을 해줘야 한
		 * Singer singer = (cntx.getBean(SingerMgt.class)).getSinger(); 
		 * Singer singer = (cntx.getBean("singerMgt")).getSinger();
		 */
		
		singer.compose();
		singer.dance();
		singer.sing();		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
