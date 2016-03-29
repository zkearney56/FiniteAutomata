package classification.cluster;

import list.ArrayList;

public abstract class ClusterAttribute {

	protected double pullStrength = 0;
	
	public ClusterAttribute(ArrayList<Object> data) {
	}
	
	public abstract double calculatePull(Object val);
	
	public double getPullStrength(){
		return pullStrength;
	}
	
	public static ClusterAttribute generate(ArrayList<Object> vals, int numStdDev){
		try{
			double d = Double.parseDouble((String) vals.get(0));
			return new NumericClusterAttribute(vals,numStdDev);
		} catch(NumberFormatException nfe){
			return new CategoricalClusterAttribute(vals);
		}		
	}
}
