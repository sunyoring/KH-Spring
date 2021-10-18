package com.kh.spring.di.model.xml;

public class Person {

	private String name;
	private Food myFood;
	
	public Person() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Person(String name, Food myFood) {
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
