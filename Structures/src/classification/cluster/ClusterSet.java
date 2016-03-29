package classification.cluster;

import java.util.Iterator;

import dataset.DataRow;
import dataset.DataSet;
import dataset.TrainingSet;
import list.ArrayList;

public class ClusterSet extends TrainingSet{
	
	private ArrayList<Cluster> clusters;

	public ClusterSet(DataSet list, int trainingSize, Object[] args) {
		super(list, trainingSize, args);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initSet() {
		int numStdDev = (int)args[0];
		clusters = new ArrayList<Cluster>();
		for(int i = 0; i < classifications.size(); i++){
			String currClass = (String) classifications.get(i);
			ArrayList<DataRow> rows = new ArrayList<DataRow>();
			Iterator<DataRow> itr = trainingSet.iterator();
			while(itr.hasNext()){
				DataRow row = itr.next();
				if(row.getClassification().equals(currClass)){
					rows.add(row);
				}
			}
			clusters.add(new Cluster(currClass, rows, numStdDev));
		}
	}

	
	public String testRow(DataRow row){
		double pull = 0;
		String determinedClass = "";
		for(int i = 0; i < clusters.size(); i++){
			double testPull = clusters.get(i).calculatePull(row);
			if(testPull > pull){
				pull = testPull;
				determinedClass = clusters.get(i).getClassification();
			}
		}
		return determinedClass;
	}

}
