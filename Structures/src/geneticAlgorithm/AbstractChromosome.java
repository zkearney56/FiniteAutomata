package geneticAlgorithm;

public abstract class AbstractChromosome implements Chromosome{

	private int fitness = 0;
	private int size = 0;
	private int intVal = 0;
	private AlgorithmEnum alg = AlgorithmEnum.ALG1;
	protected Genome genome;
	
	public AbstractChromosome(int size, AlgorithmEnum alg){
		this.size = size;
		this.alg = alg;
		initGenome(size);
		genome.randomize();
		intVal = genome.getIntVal();
		testFitness();
	}
	
	public AbstractChromosome(Chromosome x, Chromosome y, Crossover type){
		this.size = x.getSize();
		this.alg = x.getAlg();
		initGenome(size);
		if(x.getValue() == y.getValue()){
			clone(x);
		}
		else{
			crossover(x, y, type);
			testFitness();
		}
	}
	
	private final void crossover(Chromosome x, Chromosome y, Crossover type){
		switch(type){
		case EVERY_OTHER:{
			for(int i = 0; i < size; i ++){
				if ( (i & 1) == 0 ) {
					genome.add(x.getGene(i));
				} 
				else {
					genome.add(y.getGene(i));
				}
			}
			break;
		}
		case SPLIT:{
			int splitNum = size/2;
			for(int i = 0; i < splitNum; i++){
				genome.add(x.getGene(i));
			}
			for(int i = splitNum; i < size; i++){
				genome.add(y.getGene(i));
			}
			break;
		}
		}	
		intVal = genome.getIntVal();
	}
	
	public final int getFitness(){
		return fitness;
	}
	
	public final int getSize(){
		return size;
	}
	
	public final int getValue(){
		return intVal;
	}
	
	public final void mutate(int index){
		genome.mutate(index);
	}
	
	protected abstract void initGenome(int size);
	
	public final void testFitness(){
		fitness = Algorithm.calcFitness(intVal, alg);
	}
	
	public final AlgorithmEnum getAlg() {
		// TODO Auto-generated method stub
		return alg;
	}
	
	public final String toString(){
		return genome.toString();
	}
	
	public final Genome getGenome(){
		return genome;
	}
	
	public final void clone(Chromosome x){
		this.alg = x.getAlg();
		this.genome = x.getGenome();
		this.size = x.getSize();
		this.fitness = x.getFitness();
		this.intVal = x.getValue();
	}
	
	public Object getGene(int index){
		return genome.get(index);
	}
	
	public final void randomize(){
		genome.randomize();
	}
}
