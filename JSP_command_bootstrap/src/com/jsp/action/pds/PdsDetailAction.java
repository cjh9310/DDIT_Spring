package com.jsp.action.pds;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.PdsVO;
import com.jsp.service.PdsService;

public class PdsDetailAction implements Action {
	
	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String url="/pds/detail";
		
		int pno = Integer.parseInt(request.getParameter("pno"));
		String from = request.getParameter("from");
		
		PdsVO pds;
		if(from!=null && from.equals("list")) {
			pds = pdsService.read(pno);
			url="redirect:/pds/detail.do?pno="+pno;
		}else {
			pds = pdsService.getPds(pno);
		}			
		request.setAttribute("pds", pds);
		return url;
	}
	
}
