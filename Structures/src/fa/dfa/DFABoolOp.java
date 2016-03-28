package fa.dfa;

/*
 * Written by: Zachary Kearney
 * Copyright by: Zachary Kearney, 2016
 *
 * Program: FABoolOp.java
 * Date: March 22, 2016
 *
 * Description: Boolean operators for use with a DFA.
 * 
 */

public class DFABoolOp {

	/**
	 * Returns true if either DFAs are accepted.
	 * @param one - DFA one
	 * @param two - DFA two
	 * @return
	 */
	public static final boolean Union(DFA one, DFA two) {

		boolean accepted = false;
		accepted = (one.isAccepted() || two.isAccepted());
		return accepted;

	}

	/**
	 * Returns true if both DFAs are accepted.
	 * @param one - DFA one
	 * @param two - DFA two
	 * @return
	 */
	
	public static final boolean Intersection(DFA one, DFA two) {

		boolean accepted = false;
		accepted = (one.isAccepted() && two.isAccepted());
		return accepted;

	}

	/**
	 * Returns true if DFA one is accepted and DFA two is not accepted.
	 * @param one - DFA one
	 * @param two - DFA two
	 * @return
	 */
	
	public static final boolean Difference(DFA one, DFA two) {

		boolean accepted = false;
		accepted = (one.isAccepted() && !two.isAccepted());
		return accepted;

	}

	/**
	 * Returns boolean for symmetric difference of two DFAs.
	 * @param one - DFA one
	 * @param two - DFA two
	 * @return
	 */
	public static final boolean SymDifference(DFA one, DFA two) {

		boolean accepted = false;
		accepted = ((one.isAccepted() && !two.isAccepted()) || (!one.isAccepted() && two.isAccepted()));
		return accepted;

	}
}
