package com.jsp.action.pds;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.josephoconnell.html.HTMLInputFilter;
import com.jsp.action.Action;
import com.jsp.controller.MultipartHttpServletRequestParser;
import com.jsp.dto.PdsVO;
import com.jsp.service.PdsService;

public class PdsModifyAction implements Action {

	private PdsService pdsService;

	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}

	// 업로드 파일 환경 설정
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 200; // 200MB

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "/pds/modify_success";

		MultipartHttpServletRequestParser multi = 
				new MultipartHttpServletRequestParser(request, MEMORY_THRESHOLD,
															   MAX_FILE_SIZE, 
															   MAX_REQUEST_SIZE);

		
		
		
		PdsVO pds = new PdsVO();
		pds.setPno(Integer.parseInt(multi.getParameter("pno")));
		pds.setWriter(multi.getParameter("writer"));
		pds.setContent(multi.getParameter("content"));

		// XSS처리
		String title = HTMLInputFilter.htmlSpecialChars(multi.getParameter("title"));
		pds.setTitle(title);

		//DB 수정
		pdsService.modify(pds);
		
		request.setAttribute("pds", pds);
		
		return url;
	}

}
