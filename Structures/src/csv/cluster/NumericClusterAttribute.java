package csv.cluster;

import list.ArrayList;
import math.MathFunctions;

public class NumericClusterAttribute extends ClusterAttribute{

	private double stdDev, mean;
	private int numStdDev;
	private ArrayList<AttributeNode> dataNodes;
	
	public NumericClusterAttribute(ArrayList<Object> data, int numStdDev){
		super(data);
		this.numStdDev = numStdDev;
		ArrayList<Double> doubleData = new ArrayList<Double>();
		for(int i = 0; i < data.size(); i++){
			if(!data.get(i).equals("NULL")){
			doubleData.add(Double.parseDouble((String)data.get(i)));
			}
		}
		dataNodes = new ArrayList<AttributeNode>();
		stdDev = MathFunctions.stdDev(doubleData);
		mean = MathFunctions.mean(doubleData);
		createNodes(doubleData);
	}
	
	private void createNodes(ArrayList<Double> data){
		for(int i = 0; i < data.size(); i++){
			dataNodes.add(new AttributeNode(data.get(i)));
			}
		pullStrength = pullStrength / data.size();
		}
	
	public double calculatePull(Object val){
		if(val.equals("NULL")){
			return 0;
		}
		double curr = Double.parseDouble((String)val);
		double pull = 0;
		for(int i = 0; i < dataNodes.size(); i++){
			double newVal = dataNodes.get(i).val;
			double pullCoEf = dataNodes.get(i).pullCoEf;
			double pullAdd = 0;
			newVal = newVal - curr;
			if(newVal < 0){
				newVal = newVal * -1;
			}
			//double newPull = newVal/(stdDev*numStdDev);
			//pullAdd = (1-(newPull*newPull*newPull));
			pullAdd = pullCalc(newVal);
			if(pullAdd < 0){
				pullAdd = 0;
			}
			if(pullAdd > 1){
				pullAdd = 1;
			}
				pull = pull +  pullAdd * pullCoEf;
		}
		
		pull = pull/dataNodes.size();
		return pull;
	}
	
	private double pullCalc(double val){
		double newPull = val/(stdDev*numStdDev);
		return (1-(newPull*newPull*newPull));
	}
	
	class AttributeNode{
		
		double val;
		double pullCoEf = 1;
		double z = 1/mean;
		
		public AttributeNode(double val){
			double diff = 0;
			this.val = val;
			diff = mean - val;
			if(diff < 0){
				diff = diff * -1;
			}
			//double newPull = diff/(stdDev*numStdDev);
			//pullCoEf = (1-(newPull*newPull*newPull));
			pullCoEf = pullCalc(diff);
			if(pullCoEf < 0){
				pullCoEf = 0;
			}
			if(pullCoEf > 1){
				pullCoEf = 1;
			}
			pullStrength = pullStrength + pullCoEf;
		}
		}
}
