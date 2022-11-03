package com.jsp.action.pds;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.josephoconnell.html.HTMLInputFilter;
import com.jsp.action.Action;
import com.jsp.controller.FileUploadResolver;
import com.jsp.controller.GetUploadPath;
import com.jsp.controller.MultipartHttpServletRequestParser;
import com.jsp.dto.AttachVO;
import com.jsp.dto.PdsVO;
import com.jsp.service.PdsService;

public class PdsRegistAction implements Action {

	private PdsService pdsService;

	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}

	// 업로드 파일 환경 설정
	final private int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	final private int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	final private int MAX_REQUEST_SIZE = 1024 * 1024 * 200; // 200MB

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "/pds/regist_success";

		MultipartHttpServletRequestParser multi = new MultipartHttpServletRequestParser(request, MEMORY_THRESHOLD,
				MAX_FILE_SIZE, MAX_REQUEST_SIZE);

		// 파일처리
		// 실제 저장 경로를 설정.
		String uploadPath = GetUploadPath.getUploadPath("pds.upload");
		
		// 파일 저장후 List<File>를 리턴..
		List<File> fileList = FileUploadResolver.fileUpload(multi.getFileItems("uploadFile")
															, uploadPath);

		// List<File> -> List<AttachVO>
		List<AttachVO> attachList = null;
		if (fileList != null) {
			attachList = new ArrayList<AttachVO>();
			for (File file : fileList) {
				AttachVO attach = new AttachVO();
				// DB에 저장할 attach에 file 내용 추가.
				attach.setFileName(file.getName());
				attach.setUploadPath(uploadPath);
				attach.setFileType(file.getName().substring(file.getName().lastIndexOf(".") + 1));

				attachList.add(attach);
			}
		}
		
		// VO 생성
		PdsVO pds = new PdsVO();
		pds.setContent(multi.getParameter("content"));
		pds.setWriter(multi.getParameter("writer"));
		pds.setAttachList(attachList);
		
		// XSS처리
		String title = HTMLInputFilter.htmlSpecialChars(multi.getParameter("title"));
		pds.setTitle(title);

		// DB처리
		try {
			pdsService.regist(pds);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

		return url;
	}

}
