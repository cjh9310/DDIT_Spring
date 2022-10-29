package test.java0;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test02_자료읽기2 {
	public static void main(String[] args)  {
		
		// Reader , InputStream
		
		// InputStream :  
		// Reader : 
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("src/test/java0/Test01_자료읽기.java"));
			while(true) {
				String readLine = null;
				readLine = br.readLine();
				if(readLine==null) {
					break;
				}
				System.out.println(readLine);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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
