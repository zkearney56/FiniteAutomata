package genalg;

import java.time.LocalTime;

import genalg.algorithm.Algorithm;
import genalg.algorithm.AlgorithmEnum;
import genalg.algorithm.CrossoverEnum;
import genalg.algorithm.GeneticFunc;
import list.ArrayList;

public class GeneticPopulation implements Population {

	private double MUT_COEF = .005; // Mutation coefficient
	private int MAX_NUM_GEN = 200; // Maximum number of generations

	private CrossoverEnum crossType = CrossoverEnum.EVERY_OTHER;
	private AlgorithmEnum alg = AlgorithmEnum.BYTE_ALG1;

	private double mutCount = 0, currentMaxFit = 0, maxFitness = 0;
	private int size = 0, generation = 0, geneLength = 0, eliteCount = 1;
	private ArrayList<Genome> pop;
	private ArrayList<Genome> elite;
	private LocalTime time;
	private double runTime = 0;

	public GeneticPopulation(int size, AlgorithmEnum alg, CrossoverEnum type) {
		this.size = size;
		this.geneLength = Algorithm.geneLength(alg);
		this.alg = alg;
		this.crossType = type;
		setMutCoef(MUT_COEF);
	}

	@Override
	public final void setMaxGen(int maxGen) {
		MAX_NUM_GEN = maxGen;
	}

	@Override
	public final void setMutCoef(double mutcoef) {
		MUT_COEF = mutcoef;
	}

	@Override
	public final void incMutCount() {
		mutCount += (size * geneLength) * MUT_COEF;
	}

	@Override
	public final void decMutCount() {
		mutCount--;
	}

	@Override
	public final double getMutCoef() {
		return MUT_COEF;
	}

	@Override
	public final int getMaxGen() {
		return MAX_NUM_GEN;
	}

	@Override
	public final double getMutCount() {
		return mutCount;
	}

	@Override
	public final Genome getGenome(int index) {
		return pop.get(index);
	}

	@Override
	public final void setGenome(Genome e, int index) {
		pop.set(e, index);
	}

	@Override
	public final void addGenome(Genome e) {
		pop.add(e);
	}

	@Override
	public final void addGenome(Genome e, int index) {
		pop.add(index, e);
	}

	@Override
	public final Genome removeGenome(int index) {
		return pop.remove(index);
	}

	@Override
	public final int getGeneLength() {
		return geneLength;
	}

	@Override
	public final void setGeneLength(int geneLength) {
		this.geneLength = geneLength;
	}

	@Override
	public final int getGeneration() {
		return generation;
	}

	@Override
	public final double getRunTime() {
		return runTime;
	}

	@Override
	public final double getAvgRunTime() {
		return runTime / generation;
	}

	@Override
	public final ArrayList<Genome> getAllElite() {
		return elite;
	}

	@Override
	public final void setEliteCount(int num) {
		if (num <= num) {
			eliteCount = 1;
		} else if (num >= size) {
			eliteCount = size - 1;
		} else {
			eliteCount = size;
		}
	}

	@Override
	public final int getEliteCount() {
		return eliteCount;
	}

	@Override
	public final int size() {
		return size;
	}

	@Override
	public final void setCurrentMaxFit(double fit) {
		currentMaxFit = fit;
	}

	@Override
	public final void setElite(Genome newElite) {
		GeneticFunc.setElite(this, newElite);
	}

	@Override
	public final Genome getElite() {
		return elite.get(0);
	}

	@Override
	public final AlgorithmEnum getAlg() {
		return alg;
	}

	@Override
	public final CrossoverEnum getCross() {
		return crossType;
	}

	@Override
	public final double getEliteFitness() {
		return currentMaxFit;
	}

	@Override
	public final void addElite(Genome newElite) {
		if (elite.size() > eliteCount) {
			return;
		} else {
			elite.add(newElite);
		}
	}

	@Override
	public final void execute() {
		maxFitness = Algorithm.getMaxFitness(alg);
		elite = new ArrayList<Genome>(eliteCount);
		pop = new ArrayList<Genome>(size);
		GeneticFunc.fillPopulation(this);
		time = LocalTime.now();
		runTime = time.toNanoOfDay();
		while (generation < MAX_NUM_GEN && currentMaxFit < maxFitness) {
			newGeneration();
		}
		time = LocalTime.now();
		runTime = (time.toNanoOfDay() - runTime) / 1000000000;
	}

	private final void newGeneration() {

		GeneticFunc.crossover(this);
		GeneticFunc.calcFitness(this);
		GeneticFunc.findElite(this);
		GeneticFunc.mutate(this);
		GeneticFunc.calcFitness(this);
		GeneticFunc.findElite(this);
		GeneticFunc.adjustGenomeLength(this);
		GeneticFunc.calcFitness(this);
		GeneticFunc.findElite(this);
		generation++;
	}

}
