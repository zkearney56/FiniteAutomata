package fa;

/*
 * Written by: Zachary Kearney
 * Copyright by: Zachary Kearney, 2016
 *
 * Program: FAFunc.java
 * Date: March 22, 2016
 *
 * Description: Interface for a translation function of a FA.
 * 
 */

public interface FATranslation {

	/**
	 * Adds a translation to the translation table.
	 * @param val - The character value.
	 * @param next - The next FAState.
	 */
	
	public void add(Character val, FAState next);
	
	/**
	 * Returns the next FAState for the given input.
	 * @param val - The character to be tested.
	 * @return The next FAState.
	 */
	
	public FAState next(Character val);
	
	/**
	 * Returns the FAState with which this translation is associated with.
	 * @return
	 */
	
	public FAState currState();
	
}
