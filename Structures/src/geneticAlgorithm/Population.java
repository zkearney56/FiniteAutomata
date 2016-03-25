package geneticAlgorithm;

import java.util.Random;

import list.ArrayList;

public abstract class Population {
	
	private static double MUT_COEF = .015; //Mutation coefficient
	private static int MAX_NUM_GEN = 200; //Maximum number of generations
	private static int MAX_NUM_FIT = 10;
	private static Crossover type = Crossover.EVERY_OTHER;//Maximum number of gens with same max fitness
	
	private double mutCount = 0;
	private int count = 0, currentElite = 0, generation = 0, genCount = 0, geneLength = 0;
	
	private Chromosome elite;
	private ArrayList<Chromosome> pop;
	private boolean maxFound = false;
	private AlgorithmEnum alg = AlgorithmEnum.ALG1;

	
	public Population(int count, int geneLength, AlgorithmEnum alg, Crossover type){
		this.count = count;
		this.geneLength = geneLength;
		this.alg = alg;
		this.type = type;
		fillPopulation();
		mutCount = (count * geneLength) * MUT_COEF;
		System.out.println("NUM MUTATIONS: " + mutCount);
	}
	
	public final void setMaxGen(int maxGen){
		MAX_NUM_GEN = maxGen;
	}
	
	public final void setMaxFit(int maxFit){
		MAX_NUM_FIT = maxFit;
	}
	
	public final void setMutCoef(double mutcoef){
		MUT_COEF = mutcoef;
	}
	
	public final void execute(){
		firstRun();
		while(generation < MAX_NUM_GEN && !maxFound){
		newGeneration();
		}
		foundMax();
	}
	
	private void fillPopulation(){
		pop = new ArrayList<Chromosome>(count);
		for(int i = 0; i < count; i++){
			pop.add(addNewChromosome(geneLength, alg));
		}
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
					System.out.println("MUTATION");
					System.out.println(mutChrom.toString());
					mutChrom.mutate(y);	
					System.out.println(mutChrom.toString());
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
	
	private void crossover(){
		ArrayList<Chromosome> newGen = new ArrayList<Chromosome>(count);
		newGen.add(elite);
		for(int i = 1; i < count - 1; i++){
			newGen.add((CrossoverFunc.crossover(chooseRandom(), chooseRandom(), type)));
		}
		pop = newGen;
		pop.set(elite, 0);
	}
	
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
		findElite();
		printData();
		if(!generationCount()) return;
		crossover();
		mutate();
		generation++;
	}
	
	private void printData(){
		System.out.println("GENERATION: " + generation);
		System.out.println("MAX FITNESS:" + elite.getFitness());
		System.out.println("VALUE: " + elite.getValue());
		System.out.println(elite.toString());
	}
	
	private void foundMax(){
		System.out.println("\nSOLUTION FOUND");
		System.out.println("GENERATION: " + generation);
		System.out.println("MAX FITNESS:" + elite.getFitness());
		System.out.println("VALUE: " + elite.getValue());
		System.out.println(elite.toString());
	}
	
	
}
