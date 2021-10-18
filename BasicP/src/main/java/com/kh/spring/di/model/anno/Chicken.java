package com.kh.spring.di.model.anno;

import org.springframework.stereotype.Component;

//@Primary  //여러개의 객체가 존재할 경우 우선순위로 지정해주는 어노테이션
@Component("chicken")  //id를 지정해 사용할 수 있음.
public class Chicken implements Food {

	@Override
	public void eat(String foodName) {

		System.out.println("집에서" + foodName + "치킨을 먹는 중!~");
	}

}
