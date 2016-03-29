package csv;

import java.io.File;
import java.time.LocalTime;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import csv.cluster.ClusterSet;
import csv.knn.KNNSet;
import list.ArrayList;
import math.MathFunctions;

public class Test {
	
	private static DataSet list;

	public static void main(String args[]){
		File file = getFile();
		list = new DataSet();
		list.readFile(file);
		list.setMainClassification(4);
		list.eliminateNullVals();
		list.normalize();
		Object[] setArgs = {3};
		TrainingSet trainingset = new ClusterSet(list, 40, setArgs);
		System.out.println(trainingset.testData());
		testIterations(list, 40, 2000, setArgs);
		//Algorithm knn = new KNNAlgorithm(list);
		//cluster.testIterations(75, 2000, 3);
		//knn.testIterations(50, 500, 10);
	}
	
	private static File getFile(){
		
		File file = new File("null");
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV File", "csv");
		fc.addChoosableFileFilter(filter);
		fc.setDialogTitle("Import from CSV");
		fc.setApproveButtonText("Open");
		fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
		int returnVal = fc.showOpenDialog(fc);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();
			return file;
		}
		return file;
	}
	
	public static void testIterations(DataSet set, int trainingSize, int iterationCount, Object[] args) {
		System.out.println("---------------------------------");
		LocalTime currTime = LocalTime.now();
		double time = currTime.toNanoOfDay();
		ArrayList<Double> percentages = new ArrayList<Double>();
		double percentMult = .1;
		int currPercent = (int)(iterationCount*percentMult);
		int current = 0;
		for(int i = 0; i < iterationCount; i++){
			if(i == currPercent){
				current = current+10;
				percentMult = percentMult + .1;
				System.out.println(current + "% completed");
				currPercent = (int)(iterationCount*percentMult);
			}
			TrainingSet trainingset = new ClusterSet(list, trainingSize, args);
			percentages.add(trainingset.testData());
		}
		output(set, percentages, time, trainingSize, iterationCount);
	}
	
	private static void output(DataSet set, ArrayList<Double> percentages, double time, int size, int count){
		double min = MathFunctions.min(percentages);
		double max = MathFunctions.max(percentages);
		double avg = MathFunctions.mean(percentages);
		double stdDev = MathFunctions.stdDev(percentages);
		LocalTime currTime = LocalTime.now();
		time = (currTime.toNanoOfDay() - time)/1000000000;
		System.out.println("---------------------------------");
		System.out.println("CLASSIFICATION: " + set.getClassification());
		System.out.println("ATTRIBUTES : " + set.getHeaders().size());
		System.out.println("ITERATIONS: " + count);
		System.out.println("TRAINING SIZE: " + size);
		System.out.println("TOTAL SIZE: " + set.getDataRows().size());
		System.out.println("MIN: " + min + " %");
		System.out.println("MAX: " + max + " %");
		System.out.println("AVG: " + avg + " %");
		System.out.println("STDDEV: " + stdDev + " %");
		System.out.println("RUNTIME: " + time + " seconds");
		System.out.println("AVG RUNTIME: " + time/count + " seconds");
		System.out.println("---------------------------------");
	}

	}
