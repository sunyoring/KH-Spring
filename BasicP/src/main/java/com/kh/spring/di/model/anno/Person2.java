package com.kh.spring.di.model.anno;

import javax.inject.Qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Person2 {
	
	@Value("유야호") //값을 바로 넣을 수 있는 필드타입에는 @Value로 바로 넣을 수 있음.
	private String name;
	
	@Autowired  //필드타입을 기준으로 bean을 찾아서 값을 자동으로 넣어줌.
	private Food myFood;
	
	public Person2() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Person2(String name, Food myFood) {
		super();
		this.name = name;
		this.myFood = myFood;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Food getMyFood() {
		return myFood;
	}
	public void setMyFood(Food myFood) {
		this.myFood = myFood;
	}
	
	@Override
	public String toString() {
		return name + " ~~ " + myFood;
	}

}
