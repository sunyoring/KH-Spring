package com.kh.spring.di.model.anno;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration  //해당 클래스를 스프링 설정 클래스로 지정하고 스프링 빈으로 등록 -> 내부적으로 @Component를 포함하고 있기때문에 컴포넌트 스캔의 대상이 된다. 
public class BeanFactory {

	@Bean	//메소드가 생성한 객체를 스프링이 관리하는 빈으로 등록
	public Person createPerson() {
		
		//객체를 생성하고 값을 담아주어 리턴해줌
		Person p2 = new Person();
		p2.setName("김선호");
		
		Food myfood = new Chicken();
		p2.setMyFood(myfood);

	
		return p2;	
	}
	
	@Bean
	public Person createPerson2() {
		Food myfood = new Pizza();
		return new Person("로운",myfood);
	}
}
