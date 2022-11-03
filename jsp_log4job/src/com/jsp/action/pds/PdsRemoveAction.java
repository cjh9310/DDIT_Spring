package com.jsp.action.pds;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.AttachVO;
import com.jsp.service.PdsService;

public class PdsRemoveAction implements Action {

	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "/pds/remove_success";
		
		int pno = Integer.parseInt(request.getParameter("pno"));
		
		//파일삭제
		// pno에 대한 attachList 확보
		List<AttachVO> attachList = pdsService.getPds(pno).getAttachList();	
		if(attachList != null) {
			for (AttachVO attach : attachList) {
				
				String uuidFileName = pdsService.getAttachByAno(attach.getAno()).getFileName();
				
				String storedFilePath = attach.getUploadPath() + File.separator
						+ uuidFileName;
				
				File file = new File(storedFilePath);
				
				if (file.exists()) {
					file.delete();
				}
			}
		}
		
		
		//DB 내용 삭제
		pdsService.remove(pno);
		
		return url;
	}

}
