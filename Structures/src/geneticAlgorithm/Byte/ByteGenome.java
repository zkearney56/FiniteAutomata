package geneticAlgorithm.Byte;

import geneticAlgorithm.AbstractGenome;
import geneticAlgorithm.CrossoverEnum;
import geneticAlgorithm.Genome;
import list.ArrayList;

public class ByteGenome extends AbstractGenome implements Genome{

	public ByteGenome(int size){
		super(size);
	}
	
	public ByteGenome(Genome x){
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
	public Genome clone() {
		return new ByteGenome(this);
	}
}
