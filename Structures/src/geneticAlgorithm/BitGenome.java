package geneticAlgorithm;

import list.ArrayList;

public class BitGenome implements Genome{

	private ArrayList<Bit> genome;
	private int size = 0;
	
	public BitGenome(int size){
		this.size = size;
		genome = new ArrayList<Bit>(size);
		randomize();
	}
	
	public BitGenome(int size, Genome x, Genome y, Crossover type){
		this.size = size;
		genome = new ArrayList<Bit>(size);
		if(x.getIntVal() == y.getIntVal()){
			for(int i = 0; i < x.size(); i++){
				add(x.get(i));
			}
		}
		else{
		switch(type){
		case EVERY_OTHER:{
			for(int i = 0; i < size; i ++){
				if ( (i & 1) == 0 ) {
					add(x.get(i));
				} 
				else {
					add(y.get(i));
				}
			}
			break;
		}
		case SPLIT:{
			int splitNum = size/2;
			for(int i = 0; i < splitNum; i++){
				add(x.get(i));
			}
			for(int i = splitNum; i < size; i++){
				add(y.get(i));
			}
			break;
		}
		}	
	}
	}

	@Override
	public void add(Object obj) {
		genome.add((Bit)obj);
	}

	@Override
	public Object get(int index) {
		return genome.get(index);
	}

	public void randomize() {
		for(int i = 0; i < size; i++){
			genome.add(new Bit());
		}
	}
	
	public int size(){
		return size;
	}
	
	public int getIntVal(){	
			int value = 0;
			for(int i = genome.size() - 1, x = 0; i >= 0; i--, x++){
				if((int)genome.get(i).get() == 1){
					value += Math.pow(2, x);
				}
			}	
		return value;
	}

	public String toString(){
	String str = "";
	for(int i = 0; i < genome.size(); i++){
		str += (int)genome.get(i).get();
	}
	return str;
	}

	@Override
	public void mutate(int index) {
		genome.get(index).flip();
	}

	@Override
	public void crossover(Genome x, Genome y, Crossover type) {
		
	}
	}
