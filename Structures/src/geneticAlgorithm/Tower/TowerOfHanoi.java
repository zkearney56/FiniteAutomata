package geneticAlgorithm.Tower;

import list.Stack;

public class TowerOfHanoi {
	
	private Stack<Integer> tower1;
	private Stack<Integer> tower2;
	private  Stack<Integer> tower3;
	private int numMoves = 0;
	private int numDisks = 0;
	private int maxNumMoves = 0;
	
	public TowerOfHanoi(int numDisks){
		this.numDisks = numDisks;
		tower1 = new Stack<Integer>();
		tower2 = new Stack<Integer>();
		tower3 = new Stack<Integer>();
		for(int i = numDisks; i > 0; i--){
			tower1.push(i);
		}
	}
	
	public boolean testComplete(){
		if(tower3.count() == numDisks)
			return true;
		return false;
	}
	
	public int test(byte[] genome){
		maxNumMoves = genome.length/2;
		for(int i = 0; i < genome.length; i++){
			int start = genome[i];
			int end = genome[i++];
			moveDisk(start, end);
			if(testComplete()){
				return calcFitness();
			}
		}
		return calcFitness();
	}
	
	public int calcFitness(){
		if((Math.pow(2, numDisks) - 1) == numMoves){
			return 99999999;
		}
		return(maxNumMoves - numMoves) * tower3.count();
	}
	
	public void moveDisk(int startTower, int endTower){
		if(startTower == endTower){
			return;
		}
		Stack<Integer> start = null;
		Stack<Integer> end = null;
		switch(startTower){
		case 1: start = tower1;
		break;
		case 2: start = tower2;
		break;
		case 3: start = tower3;
		break;
		}
		switch(endTower){
		case 1: end = tower1;
		break;
		case 2: end = tower2;
		break;
		case 3: end = tower3;
		break;
		}
		if(end.isEmpty()){
			end.push(start.pop());
			numMoves++;
		}
		else if(start.peek() > end.peek()){
			return;
		}
		else{
			end.push(start.pop());
			numMoves++;
		}
	}
}
