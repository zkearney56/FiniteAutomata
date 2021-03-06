package genalg.algorithm;

import java.util.Arrays;

import list.Stack;

public class TowerOfHanoi {

	private Stack<Integer> tower1;
	private Stack<Integer> tower2;
	private Stack<Integer> tower3;
	private int numGene = 0;
	private int numDisks = 0;
	private int geneSize = 0;
	private int numMoves = 0;
	private int illegalMoves = 0;
	private String returnString = "";
	private byte[] newGene;
	int x = 0;
	public static int initialGeneSize = 256;
	int prevMov = -1;

	public TowerOfHanoi(int numDisks) {
		this.numDisks = numDisks;
		tower1 = new Stack<Integer>();
		tower2 = new Stack<Integer>();
		tower3 = new Stack<Integer>();
		for (int i = numDisks; i > 0; i--) {
			tower1.push(i);
		}
	}

	public String toString() {
		returnString += "MIN NUM MOVES: " + minNumMoves(numDisks) + "\n";
		returnString += "CURR NUM MOVES: " + newGene.length + "\n";
		return returnString;
	}

	public boolean testComplete() {
		if (tower3.count() == numDisks)
			return true;
		return false;
	}

	public static int minNumMoves(int numDisks) {
		return (int) (Math.pow(2, numDisks) - 1);
	}

	public Fitness test(byte[] genome) {
		newGene = new byte[genome.length];
		geneSize = genome.length;
		addString();
		for (int i = 0; i < geneSize; i++) {
			if (moveDisk(genome[i])) {
				newGene[x] = genome[i];
				x++;
				numMoves++;
			} else {
				illegalMoves++;
			}
			numGene = i;
			if (testComplete()) {
				return calcFitness();
			}
		}
		return calcFitness();
	}

	public Fitness calcFitness() {
		boolean adjust = false;
		double returnVal = Math.pow(2, tower3.count()) * (Algorithm.TOWER_FITNESS / (illegalMoves + 1));
		returnVal = returnVal / Algorithm.TOWER_FITNESS;
		// System.out.println(returnVal);
		// System.out.println(illegalMoves);
		if (numGene < geneSize && testComplete()) {
			adjust = true; // f(x) = h / (x-a)^2 +1
			returnVal = (Math.pow(returnVal, 2) / (Math.sqrt(Math.pow((numMoves - minNumMoves(numDisks)), 2)) + 1))
					+ Math.pow(returnVal, 2);
		}
		newGene = Arrays.copyOf(newGene, x);
		return new Fitness(returnVal, adjust, newGene);
	}

	private int reverseMove(int x) {
		switch (x) {
		case 0:
			return 2;
		case 1:
			return 4;
		case 2:
			return 0;
		case 3:
			return 5;
		case 4:
			return 1;
		case 5:
			return 3;
		}
		return -1;
	}

	public boolean moveDisk(int x) {
		if (prevMov == reverseMove(x) || prevMov == x) {
			return false;
		}
		Stack<Integer> start = null;
		Stack<Integer> end = null;
		switch (x) {
		case 0:
			start = tower1;
			end = tower2;
			break;

		case 1:
			start = tower1;
			end = tower3;
			break;
		case 2:
			start = tower2;
			end = tower1;
			break;
		case 3:
			start = tower2;
			end = tower3;
			break;
		case 4:
			start = tower3;
			end = tower1;
			break;
		case 5:
			start = tower3;
			end = tower2;
			break;
		}
		if (start.isEmpty()) {
			return false;
		} else if (end.isEmpty()) {
			end.push(start.pop());
			addString();
			prevMov = x;
			return true;
		} else if (start.peek() > end.peek()) {
			return false;
		} else {
			end.push(start.pop());
			addString();
			prevMov = x;
			return true;
		}
	}

	public void addString() {
		returnString += tower1.count() + "   " + tower2.count() + "   " + tower3.count() + " \n";
	}
}
