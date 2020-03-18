package com.minsait.devops.poc.ejb.statefull.exception;

public class InsufficientFundsException extends Exception {
	/**
	* 
	*/
	private static final long serialVersionUID = -4790415303787651873L;

	public InsufficientFundsException(String mess) {
		super(mess);
	}
}
