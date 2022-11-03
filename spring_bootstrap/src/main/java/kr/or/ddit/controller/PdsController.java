package kr.or.ddit.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.josephoconnell.html.HTMLInputFilter;
import com.jsp.command.Criteria;
import com.jsp.dto.AttachVO;
import com.jsp.dto.PdsVO;
import com.jsp.service.PdsService;

import kr.or.ddit.command.PdsModifyCommand;
import kr.or.ddit.command.PdsRegistCommand;

@Controller
@RequestMapping("/pds")
public class PdsController {

	@Resource(name="pdsService")
	private PdsService service;	
	
	@RequestMapping("/main")
	public void main() throws Exception {}
	
	
	@RequestMapping("/list")
	public ModelAndView list(Criteria cri, ModelAndView mnv) throws Exception {
		String url = "pds/list";

		Map<String, Object> dataMap = service.getList(cri);

		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);

		return mnv;
	}
	
	
	@RequestMapping("/registForm")
	public String registForm() throws Exception {
		String url = "pds/regist";
		return url;
	}
	

	@Resource(name = "fileUploadPath")
	private String fileUploadPath;
	
	@RequestMapping(value = "/regist", method = RequestMethod.POST,
			produces = "text/plain;charset=utf-8")	
	public String regist(PdsRegistCommand registReq,HttpServletRequest request,
					 RedirectAttributes rttr) throws Exception {
		String url = "redirect:/pds/list.do";		
		
		String savePath = this.fileUploadPath;		
		
		//file 저장 -> List<AttachVO>
		List<AttachVO> attachList 
		= MultipartFileUploadResolver.fileUpload(registReq.getUploadFile(), savePath);
		
		//DB 
		PdsVO pds = registReq.toPdsVO();						
		pds.setAttachList(attachList);		
		
		pds.setTitle(HTMLInputFilter.htmlSpecialChars(pds.getTitle()));
		
		service.regist(pds);
		
		//output
		rttr.addFlashAttribute("from", "regist");
		return url;
	}
	
	@RequestMapping("/detail")
	public ModelAndView detail(int pno, String from, ModelAndView mnv) 
														throws Exception {
		String url = "pds/detail";
		

		PdsVO pds = null;
		if (from != null && from.equals("list")) {
			pds = service.read(pno);
			url = "redirect:/pds/detail.do?pno=" + pno;
		} else {
			pds = service.getPds(pno);
		}
		
	
		mnv.addObject("pds", pds);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping("/getFile")
	public String getFile(int ano,HttpServletRequest request, 
								  Model model) throws Exception {
		
		String url="downloadFile";
		
		AttachVO attach = service.getAttachByAno(ano);
		
		model.addAttribute("savedPath", attach.getUploadPath());
		model.addAttribute("fileName",attach.getFileName());
		
		return url;
	}
	
	@RequestMapping("/modifyForm")
	public ModelAndView modifyForm(ModelAndView mnv, int pno) throws Exception {
		String url = "pds/modify";
		
		PdsVO pds = service.getPds(pno);
		
		mnv.addObject("pds",pds);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping("/modify")
	public String modifyPOST(PdsModifyCommand modifyReq,
			                 HttpServletRequest request,
			                 RedirectAttributes rttr) throws Exception {
		String url = "redirect:/pds/detail.do";
		
		// 파일 삭제
		if (modifyReq.getDeleteFile() != null && modifyReq.getDeleteFile().size() > 0) {
			for (int ano : modifyReq.getDeleteFile()) {
				AttachVO attach = service.getAttachByAno(ano);
				
				File deleteFile 
						= new File(attach.getUploadPath(), attach.getFileName());
				
				if (deleteFile.exists()) {
					deleteFile.delete(); // File 삭제
				}
				service.removeAttachByAno(ano); // DB 삭제
				
			}
		}
			
		//file 저장 -> List<AttachVO>
		List<AttachVO> attachList 
		= MultipartFileUploadResolver.fileUpload(modifyReq.getUploadFile(), fileUploadPath);
		
		//DB 
		PdsVO pds = modifyReq.toPdsVO();						
		pds.setAttachList(attachList);		
		
		pds.setTitle(HTMLInputFilter.htmlSpecialChars(pds.getTitle()));
		
		service.modify(pds);
		
		rttr.addFlashAttribute("from", "modify");
		rttr.addAttribute("pno", pds.getPno());
		
		return url;
	}
	
	@RequestMapping("/remove")
	public String remove(int pno, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/pds/detail.do";

		// 첨부파일 삭제
		List<AttachVO> attachList = service.getPds(pno).getAttachList();
		if (attachList != null) {
			for (AttachVO attach : attachList) {
				String uuidFileName = service.getAttachByAno(attach.getAno()).getFileName();
				File target = new File(attach.getUploadPath(), uuidFileName);
				if (target.exists()) {
					target.delete();
				}
			}
		}
		
		// DB삭제
		service.remove(pno);
		
		
		rttr.addFlashAttribute("from", "remove");
		rttr.addAttribute("pno", pno);
		
		return url;
	}
}





