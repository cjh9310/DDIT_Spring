package com.jsp.action.member;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.apache.commons.fileupload.FileItem;

import com.jsp.action.Action;
import com.jsp.controller.FileUploadResolver;
import com.jsp.controller.GetUploadpath;
import com.jsp.controller.MultipartHttpServletRequestParser;
import com.jsp.exception.NotMultipartFormDataException;

public class MemberPictureUploadAction implements Action {

	// 업로드 파일 환경 설정
	private static final int MEMORY_THRESHOLD = 1024*500; //500KB
	private static final int MAX_FILE_SIZE = 1024*500; //1MB
	private static final int MAX_REQUEST_SIZE = 1024*500; //2MB
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String url = null;// 화면을 없다를 명시적으로 표시하기 위해
		
		// ajax로 전송하는데(request정보를 보내서 처리해야함) 
		// try 설명 필요         
		try {
		// 1. request 변환
		MultipartHttpServletRequestParser multi = 
				new MultipartHttpServletRequestParser(request, MEMORY_THRESHOLD,  // 입력부분 
															   MAX_FILE_SIZE, 
															   MAX_REQUEST_SIZE);
		
		// 2. 저장할 경로 (properties에서 관리 함)
		String uploadPath = GetUploadpath.getUploadpath("member.picture.upload");
		
		// 3. 업로드된 이미지 저장
		FileItem[] items = multi.getFileItems("pictureFile");
		List<File> uploadFiles = FileUploadResolver.fileUpload(items, uploadPath);
		
		String uploadFileName = uploadFiles.get(0).getName();	// 파일명
		
		// 4. 이전 이미지 삭제
		String oldPicture = multi.getParameter("oldPicture");
		File oldFile = new File(uploadPath + File.separator + oldPicture);
		if (oldFile.exists()) {
			oldFile.delete();
		}
		
		// 서블릿은 화면을 내보냄 화면이 아닌 것은 전부 action에서 처리한다.
		// 5. 저장한 파일명 보내기
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(uploadFileName);

		} catch (NotMultipartFormDataException e) {
			response.sendError(Response.SC_BAD_REQUEST); //400
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(Response.SC_INTERNAL_SERVER_ERROR);
		}
		
		
		return url;
		
		
	}

}
