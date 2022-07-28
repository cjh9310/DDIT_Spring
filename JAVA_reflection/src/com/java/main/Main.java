package com.java.main;

import java.lang.reflect.Method;

public class Main { // 이번 목적은 new를 쓰면 결합도가 높은데 결합도를 낮추는 방법에 대한 예시 및 설명

	public static void main(String[] args) {
		
//		TargetClass instance = new TargetClass();		
//		instance.a();
		
		try {
			Class<?> classType =  Class.forName(args[0]);
			// 모르지만 클래스타입이긴 하다는 뜻
			Method[] methods = classType.getMethods();
			// 메서드들이 객체화 되어서 오는 것
			if(methods!=null) for(Method method : methods){
				// 메서드가 null이 아니면 for문으로 돌림
				if(method.getName().equals("b")) {
					// 검증     이름이 String이라 equals를 사용
					Object instance = classType.newInstance();
					// 인스턴스를 받는 애는 여전히 타입을 모름 (일단 b 메소드를 실행하겠는데 인스턴스가 필요함)
					//  ㄴ equals("b") 자리에 getinstance를 적으면 메소드의이름의 인스턴스를 다 가져옴
					method.invoke(instance, null);
					// 해당 메소드를 실행할건데  invoke(첫번째는 실행할 해당 인스턴스, 두번째는 파라미터가 있으면 기재하고 없으면 null)
				}
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
