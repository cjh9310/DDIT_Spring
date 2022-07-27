package com.jsp.context;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext { // return 받은 container을 바로 아래의 container에 적용됨
	private static Map<String, Object> container = new HashMap<String, Object>(); // hashMap사용이유 키값만 있으면 쉽게 꺼내올 수 있어서
	
	private ApplicationContext() {}
	
	public static Map<String, Object> getApplicationContext(){
		return container;
	}
	
}

