package com.kh.spring.di.model.vo;

public class BlackPink implements Singer{
	
	public void sing() {
		System.out.println("노래하는 제니");
	}
	
	public void compose() {
		System.out.println("작곡하는 제니");
	}
	public void dance() {
		System.out.println("춤추는 제니");
	}

}
