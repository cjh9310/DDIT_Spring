package com.jsp.controller;

import java.util.UUID;

// 동일한 이름 정책을 만들기 위해 새로운 클래스로 나눴다.
// 이를 통해 모든 파일명을 만들면 다 이러한 형식으로 만들게 됨(통일된 네이밍 규정을 위해 클래스를 만듦)

public class MakeFileName {
	
	public static String toUUIDFileName(String fileName, String delimiter) {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		// 파일이름을 고유학 만듥 위해 유효값으로 만들려고
		return uuid + delimiter + fileName;                                                                                                       
	}
	
	public static String parseFileNameFromUUID(String fileName, String delimiter) {
		String[] uuidFileName = fileName.split(delimiter); 
		// delimiter가 달러 두개인데  기존에 만든
		return uuidFileName[uuidFileName.length - 1]; // 
	}
	
	
	
}
