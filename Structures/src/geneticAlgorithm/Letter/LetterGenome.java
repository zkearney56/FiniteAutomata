package geneticAlgorithm.Letter;

import java.util.Random;

import geneticAlgorithm.Crossover;
import geneticAlgorithm.Genome;
import list.ArrayList;

public class LetterGenome implements Genome{

	private byte[] genome;
	int size = 0;

	public LetterGenome(int size){
		this.size = size;
		genome = new byte[size];
	}
	@Override
	public byte get(int index) {
		return genome[index];
	}

	@Override
	public void randomize() {
		for(int i = 0; i < size; i++){
			genome[i] = randomByte();
		}
	}
	
	private byte randomByte(){
		byte returnVal;
		Random rand = new Random();
		switch(rand.nextInt(26)){
		case 0: return 'a';
		case 1: return 'b';
		case 2: return 'c';
		case 3: return 'd';
		case 4: return 'e';
		case 5: return 'f';
		case 6: return 'g';
		case 7: return 'h';
		case 8: return 'i';
		case 9: return 'j';
		case 10: return 'k';
		case 11: return 'l';
		case 12: return 'm';
		case 13: return 'n';
		case 14: return 'o';
		case 15: return 'p';
		case 16: return 'q';
		case 17: return 'r';
		case 18: return 's';
		case 19: return 't';
		case 20: return 'u';
		case 21: return 'v';
		case 22: return 'w';
		case 23: return 'x';
		case 24: return 'y';
		case 25: return 'z';
		}
		return '?';
	}

	@Override
	public int size() {
		return genome.length;
	}

	@Override
	public void mutate(int index) {
		genome[index] = randomByte();		
	}

	@Override
	public void crossover(Genome x, Genome y, Crossover type) {
		
		byte[] xx = x.getGenome();
		byte[] yy = y.getGenome();

		switch(type){
		case EVERY_OTHER:{
			for(int i = 0; i < size; i ++){
				if ( (i & 1) == 0 ) {
					genome[i] = xx[i];
				} 
				else {
					genome[i] = yy[i];
				}
			}
			break;
		}
		case SPLIT:{
			int splitNum = size/2;
			for(int i = 0; i < splitNum; i++){
				genome[i] = xx[i];
			}
			for(int i = splitNum; i < size; i++){
				genome[i] = yy[i];
			}
			break;
		}
		}
	}
	
	public byte[] getGenome(){
		return genome;
	}

	public String toString(){
		String str = "";
		for(int i = 0; i <genome.length; i++){
			str += (char)genome[i];
		}
		return str;
	}
}
