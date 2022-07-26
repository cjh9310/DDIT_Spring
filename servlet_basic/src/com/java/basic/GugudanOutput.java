package com.java.basic;

public class GugudanOutput implements Output {
	
	public void view() {
		System.out.println();
	}
	
	public void view(int dan) {
		System.out.println(dan + "단");
	}

	public void view(int dan, int gop) {
		System.out.println(dan + " * " + gop + " = " + (dan * gop));
	}

}
