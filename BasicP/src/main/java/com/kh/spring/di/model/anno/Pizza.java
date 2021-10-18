package com.kh.spring.di.model.anno;

import org.springframework.stereotype.Component;

@Component
public class Pizza implements Food {

	@Override
	public void eat(String foodName) {
		System.out.println("한강에서 " + foodName + "피자를 먹는 중!~" );
	}
	
	

}
