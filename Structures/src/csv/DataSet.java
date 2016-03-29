package csv;

import java.io.File;
import java.io.FileReader;

import com.opencsv.CSVReader;

import list.ArrayList;

public class DataSet {
	
	private ArrayList<Attribute> attributes;
	private ArrayList<DataRow> dataRows;
	private ArrayList<String> headers;
	private ArrayList<Object> classifications;
	private String classification = "";
	
	public DataSet(){
		attributes = new ArrayList<Attribute>();
		dataRows = new ArrayList<DataRow>();
		headers = new ArrayList<String>();
		classifications = new ArrayList<Object>();
	}
	
	public ArrayList<String> getHeaders(){
		return headers;
	}
	
	public ArrayList<Object> getClassifications(){
		return classifications;
	}
	
	public ArrayList<DataRow> getDataRows(){
		return dataRows;
	}
	
	public ArrayList<Attribute> getAttributes(){
		return attributes;
	}
	
	public String getClassification(){
		return classification;
	}
	
	public void readFile(File file) {
		System.out.println(file.getAbsolutePath());
		try {
			CSVReader csvReader = new CSVReader(new FileReader(file));
			String[] headersArray = csvReader.readNext();
			headers = new ArrayList<String>(headersArray);
			String[] row = null;
			while ((row = csvReader.readNext()) != null) {
				dataRows.add(new DataRow(new ArrayList<Object>(row)));
				}
			csvReader.close();
			for (int i = 0; i < headers.size(); i++) {
				attributes.add(Attribute.generate(getType(i), headers.get(i)));
			}
			calculateAttributes();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void calculateAttributes(){
		for(int i = 0; i < attributes.size(); i++){
			attributes.get(i).calculateData(createColumn(i));
		}
	}

	private ArrayList<Object> createColumn(int index){
		ArrayList<Object> col = new ArrayList<Object>(dataRows.size());
		for(int i = 0; i < dataRows.size(); i++){
			col.add(dataRows.get(i).getVal(index));
		}
		return col;
	}
	
	public void normalize(){
		for(int i = 0; i < attributes.size(); i++){
			if(attributes.get(i) instanceof NumericAttribute){
			double min = attributes.get(i).getMin();
			double max = attributes.get(i).getMax() - min;
			for(int x = 0; x < dataRows.size(); x++){
				double curr = Double.parseDouble((String) dataRows.get(x).getVal(i));
				curr = (curr - min) / max;
				dataRows.get(x).setVal(curr, i);
			}
		}
		}
		attributes.clear();
		for (int i = 0; i < headers.size(); i++) {
			attributes.add(Attribute.generate(getType(i), headers.get(i)));
		}
		calculateAttributes();
	}
	
	public void setMainClassification(int column){
		classification = headers.get(column);
		headers.remove(column);
		classifications = attributes.get(column).getUniqueVals();
		attributes.remove(column);
		for(int i = 0; i < dataRows.size(); i++){
			dataRows.get(i).setClassification(column);
		}
	}
	
	public void eliminateNullVals(){
		for(int i = 0; i < attributes.size(); i++){
			if(attributes.get(i) instanceof NumericAttribute){
				for(int x = 0; x < dataRows.size(); x++){
				if(dataRows.get(x).getVal(i) == null){
					dataRows.get(x).setVal("NULL", i);
				}
				}
			}
			if(attributes.get(i) instanceof CategoricalAttribute){
				for(int x = 0; x < dataRows.size(); x++){
				if(dataRows.get(x).getVal(i) == null){
					dataRows.get(x).setVal("NULL", i);
				}
			}
		}
	}
	}
	
	private String getType(int column) {
		try {
			@SuppressWarnings("unused")
			double d = Double.parseDouble((String) dataRows.get(0).getVal(column));
		} catch (NumberFormatException nfe) {
			return "Categorical";
		}
		return "Numeric";
	}
	
}
