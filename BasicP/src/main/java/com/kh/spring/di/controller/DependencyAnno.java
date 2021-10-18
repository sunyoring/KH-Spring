package com.kh.spring.di.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.kh.spring.di.model.anno.Person2;

/**
 * Servlet implementation class DependencyAnno
 */
@WebServlet("/annoDI.do")
public class DependencyAnno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DependencyAnno() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*어노테이션을 활용한 객체 의존성 주입
		 * 
		 * 어노테이션이 붙은 클래스를 탐색하여 컨테이너에 자동으로 등록(컴포넌트 스캔)
		 * 
		 * @Component (클래스) , @Bean(메소드 , @Configuration도 함께 사용) 
		 * 
		 * */
		
/*		//자바 코드로 만들어진 BeanFactory.class로 생성
		AbstractApplicationContext cntx = new AnnotationConfigApplicationContext(BeanFactory.class);
		
//		getBean("메소드이름")
		Person p1 = (Person)cntx.getBean("createPerson");
		p1.getMyFood().eat("태리");
		System.out.println(p1);
		Person p2 = (Person)cntx.getBean("createPerson2");
		p2.getMyFood().eat("태리");
		System.out.println(p2);
		
*/
		
		AbstractApplicationContext cntx = new GenericXmlApplicationContext("/di-anno-context.xml");

		Person2 p = (Person2)cntx.getBean("person2");
		p.getMyFood().eat("반올림");
		
		System.out.println(p);
		
		System.out.println(cntx.isActive());
		cntx.close();
		System.out.println(cntx.isActive());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
