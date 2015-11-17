package com.db.cs4400.fancyhotel.exception;

public class FancyHotelReviewException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FancyHotelReviewException() {
		super();
	}
	
	public FancyHotelReviewException(String message) {
		super(message);
	}
	
	public FancyHotelReviewException(String message, Exception e) {
		super(message, e);
	}

}
