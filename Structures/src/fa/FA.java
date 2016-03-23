package fa;

/*
 * Written by: Zachary Kearney
 * Copyright by: Zachary Kearney, 2016
 *
 * Program: FA.java
 * Date: March 22, 2016
 *
 * Description: Interface for a finite-automata that is runnable via a thread.
 * 
 */

public interface FA extends Runnable {


	/**
	 * Run method for use with threads.
	 */

	public void run();

	/**
	 *  Returns true if the FA has been accepted.
	 * @return isAccepted
	 */
	
	public boolean isAccepted();
	
	/**
	 * Returns true if the FA is valid. Waits for execution of the program to complete.
	 * @return isValid
	 */
	
	public boolean isValid();
	
	/**
	 * Updates the input for the FA.
	 * @param input
	 */
	public void setInput(String input);
	
	/**
	 * If language input from constructor is invalid , allows you to reenter the language.
	 * @param language
	 * @throws InvalidInputException
	 */
	public void setLanguage(String language) throws InvalidInputException;
	
}
