package test.java1;

import java.awt.List;

public class Test01_파라미터 {
	public static void main(String[] args) {
		
		String data = "test";
		
		if(containsOr(data,"te","sss","sadasda","dfdfdfdfdfdf")) {
			System.out.println("true");
		}
		
		if(containsOr(data,"te","s1")) {
			System.out.println("true");
		}
		
		StringBuffer sb = methodName("파라미터",StringBuffer.class);
		List list = methodName("파라미터", List.class);
	}
	
	
	private static boolean containsOr(String target, String ... compares) {
		for(String compare : compares) {
			if(target.contains(compare)) {
				return true;
			}
		}
		return false;
	}
}
