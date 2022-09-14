package com.spring.controller;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.command.FileUploadCommand;

@Controller
public class FileUploadController {

	// String url이 GetMapping의 주소와 동일하면 생략할 수 있다.
	// 핸들러매퍼가 알아서 인식해줌
	
	@GetMapping("/fileUploadForm")
	public void fileUpload() {}
	
	private /*파일저장폴더설정*/
	String uploadPath = "c:\\fileupload\\".replace("/", File.separator);
	
	//방식1 (추천)     								업로드시 한글파일 깨짐 방지
	@PostMapping(value="/multipartFile", produces="text/plain; charset=utf-8")
	public String uploadByMultipartFile(String title, MultipartFile file, Model model)throws Exception {
		String url ="fileUploaded";
		
		File saveFile = new File(uploadPath,file.getOriginalFilename());
		
		saveFile.mkdirs();
		
		/*파일저장*/  // 파일저장때문에 try catch문 사용하게됨.
		file.transferTo(saveFile);
		
		model.addAttribute("title",title);
		model.addAttribute("orginalFileName",file.getOriginalFilename());
		model.addAttribute("uploadedFileName",saveFile.getName());
		model.addAttribute("uploadPath",saveFile.getAbsolutePath());
		
		return url;
	}
	// 방식2
	@PostMapping(value = "/multipartHttpServletRequest",produces="text/plain;charset=utf-8")
	public String uploadByMultipartHttpServletRequest(MultipartHttpServletRequest request,
														Model model) throws Exception{
		String url = null;
		
		String title = request.getParameter("title");
		MultipartFile multi = request.getFile("file");
		url = uploadByMultipartFile(title, multi, model);
		
		return url;
		
	}
	// 방식3 추천
	@PostMapping(value="/commandObject",produces="text/plain;charset=utf-8")
	public String uploadByCommandObject(FileUploadCommand cmd, Model model)throws Exception{
		String url;
		
		url = uploadByMultipartFile(cmd.getTitle(),cmd.getFile(),model);
		
		return url;
	}
	
	
}
