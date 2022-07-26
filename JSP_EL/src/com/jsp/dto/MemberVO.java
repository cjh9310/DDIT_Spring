package com.jsp.dto;

public class MemberVO{
	private String id;
	private String pwd;
	
		
	public MemberVO() {}
	public MemberVO(String id, String pwd) {
		super();
		this.id = id;
		this.pwd = pwd;
	}
	public void setId(String id){
		this.id = id;
	}
	public void setPwd(String pwd){
		this.pwd=pwd;
	}
	
	public String getId(){
		return this.id;
	}
	public String getPwd(){
		return this.pwd;
	}
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pwd=" + pwd + "]";
	}
	
	
} 











