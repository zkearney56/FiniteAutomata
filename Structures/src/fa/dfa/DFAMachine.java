package fa.dfa;

import fa.FAState;

/*
 * Written by: Zachary Kearney
 * Copyright by: Zachary Kearney, 2016
 *
 * Program: DFAMachine.java
 * Date: March 22, 2016
 *
 * Description: Creates two FSMs of type
 * L1 = { x e {0,1}* | x has even length}
 * L2 = { x e {0,1}* | x ends with 11 }
 * 
 */

public class DFAMachine {

	private final static String language = "01";
	
	/**
	 * Returns a new DFA of the type L1 = { x e {0,1}* | x has even length}.
	 * @return
	 */
	
	public L1 genL1(){
		return new L1(language);
	}

	/**
	 * Returns a new DFA of the type L2 = { x e {0,1}* | x ends with 11 }.
	 * @return
	 */
	
	public L2 genL2() {
		return new L2(language);
	}

	public class L1 extends DFA {

		public L1(String language) {
			super(language, "L1");
		}

		/**
		 * Generates the FM from a translation table.
		 */
		
		protected void genFiniteMachine() {
			FAState q1 = new DFAState(false);
			startState.setAccept(true);
			startState.addTranslation(q1, '0');
			startState.addTranslation(q1, '1');
			q1.addTranslation(startState, '0');
			q1.addTranslation(startState, '1');

		}
	}

	public class L2 extends DFA {

		public L2(String language) {
			super(language, "L2");
		}

		/**
		 * Generates the FM from a translation table.
		 */
		
		protected void genFiniteMachine() {
			FAState q1 = new DFAState(false);
			FAState q2 = new DFAState(true);
			startState.addTranslation(q1, '1');
			startState.addTranslation(startState, '0');
			q1.addTranslation(startState, '0');
			q1.addTranslation(q2, '1');
			q2.addTranslation(startState, '0');
			q2.addTranslation(q2, '1');
		}

	}

}
