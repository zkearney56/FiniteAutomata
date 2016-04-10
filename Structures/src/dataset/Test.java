package dataset;

import java.io.File;
import java.time.LocalTime;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import classification.cluster.ClusterSet;
import classification.knn.KNNSet;
import list.ArrayList;
import math.MathFunctions;

public class Test {
	
	private static DataSet DATASET;
	private static int TRAINING_SIZE = 40;
	private static int NUM_ITERATIONS = 200;
	private static int MAIN_CLASSIFICATION = 4;
	private final static Object[] ARGS = {3};
	private static File file = null;
	private final static Algorithm[] types = {Algorithm.CLUSTER, Algorithm.KNN};

	public static void main(String args[]){
		
		Scanner sc = new Scanner(System.in);
		file = getFile();
		
		if(file == null){
			return;
		}
		
		DATASET = new DataSet();
		DATASET.readFile(file);
		
		System.out.println("DATASET SIZE: " + DATASET.getNumRows());
		
		System.out.println("CHOOSE TRAINING SIZE");
			TRAINING_SIZE = -1;
			TRAINING_SIZE = sc.nextInt();
			while(TRAINING_SIZE < 0 || TRAINING_SIZE >= DATASET.getNumRows()){
				System.out.println("ERROR: ENTER A VALID TRAINING SIZE");
				TRAINING_SIZE = sc.nextInt();
			}
		
		System.out.println("CHOOSE NUMBER OF ITERATIONS");
			NUM_ITERATIONS = -1;
			NUM_ITERATIONS = sc.nextInt();
			while(NUM_ITERATIONS < 0){
				System.out.println("ERROR: ENTER A VALID NUMBER OF ITERATIONS SIZE");
				NUM_ITERATIONS = sc.nextInt();
			}
		
		System.out.println("CHOOSE MAIN CLASSIFICATION");
			MAIN_CLASSIFICATION = -1;
			for(int i = 0 ; i < DATASET.getHeaders().size(); i++){
				System.out.println(i + " " + DATASET.getHeaders().get(i));
			}
			MAIN_CLASSIFICATION = sc.nextInt();
			while(MAIN_CLASSIFICATION < 0 || MAIN_CLASSIFICATION >= DATASET.getHeaders().size()){
				System.out.println("ERROR: ENTER A VALID INT VALUE");
				MAIN_CLASSIFICATION = sc.nextInt();
			}
		
		sc.close();
		
		DATASET.setMainClassification(MAIN_CLASSIFICATION);
		DATASET.eliminateNullVals();
		DATASET.normalize();
		
		for(int i = 0; i < types.length; i++){
			testIterations(types[i]);
		}

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
		else{
			return null;
		}
	}
	
	private static void testIterations(Algorithm type) {
		System.out.println("---------------------------------");
		LocalTime currTime = LocalTime.now();
		double time = currTime.toNanoOfDay();
		ArrayList<Double> percentages = new ArrayList<Double>();
		double percentMult = .1;
		int currPercent = (int)(NUM_ITERATIONS*percentMult);
		int current = 0;
		for(int i = 0; i < NUM_ITERATIONS; i++){
			if(i == currPercent){
				current = current+10;
				percentMult = percentMult + .1;
				System.out.println(current + "% completed");
				currPercent = (int)(NUM_ITERATIONS*percentMult);
			}
			TrainingSet trainingset = null;
			switch(type){
			case CLUSTER:{
				trainingset = new ClusterSet(DATASET, TRAINING_SIZE, ARGS);
				break;
			}
			case KNN:{
				trainingset = new KNNSet(DATASET, TRAINING_SIZE, ARGS);
				break;
			}
			}
			percentages.add(trainingset.testData());
		}
		output(type, percentages, time);
	}
	
	private static void output(Algorithm type, ArrayList<Double> percentages, double time){
		double min = MathFunctions.min(percentages);
		double max = MathFunctions.max(percentages);
		double avg = MathFunctions.mean(percentages);
		double stdDev = MathFunctions.stdDev(percentages);
		LocalTime currTime = LocalTime.now();
		time = (currTime.toNanoOfDay() - time)/1000000000;
		System.out.println("---------------------------------");
		System.out.println("ALGORITHM: " + type);
		System.out.println("CLASSIFICATION: " + DATASET.getClassification());
		System.out.println("ATTRIBUTES : " + DATASET.getHeaders().size());
		System.out.println("ITERATIONS: " + NUM_ITERATIONS);
		System.out.println("TRAINING SIZE: " + TRAINING_SIZE);
		System.out.println("TOTAL SIZE: " + DATASET.getDataRows().size());
		System.out.println("MIN: " + min + " %");
		System.out.println("MAX: " + max + " %");
		System.out.println("AVG: " + avg + " %");
		System.out.println("STDDEV: " + stdDev + " %");
		System.out.println("RUNTIME: " + time + " seconds");
		System.out.println("AVG RUNTIME: " + time/NUM_ITERATIONS + " seconds");
		System.out.println("---------------------------------");
	}

	}
