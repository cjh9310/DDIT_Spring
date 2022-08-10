package com.jsp.controller;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import org.apache.ibatis.io.Resources;

public class GetUploadPath  {
	
	private static Properties properties = new Properties();
	
	static{
		String resource = "com/jsp/properties/upload.properties";		
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			properties.load(reader);				
		
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getUploadPath(String key) {
		String uploadPath=null;
		
		uploadPath = properties.getProperty(key); // 하나씩 꺼낸다.
		uploadPath = uploadPath.replace("/",File.separator); // windows인지 Linux인지 구분함
		
		return uploadPath;
	}
}





