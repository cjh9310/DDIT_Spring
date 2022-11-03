package com.jsp.action.pds;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.command.Criteria;
import com.jsp.command.CriteriaCommand;
import com.jsp.controller.HttpRequestParameterAdapter;
import com.jsp.service.PdsService;

public class PdsListAction implements Action{
	
	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String url = "/pds/list";

		try {
			CriteriaCommand criCMD		
				=  HttpRequestParameterAdapter.execute(request, CriteriaCommand.class);
			
			Criteria cri = criCMD.toCriteria();

			Map<String, Object> dataMap = pdsService.getList(cri);
			request.setAttribute("dataMap", dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return url;
	}
}
