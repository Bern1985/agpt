package com.ancs.agpt.exception;

public class XssException  extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5348259399562397990L;

	private String msg;
	private int code = 500;

	public XssException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public XssException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}

	public XssException(String msg, int code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}

	public XssException(String msg, int code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
