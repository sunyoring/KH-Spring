package com.kh.spring.di.model.anno;

import org.springframework.stereotype.Component;

@Component
public interface Food {

	public void eat(String foodName);
}
