package com.jsp.exception;

public class NotFoundIdException extends Exception {
	
	public NotFoundIdException() {
		super("아이디가 존재하지 않습니다.");
	}
}
