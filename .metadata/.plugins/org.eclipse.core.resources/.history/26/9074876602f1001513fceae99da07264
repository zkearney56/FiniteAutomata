package fa;

/*
 * Written by: Zachary Kearney
 * Copyright by: Zachary Kearney, 2016
 *
 * Program: FAState.java
 * Date: March 22, 2016
 *
 * Description: Interface for a state of a FA.
 * 
 */

public interface FAState {

	/**
	 * Sets the FAState as an accept state.
	 * @param bool - True if accept, false if not.
	 */
	
	public void setAccept(boolean bool);

	/**
	 * Adds a translation to the FAState.
	 * @param next - The next state.
	 * @param var - The character value of the translation.
	 */
	
	public void addTranslation(FAState next, char var);

	/**
	 * Returns true if FAState is an accept state.
	 * @return True if acccept, false if not.
	 */
	
	public boolean isAccept();

	/**
	 * Returns the next FAState for the character input.
	 * @param var - The character to be tested.
	 * @return The FAState for that character.
	 */
	
	public FAState nextState(char var);

}