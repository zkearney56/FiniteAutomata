package csv;

import java.util.Iterator;
import list.ArrayList;
import math.MathFunctions;

public class CategoricalAttribute extends Attribute{

	private ArrayList<CategoricalPoint> points;
	
	public CategoricalAttribute(String name){
		super(name);	
	}
	
	class CategoricalPoint {

		private String name;
		private int count;

		public CategoricalPoint(String name) {
			this.name = name;
			count = 1;
		}

		public CategoricalPoint(CategoricalPoint point) {
			this.name = point.name;
			this.count = point.count;
		}

		public void increment() {
			count++;
		}

		public int getCount() {
			return count;
		}

		public String getName() {
			return name;
		}
	}

	@Override
	public void calculateData(ArrayList<Object> data) {	
		points = new ArrayList<CategoricalPoint>();
		Iterator<Object> itr = data.iterator();
		while(itr.hasNext()){
			boolean found = false;
			String base = (String) itr.next();
			for(int i = 0; i < points.size(); i++){
				CategoricalPoint currPoint = points.get(i);
				if(base.equals(currPoint.getName())){
					currPoint.increment();
					found = true;
					break;
				}
			}
			if(!found){
				points.add(new CategoricalPoint(base));
			}
		}
		ArrayList<Double> counts = new ArrayList<Double>(points.size());
		for(int i = 0; i < points.size(); i++){
			CategoricalPoint currPoint = points.get(i);
			uniqueVals.add(currPoint.getName());
			counts.add((double)currPoint.getCount());
		}
		min = MathFunctions.min(counts);
		max = MathFunctions.max(counts);
		mean = MathFunctions.mean(counts);
		stdDev = MathFunctions.stdDev(counts);
	}
}
