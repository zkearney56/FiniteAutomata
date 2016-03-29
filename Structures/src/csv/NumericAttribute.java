package csv;

import java.util.Iterator;

import list.ArrayList;
import math.MathFunctions;

public class NumericAttribute extends Attribute{

	public NumericAttribute(String name){
		super(name);
	}

	@Override
	public void calculateData(ArrayList<Object> data) {
		ArrayList<Double> convertedData = new ArrayList<Double>(data.size());
		Iterator<Object> itr = data.iterator();
		while(itr.hasNext()){
			convertedData.add(Double.parseDouble((String)itr.next()));
		}
		min = MathFunctions.min(convertedData);
		max = MathFunctions.max(convertedData);
		mean = MathFunctions.mean(convertedData);
		stdDev = MathFunctions.stdDev(convertedData);
		for(Double doub : convertedData){
			if(!uniqueVals.contains(doub)){
				uniqueVals.add(doub);
			}
		}
	}

}