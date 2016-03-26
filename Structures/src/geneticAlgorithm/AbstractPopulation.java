package geneticAlgorithm;

import java.util.Random;

import geneticAlgorithm.Algorithm.Algorithm;
import geneticAlgorithm.Algorithm.AlgorithmEnum;
import list.ArrayList;

public abstract class AbstractPopulation implements Population {
	
	private static double MUT_COEF = .005; //Mutation coefficient
	private static int MAX_NUM_GEN = 200; //Maximum number of generations
	private static int MAX_NUM_FIT = 10;
	private static CrossoverEnum crossType = CrossoverEnum.EVERY_OTHER;//Maximum number of gens with same max fitness
	private static AlgorithmEnum alg = AlgorithmEnum.BYTE_ALG1;
	
	private double mutCount = 0;
	private int size = 0, currentElite = 0, generation = 0, genCount = 0, geneLength = 0;
	
	private Chromosome elite;
	private ArrayList<Chromosome> pop;
	private boolean maxFound = false;


	
	public AbstractPopulation(int size, int geneLength, AlgorithmEnum alg, CrossoverEnum type){
		this.size = size;
		this.geneLength = geneLength;
		this.alg = alg;
		this.crossType = type;
		fillPopulation();
		setMutCoef(MUT_COEF);
	}
	
	/* (non-Javadoc)
	 * @see geneticAlgorithm.Population#setMaxGen(int)
	 */
	@Override
	public final void setMaxGen(int maxGen){
		MAX_NUM_GEN = maxGen;
	}
	
	/* (non-Javadoc)
	 * @see geneticAlgorithm.Population#setMaxFit(int)
	 */
	@Override
	public final void setMaxFit(int maxFit){
		MAX_NUM_FIT = maxFit;
	}
	
	/* (non-Javadoc)
	 * @see geneticAlgorithm.Population#setMutCoef(double)
	 */
	@Override
	public final void setMutCoef(double mutcoef){
		MUT_COEF = mutcoef;
	}
	
	public final void incMutCount(){
		mutCount += (size * geneLength) * MUT_COEF;
	}
	
	public final void decMutCount(){
		mutCount--;
	}
	/* (non-Javadoc)
	 * @see geneticAlgorithm.Population#getMutCoef()
	 */
	@Override
	public final double getMutCoef(){
		return MUT_COEF;
	}
	
	/* (non-Javadoc)
	 * @see geneticAlgorithm.Population#getMaxGen()
	 */
	@Override
	public final int getMaxGen(){
		return MAX_NUM_GEN;
	}
	
	/* (non-Javadoc)
	 * @see geneticAlgorithm.Population#getMaxFit()
	 */
	@Override
	public final int getMaxFit(){
		return MAX_NUM_FIT;
	}
	
	/* (non-Javadoc)
	 * @see geneticAlgorithm.Population#getMutCount()
	 */
	@Override
	public final double getMutCount(){
		return mutCount;
	}
	
	/* (non-Javadoc)
	 * @see geneticAlgorithm.Population#getChromosome(int)
	 */
	@Override
	public final Chromosome getChromosome(int index){
		return pop.get(index);
	}
	
	public final void setChromosome(Chromosome e, int index){
		pop.set(e, index);
	}
	
	/* (non-Javadoc)
	 * @see geneticAlgorithm.Population#execute()
	 */
	@Override
	public final void execute(){
		firstRun();
		while(generation < MAX_NUM_GEN && !maxFound){
		newGeneration();
		}
		foundMax();
	}
	
	public final int size(){
		return size;
	}
	
	public final void setElite(Chromosome elite){
		this.elite = elite;
		elite.setElite(true);
	}
	
	public final Chromosome getElite(){
		return elite;
	}
	
	public final AlgorithmEnum getAlg(){
		return alg;
	}

	public final CrossoverEnum getCross(){
		return crossType;
	}
	private void fillPopulation(){
		pop = new ArrayList<Chromosome>(size);
		for(int i = 0; i < size; i++){
			pop.add(addNewChromosome(geneLength, alg));
		}
		elite = pop.get(0);
		elite.setElite(true);
		currentElite = elite.getFitness();
	}
	
	protected abstract Chromosome addNewChromosome(int geneLength, AlgorithmEnum alg);
	 
	private void mutate(){
		int x = 0;
		while(x < mutCount){
			if(mutateLoop()){
				x++;
			}
		}
	}
	
	private boolean mutateLoop(){
		for(int i = 1; i < pop.size(); i++){
			Chromosome mutChrom = pop.get(i);
			if(!mutChrom.isElite()){
			for(int y = 0; y < mutChrom.getSize(); y++){
				if(Math.random() < MUT_COEF){
					mutChrom.mutate(y);	
					return true;
				}
			}
		}}
		return false;		
	}
	
	private Chromosome chooseRandom(){
		Random rand = new Random();
		int prob = 1;
		if(elite.getFitness() > 0){
			prob = rand.nextInt(elite.getFitness());	
		}
		
		int probIndex = 0;
		while(true){
			int randIndex = rand.nextInt(pop.size());
			probIndex += pop.get(randIndex).getFitness();
			if(probIndex >= prob){
				return pop.get(randIndex);
			}
		}
	}
	
	private void findElite(){
		for(int i = 0; i < pop.size(); i++){
			if(pop.get(i).getFitness() > elite.getFitness()){
				elite.setElite(false);
				elite = pop.get(i);
				elite.setElite(true);
			}
		}
	}

	private boolean generationCount(){
		boolean cont = true;
		if(currentElite == elite.getFitness()){
			genCount++;
		}
		else{
			currentElite = elite.getFitness();
			genCount = 0;
		}
		if(genCount >= MAX_NUM_FIT){
			cont = false;
			maxFound = true;
		}
		return cont;
	}
	
	/**
	private void crossover(){
		ArrayList<Chromosome> newGen = new ArrayList<Chromosome>(size);
		newGen.add(elite);
		for(int i = 1; i < size - 1; i++){
			newGen.add((CrossoverFunc.crossover(chooseRandom(), chooseRandom(), crossType)));
		}
		pop = newGen;
		pop.set(elite, 0);
	}
	*/
	
	private void firstRun(){
		for(int i = 0; i < pop.size(); i++){
			if(pop.get(i).getFitness() >= currentElite){
				elite = pop.get(i);
				
			}
			currentElite = elite.getFitness();
			elite.setElite(true);
		}
	}
	
	private void newGeneration(){
		
		GeneticFunc.findElite(this);
		printData();
		if(!generationCount()) return;
		GeneticFunc.crossOver(this);
		GeneticFunc.mutate(this);
		generation++;
		
	}
	
	private void printData(){
		System.out.println("GENERATION: " + generation);
		System.out.println("MAX FITNESS:" + elite.getFitness());
		System.out.println("VALUE: " + Algorithm.intVal(elite.getGenome().getGenome()));
		System.out.println(elite.toString());
	}
	
	private void foundMax(){
		System.out.println("\nSOLUTION FOUND");
		System.out.println("GENERATION: " + generation);
		System.out.println("MAX FITNESS:" + elite.getFitness());
		System.out.println("VALUE: " + Algorithm.intVal(elite.getGenome().getGenome()));
		System.out.println(elite.toString());
	}
	
	
}
