package csv;

import list.ArrayList;

public abstract class Attribute {

	protected double min, max, mean, stdDev;
	protected String name;
	protected ArrayList<Object> uniqueVals;
	
	public Attribute(String name){
		this.name = name;
		uniqueVals = new ArrayList<Object>();
	}
	
	public ArrayList<Object> getUniqueVals(){
		return uniqueVals;
	}
	
	public double getMin(){
		return min;
	}
	
	public double getMax(){
		return max;
	}
	
	public double getMean(){
		return mean;
	}
	
	public double getStdDev(){
		return stdDev;
	}
	
	public String getName(){
		return name;
	}
	
	public abstract void calculateData(ArrayList<Object> data);
	
	public static Attribute generate(String type, String header){
		if(type.equals("Numeric")){
				NumericAttribute att = new NumericAttribute(header);	
				return att;
		}
		else if(type.equals("Categorical")){
			CategoricalAttribute att = new CategoricalAttribute(header);
			return att;
		}
		return null;
	}
}
