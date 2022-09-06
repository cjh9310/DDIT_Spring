package com.spring.cal;

import com.spring.module.Divide;
import com.spring.module.Minus;
import com.spring.module.Multiplex;
import com.spring.module.Summation;

public class Calculator {
	
	private Summation sum = new Summation();
	private Minus min = new Minus();
	private Multiplex multi = new Multiplex();
	private Divide div = new Divide();
	
	public void setSum(Summation sum) {
		this.sum = sum;
	}

	public void setMin(Minus min) {
		this.min = min;
	}

	public void setMulti(Multiplex multi) {
		this.multi = multi;
	}

	public void setDiv(Divide div) {
		this.div = div;
	}

	
	
	
	public int sum(int a, int b) {
		return sum.sum(a, b);
	}
	
	public int minus(int a, int b) {
		return min.minus(a, b);
	} 
	
	public int multi(int a, int b) {
		return multi.multi(a, b);				
	}
	
	public float div(int a, int b) {
		return div.div(a, b);
	}	
}
