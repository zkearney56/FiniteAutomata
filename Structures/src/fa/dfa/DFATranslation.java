package fa.dfa;
/*
 * Written by: Zachary Kearney
 * Copyright by: Zachary Kearney, 2016
 *
 * Program: DFATranslation
 * Date: March 22, 2016
 *
 * Description: Object containing all translations for a DFAState object;
 * ConcurrentHashMap is used to be thread safe.
 * 
 */

import java.util.concurrent.ConcurrentHashMap;

import fa.FAState;
import fa.FATranslation;

public class DFATranslation implements FATranslation {

	private FAState currState;
	private ConcurrentHashMap<Character, FAState> translations;

	public DFATranslation(FAState currState) {
		this.currState = currState;
		translations = new ConcurrentHashMap<Character, FAState>();
	}

	public void add(Character val, FAState next) {
		translations.put(val, next);
	}

	public FAState next(Character val) {
		return translations.get(val);
	}

	public FAState currState() {
		return currState;
	}

}
