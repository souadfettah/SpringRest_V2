package com.lms.models;

import java.io.Serializable;

public class Status implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code;
	private String message;

	public Status() {
	}

	public Status(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
