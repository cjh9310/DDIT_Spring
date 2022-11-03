package com.jsp.controller;

import java.io.File;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ServletFileUploadBuilder {

	public static ServletFileUpload build(int MEMORY_THRESHOLD, 
										  int MAX_FILE_SIZE, 
										  int MAX_REQUEST_SIZE) {

		// 업로드를 위한 upload 환경설정 적용.
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 저장을 위한 threshold memory 적용.
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// 임시 저장 위치 결정.
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);

		// 업로드 파일의 크기 적용.
		upload.setFileSizeMax(MAX_FILE_SIZE);
		// 업로드 request 크기 적용.
		upload.setSizeMax(MAX_REQUEST_SIZE);

		return upload;
	}
}
