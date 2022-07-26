package com.java.basic;

import java.util.Map;

public class GugudanService implements Service {
	
	private Input input;
	private Output output;

	public void setInput(Input input) {
		this.input = input;
	}

	public void setOutput(Output output) {
		this.output = output;
	}


	@Override
	public void process() {
		Map<String,Integer> data = input.execute();
		
		//입력
		int danNum = data.get("dan");
		int gopNum = data.get("gop");
		
		// 단수반복
		for (int dan=2; dan < danNum+1; dan++) {
			output.view(dan);

			// 곱수반복
			for (int gop = 1; gop < gopNum+1; gop++) {
				output.view(dan, gop);
			}
			output.view();
		}

	}

}
