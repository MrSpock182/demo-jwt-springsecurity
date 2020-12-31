package br.com.studiotrek.spring.security.domain.dto;

import java.util.Date;

public class ErrorResponse {
	private final Date date;
	private final String path;
	private final Integer status;
	private final String error;
	private final String message;

	public ErrorResponse(Date date, String path, Integer status, String error, String message) {
		this.date = date;
		this.path = path;
		this.status = status;
		this.error = error;
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public String getPath() {
		return path;
	}

	public Integer getStatus() {
		return status;
	}

	public String getError() {
		return error;
	}

	public String getMessage() {
		return message;
	}
}
