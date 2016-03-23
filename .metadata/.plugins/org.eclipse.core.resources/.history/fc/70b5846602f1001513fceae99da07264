package fa;

/*
 * Written by: Zachary Kearney
 * Copyright by: Zachary Kearney, 2016
 *
 * Program: DFAState.java
 * Date: March 22, 2016
 *
 * Description: State object for use with DFAs. 
 * 
 */

public class DFAState implements FAState {

	private boolean accept = false;
	private DFATranslation translation;

	public DFAState(boolean accept) {
		this.accept = accept;
		translation = new DFATranslation(this);
	}

	public void setAccept(boolean bool) {
		accept = bool;
	}

	public void addTranslation(FAState next, char var) {
		translation.add(var, next);
	}

	public boolean isAccept() {
		return accept;
	}

	public FAState nextState(char var) {
		return translation.next(var);
	}
	
}
