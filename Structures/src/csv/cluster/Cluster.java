package csv.cluster;

import csv.DataRow;
import list.ArrayList;

public class Cluster {

	private String classification = "";
	private ArrayList<ClusterAttribute> attributes;
	
	public Cluster(String classification, ArrayList<DataRow> rows, int numStdDev) {
		attributes = new ArrayList<ClusterAttribute>();
		this.classification = classification;
		for(int x = 0; x < rows.get(0).size(); x++){
			ArrayList<Object> vals = new ArrayList<Object>();
		for(int i = 0; i < rows.size(); i++){
			Object addVal = rows.get(i).getVal(x);
			if(!addVal.equals("NULL")){
				vals.add(addVal);
			}			
		}
		attributes.add(ClusterAttribute.generate(vals, numStdDev));
		}
		// TODO Auto-generated constructor stub
	}
	
	public String getClassification(){
		return classification;
	}
	
	public double calculatePull(DataRow row){
		double pull = 0;
		for(int x = 0; x < row.size(); x++){
			Object testVal = row.getVal(x);
			pull = pull + attributes.get(x).calculatePull(testVal);
		}
		return pull;
	}
	
	public double pullStrength(int column){
		return attributes.get(column).getPullStrength();
	}
}
