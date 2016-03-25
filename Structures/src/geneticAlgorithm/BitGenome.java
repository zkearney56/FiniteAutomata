package geneticAlgorithm;

import list.ArrayList;

public class BitGenome implements Genome{

	private ArrayList<Bit> genome;
	private int size = 0;
	private int intVal = -1;
	
	public BitGenome(int size){
		this.size = size;
		genome = new ArrayList<Bit>(size);
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

	@Override
	public void add(Object obj) {
		genome.add((Bit)obj);
		toInt();
	}

	@Override
	public Object get(int index) {
		return genome.get(index);
	}

	public void randomize() {
		for(int i = 0; i < size; i++){
			genome.add(new Bit());
		}
		toInt();
	}
	
	public int size(){
		return size;
	}
	
	public int toInt(){
		int value = 0;
		for(int i = genome.size() - 1, x = 0; i >= 0; i--, x++){
			if(genome.get(i).get() == 1){
				value += Math.pow(2, x);
			}
		}
		intVal = value;
		return intVal;
	}
	
	public int getIntVal(){
		if(intVal == -1){
			return toInt();
		}
		return intVal;
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

	}
