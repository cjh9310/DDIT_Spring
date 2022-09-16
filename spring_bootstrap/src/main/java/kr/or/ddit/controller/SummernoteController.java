package kr.or.ddit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.controller.MakeFileName;

@RestController
public class SummernoteController {
	
	@Resource(name = "imgPath")
	private String imgPath;
	
	
	@RequestMapping(value="/uploadImg",produces = "text/plain;charset=utf-8")
	public ResponseEntity<String> uploadImg(MultipartFile file, 
											HttpServletRequest request){
		ResponseEntity<String> result = null;
		
		int fileSize = 5 * 1024 * 1024; // 5MB 제한
		if (file.getSize() > fileSize) {
			return new ResponseEntity<String>("용량 초과입니다.", HttpStatus.BAD_REQUEST);
		}		
		

		String savePath = imgPath.replace("/", File.separator);
		String fileName = MakeFileName.toUUIDFileName("", "");
		File saveFile = new File(savePath, fileName);
		
		saveFile.mkdirs();
		

		try {
			file.transferTo(saveFile);
			result = new ResponseEntity<String>(request.getContextPath() 
					+ "/getImg.do?fileName=" + fileName,HttpStatus.OK);
			
		}catch(IOException e) {
			e.printStackTrace();
			result = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
	
	@RequestMapping("/getImg")
	public ResponseEntity<byte[]> getImg(String fileName) {
		ResponseEntity<byte[]> entity = null;
		
		String savePath = imgPath.replace("/", File.separator);
		File sendFile = new File(savePath, fileName);
		

		InputStream in = null;
		
		try {

			in = new FileInputStream(sendFile);
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), 
					 HttpStatus.CREATED);

		}catch(IOException e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		}finally {
			if(in!=null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
					entity = new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
		}
		return entity;	
	}
	
	@RequestMapping("/deleteImg")
	public ResponseEntity<String> deleteImg(@RequestBody Map<String, String> data) {
		ResponseEntity<String> result = null;
		
		String savePath = imgPath.replace("/", File.separator);
		File target = new File(savePath, data.get("fileName"));
		
		if (!target.exists()) {
			result = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		} else {

			target.delete();
			result = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}
		
		return result;
	}
}










