package csv;

import list.ArrayList;

public abstract class TrainingSet {

	protected ArrayList<DataRow> trainingSet;
	protected ArrayList<DataRow> testSet;
	protected ArrayList<Object> classifications;
	protected Object[] args;
	protected int trainingSize = 0;
	
	public TrainingSet(DataSet set, int trainingSize, Object[] args){
		this.classifications = set.getClassifications();
		ArrayList<DataRow> data = new ArrayList<DataRow>(set.getDataRows());
		this.args = args;
		this.trainingSize = trainingSize;
		data.shuffle();
		trainingSet = new ArrayList<DataRow>(trainingSize);
		for(int i = 0; i < trainingSize; i++){
			trainingSet.add(data.remove(i));
		}
		testSet = new ArrayList<DataRow>(data.size());
		for(int i = 0; i < data.size(); i++){
			testSet.add(data.remove(i));
		}
		initSet();
	}
	
	public final double testData(){
		double percent = 0;
		for(int i = 0; i < testSet.size(); i++){
			DataRow testRow = testSet.get(i);
			String currClass = testRow.getClassification();
			String testClass = testRow(testRow);
			if(currClass.equals(testClass)){
				percent++;
			}
		}
		percent = (percent * 100)/testSet.size();
		return percent;
	}

	public abstract String testRow(DataRow testRow);
	
	public abstract void initSet();
	
}
