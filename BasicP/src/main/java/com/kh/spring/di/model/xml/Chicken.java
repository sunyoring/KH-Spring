package com.kh.spring.di.model.xml;

public class Chicken implements Food {

	@Override
	public void eat(String foodName) {

		System.out.println("집에서" + foodName + "치킨을 먹는 중!~");
	}

}
