package com.jsp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileDownloadResolver {

	public static void sendFile(String fileName, String savedPath, HttpServletRequest request, 
								HttpServletResponse response) throws ServletException, IOException{
		
		String filePath = savedPath + File.pathSeparator + fileName;  
		//파일의 경로                                      윈도우나 맥이  /가 달라서 File.pathSeparator를 써서 자동으로 맞춰준다
		File downloadFile = new File(filePath);
		
		// 파일 포맷으로 MIME를 결정한다.
		ServletContext context = request.getServletContext();
		String mimeType = context.getMimeType(filePath);
		if (mimeType == null) {
			mimeType = "application/octet-stream"; //원래 가지고있는 확장자 그대로 열어준다.
		}
		
		// response 수정
		response.setContentType(mimeType);  // setContentType를 사용하지 않으면 jsp, png가 아닌 txt로 바뀜
		response.setContentLength((int) downloadFile.length());
		
		String headerKey = "Content-Disposition";
		//한글깨짐 방지 : ISO-8859-1
		String sendFileName = 
		MakeFileName.parseFileNameFromUUID(downloadFile.getName(), "\\$\\$");
		
		String header = request.getHeader("User-Agent"); // 크롬, 파이어폭스 등은 자동으로 설정됨.
		if (header.contains("MSIE")) { // 인터넷 익스플로어만 이것이 필요함
			sendFileName = URLEncoder.encode(sendFileName,"UTF-8");
		} else {
			sendFileName = new String(sendFileName.getBytes("utf-8"),"ISO-8859-1");
		}
		String headerValue = String.format("attachment; filename=\"%s\"", sendFileName);
		response.setHeader(headerKey, headerValue);
		
		//파일 내보내기
		FileInputStream inStream = new FileInputStream(downloadFile);
		OutputStream outStream = response.getOutputStream();
		try {
		byte[] buffer = new byte[4096]; // 옛날부터 관행적으로 사용함  ( 그냥 쓰라)
		int bytesRead = -1;    //유효하지 않은 값. 파일이 있으면 0   
		
		while((bytesRead = inStream.read(buffer)) != -1) {
			outStream.write(buffer,0,bytesRead);
		}
		} finally {
			outStream.close();
			inStream.close();
		}
	}
}
