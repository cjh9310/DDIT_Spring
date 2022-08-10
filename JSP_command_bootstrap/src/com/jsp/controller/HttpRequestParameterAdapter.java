package com.jsp.controller;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

public class HttpRequestParameterAdapter {

	public static <T>T execute(HttpServletRequest request, Class<T> className) throws Exception {
		// Adapter 사용이유 => getset 쓰기 싫어서?
		
			// <T> = Class<T>  , T(와일드카드) = className
			//의존성 확인 및 조립
				Method[] methods = className.getMethods();

				//인스턴스 생성
				T obj = className.newInstance();
				
				//setter 확인
				for (Method method : methods) {                 // ex) setId를 가져올 경우 
					if (method.getName().indexOf("set") == 0) { // set이라는 이름을 가진 메소드가 있으면
						String requestParamName = method.getName().replace("set", ""); // set메소드에서 set을 지워주고 공백이 생김
						requestParamName = (requestParamName.charAt(0) + "") // 
								.toLowerCase() // Id에서  toLowerCase 로  I를 소문자로 바꿔줌 +
								+ requestParamName.substring(1); // I뒤에 있는 것들을 가져와서 합침

						String[] paramValues
								= request.getParameterValues(requestParamName);
						
						if (paramValues != null && paramValues.length > 0) {
							if (method.getParameterTypes()[0].isArray()) { //isArray 배열인지 확인
								//invoke가 parameter를 받을때 이렇게 배열을 만들어줘야만 받을 수 있다.   object안에 배열이라는 뜻
								method.invoke(obj, new Object[] { paramValues });
							} else {
								// 배열이 아니면 바로 String으로 넣어줌
								method.invoke(obj, paramValues[0]);
					} // invoke 실행한다. 맞다네요
				}
			}
		}
				return obj;
	}
}