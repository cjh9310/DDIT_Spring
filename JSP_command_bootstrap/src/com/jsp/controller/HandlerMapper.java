package com.jsp.controller;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import com.jsp.action.Action;
import com.jsp.context.ApplicationContext;

public class HandlerMapper {
	
	private Map<String, Action> commandMap= new HashMap<String, Action>();
	
	static final String path = "com/jsp/properties/url";
	
	public HandlerMapper() throws Exception {
		this(path);
	}
	public HandlerMapper(String path) throws Exception {	
		ResourceBundle rbHome = ResourceBundle.getBundle(path);
		Set<String> actionSetHome = rbHome.keySet(); // uri set
		Iterator<String> it = actionSetHome.iterator();
		while(it.hasNext()){
			String command = it.next(); //key -> url			

			String actionClassName = rbHome.getString(command);
			
			Class<?> actionClass = Class.forName(actionClassName);
			Action commandAction = (Action)actionClass.newInstance();
			
			//의존주입(service, dao.......)
			//의존성 확인 및 조립
			Method[] methods = actionClass.getMethods();
			for (Method method : methods) {
				if (method.getName().indexOf("set")==0) {
					String paramType=method.getParameterTypes()[0].getName();
					paramType=paramType.substring(paramType.lastIndexOf(".")+1);
					paramType=(paramType.charAt(0) + "").toLowerCase() + paramType.substring(1);
					
					method.invoke(commandAction,ApplicationContext.getApplicationContext().get(paramType));
					
					System.out.println("[HandlerMapper:invoke]"
							+actionClassName+":"
							+ApplicationContext.getApplicationContext().get(paramType));
				}
			}
			
			commandMap.put(command, commandAction);
			System.out.println("[HandlerMapper]"+command+":"+commandAction +" 가 준비되었습니다.");
			
		}
		
	}
	
	public Action getAction(String url) {
		Action action = commandMap.get(url);
		return action;
	}
}








