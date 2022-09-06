package com.spring.module;

public class ExtraSummation extends Summation {
	
	@Override
	public int sum(int a, int b) {
		return super.sum(a, b)+10;
	}
	
}
