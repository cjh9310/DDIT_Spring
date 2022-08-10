package com.jsp.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.controller.FileDownloadResolver;
import com.jsp.controller.GetUploadpath;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;

public class MemberGetPictureAction implements Action{

	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = null;   // 사진을 가져온 후 새창or화면에 바꿀필요 없이 그대로 유지해야해서 url에 추가없이 null로 표시
		
		String id = request.getParameter("id");
		
		try {
			MemberVO member = memberService.getMember(id);
			
			// 파일이 나올때는 파일명과 경로가 필수이다.
			String fileName = member.getPicture();
			String savedPath = GetUploadpath.getUploadpath("member.picture.upload");
			
			FileDownloadResolver.sendFile(fileName, savedPath, request, response);
//			controller											request = input역할  response = output역할  
			
		}catch(Exception e) {
			e.printStackTrace();
			response.sendError(response.SC_INTERNAL_SERVER_ERROR);
		}
		return url;
		
	}
}
