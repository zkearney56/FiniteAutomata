package fa;

/*
 * Written by: Zachary Kearney
 * Copyright by: Zachary Kearney, 2016
 *
 * Program: Implementation.java
 * Date: March 22, 2016
 *
 * Description: Shows an implementation of the DFAs.
 * 
 */

import java.util.Scanner;

import fa.dfa.DFA;
import fa.dfa.DFABoolOp;
import fa.dfa.DFAMachine;
import fa.dfa.DFAMachine.L1;
import fa.dfa.DFAMachine.L2;

public class Implementation implements Runnable{

	private String input = "";
	private L1 one;
	private L2 two;

	public static void main(String args[]) {

		new Thread(new Implementation()).start();
	}

	public Implementation() {
	}
	
	public void run(){
		Scanner input = new Scanner(System.in);
		boolean running = true;
		System.out.println("-----------------------------------------------");
		System.out.println("DFA Machine");
		System.out.println("L1 = { x e {0,1}* | x has even length}");
		System.out.println("L2 = { x e {0,1}* | x ends with 11 }");
		DFAMachine machine = new DFAMachine();
		one = machine.genL1();
		two = machine.genL2();
		while (running) {
			System.out.println("Input a string of 0s and 1s");
			System.out.println("Type quit to exit");
			System.out.print("   ");
			String in = input.next();
			if (in.equals("quit")) {
				running = false;
				input.close();
			} else {
				testString(in);
			}
		}
	}

	public void testString(String input) {

		this.input = input;
		System.out.println("-----------------------------------------------");
		System.out.println("Testing Input : " + input + "\n");
		one.setInput(input);
		two.setInput(input);

		if (one.isValid() && two.isValid()) {
			new Thread(one).start();
			new Thread(two).start();

			if (one.isAccepted())
				System.out.println("  " + one.name() + ": " + input + "\n	Accepted");
			else
				System.out.println("  " + one.name() + ": " + input + "\n	Not accepted");
			if (two.isAccepted())
				System.out.println("  " + two.name() + ": " + input + "\n	Accepted");
			else
				System.out.println("  " + two.name() + ": " + input + "\n	Not accepted");
			System.out.println("");
			testFSA(one, two, FAFunc.UNION);
			testFSA(one, two, FAFunc.INTERSECTION);
			testFSA(one, two, FAFunc.DIFF);
			testFSA(one, two, FAFunc.SYM_DIFF);
			System.out.println("-----------------------------------------------");

		}
	}

	public void testFSA(DFA one, DFA two, FAFunc type) {

		switch (type) {
		case UNION:
			System.out.println("  " + one.name() + " u " + two.name() + ": " + input + " (Union)");
			printAcc(DFABoolOp.Union(one, two));
			break;
		case INTERSECTION:
			System.out.println("  " + one.name() + " n " + two.name() + ": " + input + " (Intersection)");
			printAcc(DFABoolOp.Intersection(one, two));
			break;
		case DIFF:
			System.out.println("  " + one.name() + " - " + two.name() + ": " + input + " (Difference)");
			printAcc(DFABoolOp.Difference(one, two));
			break;
		case SYM_DIFF:
			System.out.println("  Lu - Ln : " + input + " (Symmetric Difference)");
			printAcc(DFABoolOp.SymDifference(one, two));
			break;
		}
	}

	private void printAcc(boolean accepted) {
		if (accepted) {
			System.out.println("	Accepted");
		}

		else {
			System.out.println("	Not accepted");
		}
	}
}
