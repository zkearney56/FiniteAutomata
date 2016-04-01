package geneticAlgorithm.Algorithm;

public class Fitness {

	public byte[] newByte = {};
	public boolean adjustSize = false;
	public double fitness = 0;
	
	public Fitness(double fit, boolean adjust, byte[] newByte){
		this.fitness = fit;
		this.adjustSize = adjust;
		this.newByte = newByte;
	}
	
	public Fitness(double fit){
		this.fitness = fit;
	}
	
	public double getFitness(){
		return fitness;
	}
	
	public boolean adjustSize(){
		return adjustSize;
	}
	
	public byte[] newByte(){
		return newByte;
	}
}
