package test.java0;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test03_자료읽기3 {
	public static void main(String[] args)  {
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("src/test/java0/Test01_자료읽기.java"));
			while(true) {
				String readLine = null;
				readLine = br.readLine();
				if(readLine==null) {
					break;
				}
				if(1==1) {
					return ;
				}
				System.out.println(readLine);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		finally {
			System.out.println("강제종료 finally");
			try {
				if(br!=null) {
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		
		
		
		
		
	}
}
