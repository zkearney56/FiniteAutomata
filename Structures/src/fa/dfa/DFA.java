package fa.dfa;

/*
 * Written by: Zachary Kearney
 * Copyright by: Zachary Kearney, 2016
 *
 * Program: DFA.java
 * Date: March 22, 2016
 *
 * Description: Abstract class of a Deterministic Finite Automata.
 * 
 */

import list.ArrayList;
import java.util.Iterator;

import fa.FA;
import fa.FAState;
import fa.InvalidInputException;

public abstract class DFA implements FA {

	
	private String name = "";
	protected String testString = "";
	protected final FAState startState;
	protected boolean accepted = false;
	protected boolean complete = false;
	private boolean valid = false;
	private boolean hasLanguage = false;
	private ArrayList<Character> charList;
	private ArrayList<Character> lang;

	public DFA(String language, String name) {
		this.name = name;
		lang = new ArrayList<Character>();
		charList = new ArrayList<Character>();
		startState = new DFAState(false);
		
		try{
		setLanguage(language);
		}
		catch(InvalidInputException e){
			e.printStackTrace();
		}
	}
	
	public void setLanguage(String language) throws InvalidInputException{
		if(!hasLanguage){
		ArrayList<Character> chars = new ArrayList<Character>();
		for (char c : language.toCharArray()) {
			chars.add(c);
		}
		Iterator<Character> itr = chars.iterator();
		while (itr.hasNext()) {
			Character chr = itr.next();
			if (!lang.contains(chr)) {
				lang.add(chr);
			}
		}
		if(!lang.isEmpty()){
			hasLanguage = true;
		}
		else{
			throw new InvalidInputException("Error: Invalid Language.");
		}
		}
		else{
			throw new InvalidInputException("Error: DFA already has a language.");
		}
	}
	
	/**
	 * Resets DFA. Tests input against the DFA language. If it is valid, DFA can test the string.
	 */
	
	public void setInput(String input){
		complete = false;
		accepted = false;
		valid = false;
		testString = input;
		try {
			if (TestInput()){
				valid = true;
			}
		} catch (InvalidInputException e) {
			e.printStackTrace();
			testString = "";
			complete = true;
			accepted = false;
			valid = false;
		}
	}

	/**
	 * Run method for threading.
	 */
	
	public void run() {
		if (valid) {
			genFiniteMachine();
			testFiniteMachine();
			complete = true;
		}
	}

	/**
	 * Tests finite machine by iterating through the states using the test input. Once iteration has completed, checks current State for acceptance.
	 */
	
	private void testFiniteMachine() {
		char[] charArray = testString.toCharArray();
		charList.clear();
		for (int i = 0; i < charArray.length; i++) {
			charList.add(charArray[i]);
		}
		FAState currState = startState;
		Iterator<Character> itr = charList.iterator();
		while (itr.hasNext()) {
			currState = currState.nextState(itr.next());
		}
		if (currState.isAccept()) {
			accepted = true;
		} else {
			accepted = false;
		}
	}

	protected abstract void genFiniteMachine();

	/**
	 * Returns the name of the DFA.
	 * @return
	 */
	
	public String name() {
		return name;
	}

	/**
	 * Tests input against language for validity.
	 * @return
	 * @throws InvalidInputException
	 */
	private boolean TestInput() throws InvalidInputException {

		if(!hasLanguage){
			throw new InvalidInputException("Error: Language is empty.");
		}
		
		if (testString == null) {

			throw new InvalidInputException("Error: Input cannot be null.");

		}

		for (int i = 0; i < testString.length(); i++) {
			if (!lang.contains(testString.charAt(i))) {
				throw new InvalidInputException("Error: Invalid Input. Language does not contain input chararcters.");
			}
		}
		return true;
	}

	public boolean isAccepted() {

		while (complete == false) {

			try {
				Thread.sleep(10); 	// Sleeps for 10 milliseconds and checks again.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		return accepted;
	}

	
	public boolean isValid() {
		return valid;
	}
}
