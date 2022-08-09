package com.jsp.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

//저장을 담당한다.
public class FileUploadResolver {
		
	public static List<File> fileUpload(FileItem[] items, String uploadPath) throws Exception{
		List<File> uploadFileList = new ArrayList<File>();
		
		File file = new File(uploadPath);
		file.mkdirs();
		
		if (items != null)for (FileItem item : items) {
			String fileName = new File(item.getName()).getName(); // 사용자 파일명
			fileName = MakeFileName.toUUIDFileName(fileName, "$$"); // 고유한 파일명 ,유효한 파일명의 구분자      고유한 파일명을 사용하면 중복 걱정을 안해도 된다.
			
			//저장할 준비
			String filePath = uploadPath + file.pathSeparator + fileName;
			File storeFile = new File(filePath);
			
			// local HDD에 저장.
			try {
				item.write(storeFile);
			}catch(Exception e) {
				e.printStackTrace();
				throw e;
			}
			//list에 파일을 계속 추가하는 형식으로 저장
			uploadFileList.add(storeFile);
			
		}
		return uploadFileList;
	}
}
	
	
	
	
	
	
	

