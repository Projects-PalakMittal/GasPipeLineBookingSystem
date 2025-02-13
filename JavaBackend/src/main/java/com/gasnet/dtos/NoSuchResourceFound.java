package com.gasnet.dtos;

public class NoSuchResourceFound extends RuntimeException{
	
	public NoSuchResourceFound(String errMessage) {
		super(errMessage);
	}
}
