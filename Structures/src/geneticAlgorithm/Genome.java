package geneticAlgorithm;

public interface Genome {

	//public void add(Object obj);
	public byte get(int index);
	public void randomize();
	public int size();
	public void mutate(int index);
	public void crossover(Genome x, Genome y, Crossover type);
	public byte[] getGenome();
}
