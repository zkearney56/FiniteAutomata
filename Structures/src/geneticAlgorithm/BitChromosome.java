package geneticAlgorithm;

import list.ArrayList;

public class BitChromosome extends AbstractChromosome implements Chromosome{
	
	private ArrayList<Bit> geneSequence;
	private int size = 0;
	private int fitness = 0;
	private int intVal = 0;
	private AlgorithmEnum alg = AlgorithmEnum.ALG1;
	
	public BitChromosome(int size, AlgorithmEnum alg){
		super(size, alg);
	}
	
	public BitChromosome(Chromosome x, Chromosome y, Crossover cross){
		super(x,y,cross);
	}
	
	public ArrayList<Bit> getSequence(){
		return geneSequence;
	}
	
	private Bit getBit(int index){	
		return geneSequence.get(index);	
	}
	
	protected void toInt(){
		int value = 0;
		for(int i = geneSequence.size() - 1, x = 0; i >= 0; i--, x++){
			if(geneSequence.get(i).get() == 1){
				value += Math.pow(2, x);
				
			}
		}
		intVal = value;
	}
	
	
	protected void testFitness(){
		fitness = Algorithm.calcFitness(intVal, alg);
	}
	
	public String toString(){
		String str = "";
		for(int i = 0; i < geneSequence.size(); i++){
			str += (int)geneSequence.get(i).get();
		}
		return str;
	}

	
	public void mutate(int index){
		geneSequence.get(index).flip();
	}



	@Override
	protected void crossover(Chromosome x, Chromosome y, Crossover type) {
		switch(type){
		case EVERY_OTHER:{
			for(int i = 0; i < size; i ++){
				if ( (i & 1) == 0 ) {
					geneSequence.add(((BitChromosome) x).getBit(i));
				} 
				else {
					geneSequence.add(((BitChromosome) y).getBit(i));
				}
			}
			break;
		}
		case SPLIT:{
			int splitNum = size/2;
			for(int i = 0; i < splitNum; i++){
				geneSequence.add(((BitChromosome) x).getBit(i));
			}
			for(int i = splitNum; i < size; i++){
				geneSequence.add(((BitChromosome) y).getBit(i));
			}
			break;
		}
		}	
	}

	@Override
	protected void randomize() {
		for(int i = 0; i < size; i++){
			geneSequence.add(new Bit());
		}
	}

	@Override
	protected void initArray() {
		geneSequence = new ArrayList<Bit>(size);	
	}


	@Override
	protected void clone(Chromosome x) {
		this.alg = x.getAlg();
		this.geneSequence = ((BitChromosome)x).getSequence();
		this.size = x.getSize();
		this.fitness = x.getFitness();
		this.intVal = x.getValue();
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

}
