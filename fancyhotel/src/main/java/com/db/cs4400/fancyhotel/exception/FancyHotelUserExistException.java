package com.db.cs4400.fancyhotel.exception;

public class FancyHotelUserExistException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FancyHotelUserExistException() {
		super();
	}
	
	public FancyHotelUserExistException(String message) {
		super(message);
	}
	
	public FancyHotelUserExistException(String message, Exception e) {
		super(message, e);
	}

}
