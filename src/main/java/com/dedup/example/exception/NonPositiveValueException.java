package com.dedup.example.exception;

/**
 * 
 * Exception Class
 * @author Meenal Jadhav
 * @version 1.0
 */
public class NonPositiveValueException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * Constructor
	 */
	public NonPositiveValueException() {
		super();
	}

	/**
	 * 
	 * Constructor
	 */
	public NonPositiveValueException(final String arg0) {
		super(arg0);
	}
	
	/**
	 * 
	 * Constructor
	 */
	public NonPositiveValueException(final Throwable arg0) {
		super(arg0);
	}

	/**
	 * 
	 * Constructor
	 */
	public NonPositiveValueException(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
	}
	
	/**
	 * 
	 * Constructor
	 */
	public NonPositiveValueException(final String arg0, final Throwable arg1, final boolean arg2, final boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
