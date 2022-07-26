package com.java.basic;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GugudanInput implements Input {

	@Override
	public Map<String, Integer> execute() {
		Map<String, Integer> data = new HashMap<String, Integer>();

		Scanner scann = new Scanner(System.in);
		System.out.print("단수를 입력하시오._");
		data.put("dan", Integer.parseInt(scann.nextLine()));

		System.out.print("곱수를 입력하시오._");
		data.put("gop", Integer.parseInt(scann.nextLine()));

		return data;
	}

}
