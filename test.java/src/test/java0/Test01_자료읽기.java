package test.java0;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test01_자료읽기 {
	public static void main(String[] args) throws IOException,Exception {
		
		// Reader , InputStream
		
		// InputStream :  
		// Reader : 
		
		BufferedReader br = null;
		try {
			
			br = new BufferedReader(new FileReader("src/test/java0/Test01_자료읽기.java"));
			while(true) {
				String readLine = br.readLine();
				if(readLine==null) {
					break;
				}
				System.out.println(readLine);
			}
		}finally {
			
			if(br!=null) {
				br.close(); 
			}
		}
		
		
		
		
		
	}
}








