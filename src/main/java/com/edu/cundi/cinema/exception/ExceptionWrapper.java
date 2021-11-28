package com.edu.cundi.cinema.exception;

import java.time.LocalTime;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExceptionWrapper {

	private LocalTime timestamp;
	private int status;
	private String error;
	private String message;
	private List<String> details;
	private String path;

	public ExceptionWrapper(int status, String error, String message, String path) {
		this.timestamp = LocalTime.now();
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	public ExceptionWrapper(int status, String error, String message, String path, List<String> details) {
		this.timestamp = LocalTime.now();
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
		this.details = details;
	}

}
