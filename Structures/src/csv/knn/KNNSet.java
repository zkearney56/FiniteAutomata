package csv.knn;

import csv.DataRow;
import csv.DataSet;
import list.ArrayList;
import csv.TrainingSet;

public class KNNSet extends TrainingSet{

	private ArrayList<KNN> knnList;
	
	public KNNSet(DataSet list, int trainingSize, Object[] args) {
		super(list, trainingSize, args);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String testRow(DataRow row) {
		double dist = 100;
		String determinedClass = "";
		for(int i = 0; i < knnList.size(); i++){
			double testPull = knnList.get(i).calcDistance(row);
			if(testPull < dist){
				dist = testPull;
				determinedClass = knnList.get(i).getClassification();
			}
		}
		return determinedClass;
	}

	@Override
	public void initSet() {
		knnList = new ArrayList<KNN>();
		for(int i = 0; i < trainingSet.size(); i++){
			knnList.add(new KNN(trainingSet.get(i)));
		}
	}

}
