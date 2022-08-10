package com.jsp.action.member;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.action.Action;
import com.jsp.controller.GetUploadPath;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;

public class MemberRemoveAction implements Action {

	private MemberService memberService;
	public void setSearchMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "/member/remove_success";
		
		String id = request.getParameter("id");
		
		// 지울건데 왜 getMember인가?   이미지가 남아있어서 이미지를 지워야함.
		MemberVO member = memberService.getMember(id);
		// 이미지 삭제하는 작업 필요
		// 사진 이미지 삭제
		String picture = member.getPicture();
		String savedPath = GetUploadPath.getUploadPath("member.picture.upload");
		
		try {
			File deletePictureFile = new File(savedPath, picture);
			if(deletePictureFile.exists()) 
				deletePictureFile.delete();
		}catch(Exception e) {
			// e.printStackTrace();
			System.out.println("사진이 없습니다.");
		}
		
		// DB 삭제
		memberService.remove(id);
		
		// loginUser 확인
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if (loginUser != null && member.getId().contentEquals(loginUser.getId())) {
			session.invalidate();
		}
		
		request.setAttribute("member", member);
		
		return url;
	}

}
