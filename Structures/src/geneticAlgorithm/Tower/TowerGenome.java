package geneticAlgorithm.Tower;

import geneticAlgorithm.AbstractGenome;
import geneticAlgorithm.Genome;
import geneticAlgorithm.Algorithm.AlgorithmEnum;

public class TowerGenome extends AbstractGenome implements Genome{

	public TowerGenome(Genome x) {
		super(x);
		// TODO Auto-generated constructor stub
	}

	public TowerGenome(int geneLength, AlgorithmEnum alg) {
		super(geneLength, alg);
	}

	@Override
	public Genome clone() {
		return new TowerGenome(this);
	}

	@Override
	public byte mutateByte(byte b) {
		double rand = Math.random();
		if(b == 1){
			if(rand < .5){
				return 2;
			}
			else{
				return 3;
			}
		}
		else if(b == 2){
			if(rand < .5){
				return 1;
			}
			else{
				return 3;
			}
		}
		else{
			if(rand < .5){
				return 1;
			}
			else{
				return 2;
			}
		}
	}

	@Override
	public byte randomByte() {
		double rand = Math.random();
		if(rand < .33){
			return 1;
		}
		else if(rand < .66){
			return 2;
		}
		else{
			return 3;
		}

	}

}
