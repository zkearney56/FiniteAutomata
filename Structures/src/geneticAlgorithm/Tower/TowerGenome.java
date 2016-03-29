package geneticAlgorithm.Tower;

import java.util.Random;

import geneticAlgorithm.AbstractGenome;
import geneticAlgorithm.Genome;
import geneticAlgorithm.Algorithm.AlgorithmEnum;

public class TowerGenome extends AbstractGenome implements Genome{

	private Random rand;
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
		rand = new Random();
		while(true){
			int next = rand.nextInt(6);
			if(next != b){
				return (byte)next;
			}
		}
	}
	
	@Override
	public byte randomByte() {
		rand = new Random();
		int next = rand.nextInt(6);
		return (byte) next;
	}

}
