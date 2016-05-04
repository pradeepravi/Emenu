package com.pradeep.bowl.score.exception;

/**
 * Throwing any basic exceptions related to the Bowling Game
 * 
 * @author Pradeep
 */
public class BowlingScoreException extends Exception {

	private String message ; 
	public BowlingScoreException(String message) {
		super();
		this.message = message; 
	}
	private static final long serialVersionUID = 3090450429430853114L;

	@Override
	public String getMessage() {
		return this.message;
	}

}
