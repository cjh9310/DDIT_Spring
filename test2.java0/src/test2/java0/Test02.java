package test2.java0;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test02 {
	public static void main(String[] args) {
		
		// String
		String data = "Test";
		String data1 = new String("Test");
		System.out.println(data==data1);
		System.out.println("종료");

		// List, Set, Map, Array
		// List - 
		// Set
		// Map  - 
		
		
		
		List<Integer> list = new ArrayList<>(); // ArrayList, LinkedList  
		Set<Integer> set = new HashSet<>(); 	//  
		
		long count = 200000000; 
		
		long t2 = System.currentTimeMillis();
		for(int i = 0; i<count; i++) {
			int value = (int) (Math.random()*100); // 0<= x < 1
			//System.out.println(value);
			set.add(value);
		}
		long t1 = System.currentTimeMillis();
		System.out.println((t1-t2)/1000.0);
		
		for(int i = 0; i<count; i++) {
			int value = (int) (Math.random()*100); // 0<= x < 1
			//System.out.println(value);
			
			if(list.contains(value) == false) {
				list.add(value);
			}
			
		}
		long t3 = System.currentTimeMillis();
		System.out.println((t3-t1)/1000.0);		

		
		long t4 = System.currentTimeMillis();
		long t5 = System.currentTimeMillis();
		
		
	}
}
