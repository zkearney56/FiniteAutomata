package geneticAlgorithm.old;
/**
import geneticAlgorithm.Algorithm.CrossoverEnum;
import list.ArrayList;

public class ByteChromosome extends AbstractChromosome implements Chromosome{

	public ByteChromosome(int size){
		super(size);
	}
	
	public ByteChromosome(Chromosome x){
		super(x);
	}

	public byte randomByte(){
		byte returnVal;
		if(Math.random() < .5){
			returnVal = (byte) 0;
		}
		else{
			returnVal = (byte) 1;
		}
		return returnVal;
	}

	public byte mutateByte(byte b) {
		if(b == 0){
			b = 1;
		}
		else{
			b = 0;
		}
		return b;
		
	}

	@Override
	public Chromosome clone() {
		return new ByteChromosome(this);
	}
}
*/
