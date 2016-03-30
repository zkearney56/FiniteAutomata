package geneticAlgorithm.Gene;

import geneticAlgorithm.AbstractGenome;
import geneticAlgorithm.Genome;
import geneticAlgorithm.Algorithm.AlgorithmEnum;

public class GeneGenome extends AbstractGenome implements Genome{
	
	public GeneGenome(Genome x){
		super(x);
	}
	
	public GeneGenome(int size, AlgorithmEnum alg) {
		super(size, alg);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Genome clone() {
		return new GeneGenome(this);
	}

	@Override
	public byte mutateByte(byte b) {
		double rand = Math.random();
		if(b == 00){
			if(rand < .33){
				return (byte) 01;
			}
			else if(rand < .66){
				return (byte) 11;
			}
			else{
				return (byte) 10;
			}
		}
		else if(b == 01){
			if(rand < .33){
				return (byte) 11;
			}
			
			else if (rand < .66){
				return (byte) 10;
			}
			else return (byte) 00;
		}
		else if(b == 10){
			if(rand < .33){
				return (byte) 11;
			}
			
			else if (rand < .66){
				return (byte) 01;
			}
			else return (byte) 00;
		}
		else if(b == 11){
			if(rand < .33){
				return (byte) 01;
			}
			else if(rand < .66){
				return (byte) 00;
			}
			else{
				return (byte) 10;
			}
		}
		else{
		return 0;
		}	
	}

	@Override
	public byte randomByte() {
		byte returnVal;
		double rand = Math.random();
		if(rand < .25){
			returnVal = (byte) 00;
		}
		else if(rand < .5){
			returnVal = (byte) 10;
		}
		else if (rand < .75){
			returnVal = (byte) 01;
		}
		else{
			returnVal = (byte) 11;
		}
		return returnVal;
	}

}
