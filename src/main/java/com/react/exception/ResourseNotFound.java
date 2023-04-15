package com.react.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ResourseNotFound extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public ResourseNotFound(String message) {
		super(message);
	}
}
