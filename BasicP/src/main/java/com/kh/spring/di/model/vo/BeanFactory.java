package com.kh.spring.di.model.vo;

public class BeanFactory {
	
	public static Object getBean(String name) {
		
		Singer singer = null;
		if(name.equals("twice")) {
			singer = new Twice();
		}else {
			singer = new BlackPink();
		}
		
		return singer;
	}

}
