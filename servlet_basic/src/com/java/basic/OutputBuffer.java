package com.java.basic;

public class OutputBuffer {
	
	private String buffer = "";
	
	private static OutputBuffer instance = new OutputBuffer();
	
	private OutputBuffer() {}
	public static OutputBuffer getInstance() {
		return instance;
	}
	
	public void out(String string) {
		this.buffer+=string;
	}
	
	public void flush() {
		System.out.println(this.buffer);
		this.buffer = "";
	}
	
}
