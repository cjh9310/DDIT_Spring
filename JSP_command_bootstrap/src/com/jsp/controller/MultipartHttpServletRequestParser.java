package com.jsp.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jsp.exception.NotMultipartFormDataException;

public class MultipartHttpServletRequestParser {
	
	
	private Map<String , List<String>> paramString = new HashMap<String, List<String>>();
	private Map<String , List<FileItem>>  paramFile = new HashMap<String, List<FileItem>>();
//          Map<  키  , value는 무조건 배열로>              	hashMap<isfile(파일일 경우) , isnotfile(파일이 아닐 경우)
	
// 생성자
	public MultipartHttpServletRequestParser(HttpServletRequest request,
											 int memory_threshold,
											 int max_file_size,
											 int max_request_size)
											throws NotMultipartFormDataException,
											       UnsupportedEncodingException,
											       FileUploadException{
		
		//request 파일 첨부 여부 확인.
		if (!ServletFileUpload.isMultipartContent(request)) {
			throw new NotMultipartFormDataException();
		}
		
		ServletFileUpload upload = ServletFileUploadBuilder.build( memory_threshold, 
																	max_file_size, 
																	max_request_size);
		
		
		//request parsing
		List<FileItem> formItems = upload.parseRequest(request);
		
		if(formItems != null) for (FileItem item : formItems) {
			// FileItem은 파싱하면 낱개로 받아진다.
			String paramName = item.getFieldName();
			if(item.isFormField()) { // 일반 parameter : text
				
				List<String> paramValueList = this.paramString.get(paramName);
				// arrayList를 가져와서 get으로 들어있는 지를 확인하고 없으면  
				if(paramValueList == null) {
					paramValueList = new ArrayList<String>();
					this.paramString.put(paramName, paramValueList);
				}// if로 배열을 통하여 키 벨류값으로 넣어주고
				paramValueList.add(item.getString("utf-8"));
				// 있으면  추가해줌  utf-8이 왜 있는가?? => 한글이 꺠짐
			}else {
				List<FileItem> files = this.paramFile.get(paramName);
				if (files == null) {
					files = new ArrayList<FileItem>();
					this.paramFile.put(paramName,files);
				}
				files.add(item);
			}
		}
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
//getParameter는 처음부터 지정 값까지 출력
	public String getParameter(String paramName) {
		List<String> paramValueList = paramString.get(paramName);
		String paramValue = paramValueList.get(0);
		return paramValue;
	}
//getParameterValue는 하나의 값만 출력
// getParameterValues는?
	public String[] getParameterValues(String paramName) {
		List<String> paramValueList = paramString.get(paramName);
		String[] paramValueArray = null;
		if(paramValueList!= null) {
			paramValueArray = new String[paramValueList.size()];
			paramValueList.toArray(paramValueArray);
		}
		return paramValueArray;
	}
	
	public FileItem getFileItem(String paramName) {
		List<FileItem> itemList = paramFile.get(paramName);
		FileItem result = null;
		
		if(itemList != null) result = itemList.get(0);
			
		return result;	
	}
	public FileItem[] getFileItems(String paramName) {
		List<FileItem> items = paramFile.get(paramName);
		FileItem[] files = null;
		if(items !=null) {
			files = new FileItem[items.size()];
			items.toArray(files);
		}
		return files;
	}
	public Enumeration<String> getParamterNames(){
		List<String> paramNames = new ArrayList<String>();
		
		if (paramString.size() > 0) {
			for (String paramName : paramString.keySet()) {
				paramNames.add(paramName);
			}
		}
		Enumeration<String> result = Collections.enumeration(paramNames);
		
		return result;
		
	}
	
	
	
	
	
	
	
}
