package geneticAlgorithm;

public abstract class AbstractChromosome implements Chromosome{

	private int fitness = 0;
	private int size = 0;
	private int intVal = 0;
	private AlgorithmEnum alg = AlgorithmEnum.ALG1;
	
	public AbstractChromosome(int size, AlgorithmEnum alg){
		this.size = size;
		this.alg = alg;
		init();
	}
	
	public AbstractChromosome(Chromosome x, Chromosome y, Crossover type){
		this.size = x.getSize();
		this.alg = x.getAlg();
		initArray();
		if(x.getValue() == y.getValue()){
			clone(x);
		}
		else{
			crossover(x, y, type);
			toInt();
			testFitness();
		}
	}
	
	protected abstract void crossover(Chromosome x, Chromosome y, Crossover type);
	
	public int getFitness(){
		return fitness;
	}
	
	public int getSize(){
		return size;
	}
	
	public int getValue(){
		return intVal;
	}
	
	protected abstract void toInt();
	
	protected abstract void randomize();
	
	protected abstract void initArray();
	
	protected abstract void testFitness();
	
	protected abstract void clone(Chromosome x);
	
	private void init(){
		randomize();
		initArray();
		toInt();
		testFitness();
	}
	
	public AlgorithmEnum getAlg() {
		// TODO Auto-generated method stub
		return alg;
	}
	
}
