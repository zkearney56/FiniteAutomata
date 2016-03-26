package geneticAlgorithm.Byte;

import geneticAlgorithm.AbstractGenome;
import geneticAlgorithm.Genome;
import geneticAlgorithm.Algorithm.AlgorithmEnum;

public class ByteGenome extends AbstractGenome implements Genome{
	
	public ByteGenome(Genome x){
		super(x);
	}
	
	public ByteGenome(int size, AlgorithmEnum alg) {
		super(size, alg);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Genome clone() {
		return new ByteGenome(this);
	}

	@Override
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
	public byte randomByte() {
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
