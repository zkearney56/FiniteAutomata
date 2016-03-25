package geneticAlgorithm;

public interface Genome {

	public void add(Object obj);
	public Object get(int index);
	public void randomize();
	public int size();
	public void mutate(int index);
	public int getIntVal();
	
}
