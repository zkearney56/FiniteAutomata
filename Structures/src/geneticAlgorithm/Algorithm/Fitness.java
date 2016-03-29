package geneticAlgorithm.Algorithm;

public class Fitness {

	public byte[] newByte = {};
	public boolean adjustSize = false;
	public int fitness = 0;
	
	public Fitness(int fit, boolean adjust, byte[] newByte){
		this.fitness = fit;
		this.adjustSize = adjust;
		this.newByte = newByte;
	}
	
	public Fitness(int fit){
		this.fitness = fit;
	}
	
	public int getFitness(){
		return fitness;
	}
	
	public boolean adjustSize(){
		return adjustSize;
	}
	
	public byte[] newByte(){
		return newByte;
	}
}
