package csv;

import list.ArrayList;

public class DataRow {
	
	private ArrayList<Object> data;
	private String classification = "";
	
	public DataRow(ArrayList<Object> data){
		this.data = data;
	}
	
	public DataRow(DataRow clone){
		this.data = new ArrayList<Object>(clone.data.toArray());
	}
	
	public ArrayList<Object> getData(){
		return data;
	}
	
	public void removeColumn(int index){
		data.remove(index);
	}
	
	public Object getVal(int index){
		return data.get(index);
	}
	
	public void swap(int index1, int index2){
		data.swap(index1, index2);
	}
	
	public void setClassification(int index){
		classification = (String)data.get(index);
		data.remove(index);
	}
	
	public String getClassification(){
		return classification;
	}
	
	public void setVal(Object val, int index){
		data.set(val.toString(), index);
	}
	
	public int size(){
		return data.size();
	}
}