package com.jsp.action.pds;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.PdsVO;
import com.jsp.service.PdsService;

public class PdsModifyFormAction implements Action {
	

	public PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;

	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "/pds/modify";
		
		int pno = Integer.parseInt(request.getParameter("pno"));

		PdsVO pds = pdsService.getPds(pno);		
		
		request.setAttribute("pds",pds);
		
		return url;
	}

}
