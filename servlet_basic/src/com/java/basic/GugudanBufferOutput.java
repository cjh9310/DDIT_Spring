package com.java.basic;

public class GugudanBufferOutput implements Output {

	private OutputBuffer buffer;
	public void setBuffer(OutputBuffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public void view() {
		buffer.out("\n");		
	}

	@Override
	public void view(int dan) {
		buffer.out(dan+"단\n");
		
	}

	@Override
	public void view(int dan, int gop) {
		buffer.out(dan + " * " + gop + " = " + (dan * gop)+"\n");
		
	}

}
