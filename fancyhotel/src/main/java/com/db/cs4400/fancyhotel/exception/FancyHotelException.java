package com.db.cs4400.fancyhotel.exception;

public class FancyHotelException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FancyHotelException() {
		super();
	}

	public FancyHotelException(String message) {
		super(message);
	}
	
	public FancyHotelException(String message, Exception e) {
		super(message, e);
	}
	
	public FancyHotelException(Exception e) {
		super(e);
	}
}
