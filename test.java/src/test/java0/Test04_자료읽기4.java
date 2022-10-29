package test.java0;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test04_자료읽기4 {
	public static void main(String[] args) {
		
		try (
				BufferedReader br = new BufferedReader(new FileReader("src/test/java0/Test01_자료읽기.java"));
				
				){
			
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
		
	}
}
