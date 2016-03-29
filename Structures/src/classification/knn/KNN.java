package classification.knn;

import dataset.DataRow;

public class KNN {

	private DataRow data;
	
	public KNN(DataRow data){
		this.data = data;
	}
	
	public double calcDistance(DataRow input){
		double returnVal = 0;
		for(int i = 0; i < input.size(); i++){
			double datVal = Double.parseDouble((String) data.getVal(i)) - Double.parseDouble((String)input.getVal(i));
			if(datVal < 0){
				datVal = -datVal;
			}
			returnVal = returnVal + datVal;
		}
		return returnVal;
	}
	
	public String getClassification(){
		return data.getClassification();
	}
}
