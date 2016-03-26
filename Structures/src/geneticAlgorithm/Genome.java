package geneticAlgorithm;

public interface Genome extends Cloneable{

	//public void add(Object obj);
	public byte get(int index);
	public void randomize();
	public int size();
	public void mutate(int index);
	public byte[] getGenome();
	public void crossover(Genome mate, CrossoverEnum type, int arg1, int arg2);
	public Genome clone();
}
