package com.kh.spring.di.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.kh.spring.di.model.xml.Food;
import com.kh.spring.di.model.xml.Person;

/**
 * Servlet implementation class DependencyXML
 */
@WebServlet("/xmlDI.do")
public class DependencyXML extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DependencyXML() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* 객체 의존성 : 객체를 생성할 때 new로 생성하는 것 뿐 아니라 메소드를 사용하거나 어떠한 방법으로든 객체의 정보를 전달 받는 것을 모두 포함
		 * 
		 * 1. 생성자를 통한 의존성 주입법
		 * 		Person p = new Person("김선호","차차차")
		 * 2. setter를 통한 의존성 주입법
		 * 		p.setName("김선호");
		 * 3. 다른 메소드를 통한 의존성 주입법
		 * 		Connection con = getConnection();
		 * */
	
		AbstractApplicationContext cntx = new GenericXmlApplicationContext("/di-xml-context.xml");
		
		Person p1 = (Person)(cntx.getBean("person1"));
		System.out.println("p1 : " + p1);
		p1.getMyFood().eat("푸라닭");
		
		Person p2 = (Person)(cntx.getBean("person2"));
		System.out.println("p2 : " + p2);
		p2.getMyFood().eat("굽네");
		
		Person p3 = (Person)(cntx.getBean("person1"));
		System.out.println("p3 : " + p3);
		p3.getMyFood().eat("보드람");
		
		Person p4 = (Person)(cntx.getBean("person2"));
		System.out.println("p4 : " + p4);
		p4.getMyFood().eat("반올림");

		
		//		Food food = (cntx.getBean("person1",Person.class)).getMyFood();		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
