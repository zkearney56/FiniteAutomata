package geneticAlgorithm;

import java.util.Random;

import list.ArrayList;

public class Population {

	private double mutCount = 0;
	private static double MUT_COEF = .015;
	private int totalFitness = 0;
	
	private ArrayList<Chromosome> pop;
	public Population(int count, int geneLength){
		pop = new ArrayList<Chromosome>(count);
		for(int i = 0; i < count; i++){
			pop.add(new Chromosome(geneLength));
		}
		mutCount = (count * geneLength) * MUT_COEF;
		System.out.println("NUM MUTATIONS: " + mutCount);
	}
	
	public void mutate(){
		int x = 0;
		while(x < mutCount){
			if(mutateLoop()){
				x++;
			}
		}
	}
	
	private boolean mutateLoop(){
		for(int i = 0; i < pop.size(); i++){
			Chromosome mutChrom = pop.get(i);
			for(int y = 0; y < mutChrom.getSize(); y++){
				if(Math.random() < .015){
					mutChrom.mutate(y);			
					return true;
				}
			}
		}
		return false;
	}
	
	private void calculateTotalFitness(){
		for(int i = 0; i < pop.size(); i++){
			totalFitness += pop.get(i).getFitness();
		}
	}
	
	private Chromosome chooseRandom(){
		Random rand = new Random();		
		int prob = rand.nextInt(totalFitness);
		int probIndex = 0;
		while(true){
			int randIndex = rand.nextInt(pop.size());
			probIndex += pop.get(randIndex).getFitness();
			if(probIndex >= prob){
				return pop.get(probIndex);
			}
		}
	}
}
