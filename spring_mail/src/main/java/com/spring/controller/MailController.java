package com.spring.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.spring.command.MailMessageCommand;
import com.spring.mail.MimeAttachNotifier;

@Controller
@RequestMapping("/mail")
public class MailController {
	
	@Autowired
	private MimeAttachNotifier notifier;
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public void mailGet() throws Exception {
	}

	@RequestMapping(value = "/send", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public ModelAndView mailPost(MailMessageCommand mailReq, HttpServletRequest request, ModelAndView mnv)
			throws Exception {
		String url = "mail/mail_success";

		MultipartFile attach = mailReq.getFile();

		String uploadPath 
		= request.getSession().getServletContext().getRealPath("resources/mail_attach");
		
		// 파일유무
		if (attach != null && !attach.isEmpty()) {
			// 파일의크기
			if (attach.getSize() > 1024 * 1024 * 5) {
				url = "mail/mail_fail";
				mnv.addObject("message", "첨부파일이 용량초과 입니다.");
				mnv.setViewName(url);
				return mnv;
			}
			// 파일저장
			File file = new File(uploadPath, attach.getOriginalFilename());
			file.mkdirs();
			mnv.addObject("uploadPath", uploadPath);	
			attach.transferTo(file);
		}

		notifier.sendMail(mailReq, uploadPath);	//메일보내기	
		
		mnv.setViewName(url);
		mnv.addObject("mailRequest", mailReq);	
		return mnv;
	}
}
