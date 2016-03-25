package geneticAlgorithm;

import list.ArrayList;

public class Chromosome {
	
	private ArrayList<Bit> geneSequence;
	private int size = 0;
	private int fitness = 0;
	private int intVal = 0;
	
	public Chromosome(int size){
		this.size = size;
		geneSequence = new ArrayList<Bit>(size);
		randomize();
		init();
	}
	
	public Chromosome(Chromosome x, Chromosome y, Crossover cross){
		this.size = x.size;
		geneSequence = new ArrayList<Bit>(size);
		crossover(x,y,cross);
		init();
	}
	
	private void crossover(Chromosome x, Chromosome y, Crossover cross){
		switch(cross){
		case EVERY_OTHER:{
			geneSequence = new ArrayList<Bit>(size);
			for(int i = 0; i < size; i ++){
				if ( (i & 1) == 0 ) {
					geneSequence.add(x.getBit(i));
				} 
				else {
					geneSequence.add(y.getBit(i));
				}
			}
			break;
		}
		case SPLIT:{
			geneSequence = new ArrayList<Bit>(size);
			int splitNum = size/2;
			for(int i = 0; i < splitNum; i++){
				geneSequence.add(x.getBit(i));
			}
			for(int i = splitNum; i < size; i++){
				geneSequence.add(y.getBit(i));
			}
			break;
		}
		}	
	}
	
	public Bit getBit(int index){	
		return geneSequence.get(index);	
	}
	
	private void init(){
		toInt();
		testFitness();
	}
	
	private void randomize(){
		for(int i = 0; i < size; i++){
			geneSequence.add(new Bit());
		}
	}
	
	private void toInt(){
		int value = 0;
		for(int i = geneSequence.size() - 1, x = 0; i >= 0; i--, x++){
			if(geneSequence.get(i).get() == 1){
				value += Math.pow(2, x);
				
			}
		}
		intVal = value;
	}
	
	
	private void testFitness(){
		
	}
	
	public String toString(){
		String str = "";
		for(int i = 0; i < geneSequence.size(); i++){
			str += (int)geneSequence.get(i).get();
		}
		return str;
	}

	
	static class Bit{
		byte dat;
		
		Bit(){
			dat = randomBit();
		}
		
		void flip(){
			if (dat == 0){
				dat = (byte) 1;
			}
			else{
				dat = (byte) 0;
			}
		}
		
		byte get(){
			return dat;
		}
		
		private byte randomBit(){
			byte returnVal;
			if(Math.random() < .5){
				returnVal = (byte) 0;
			}
			else{
				returnVal = (byte) 1;
			}
			return returnVal;
		}
	}
	
	public int getFitness(){
		return fitness;
	}
	
	public int getSize(){
		return size;
	}
	
	public int getIntVal(){
		return intVal;
	}
	
	public void mutate(int index){
		geneSequence.get(index).flip();
	}
}
