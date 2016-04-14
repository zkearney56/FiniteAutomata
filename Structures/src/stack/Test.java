package stack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import list.ArrayList;

public class Test {

	public static final String dir = System.getProperty("user.dir").concat("\\palinString.txt");
	private ArrayList<String> testVals;
	private File testFile = null;
	private final static Character pound = '#';
	private final static Character a = 'a';
	private final static Character b = 'b';
	
	public static void main(String args[]){
		
		System.out.println(dir);
		File f = new File(dir);
		new Test(f);
	}
	
	public Test(File f){
		
		testVals = new ArrayList<String>();
		testFile = f;
		try {
			load(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(String arr : testVals){
			System.out.println(arr);
		}
		run();
	}
	
	public void run(){
		for(int i = 0; i < testVals.size(); i++){
			if(!testVals.get(i).equals("0")){
				System.out.println(testVals.get(i));
				StackList<Character> stack = new StackList<Character>();
				Character[] charObjectArray = testVals.get(i).chars()
                        .mapToObj(c -> (char)c)
                        .toArray(Character[]::new);
				boolean push = true;
				boolean accepted = true;
				for(Character val : charObjectArray){
					if(push){
						if(val.equals(pound)){
							push = false;
						}
						else{
							stack.push(val);
						}
					}
					else{
						if(stack.isEmpty()){
							accepted = false;
							System.out.println("Break");
						}
						else if(stack.peek().equals(val)){
							stack.pop();
						}
						else{
							accepted = false;
							System.out.println("Break");
						}
					}
				}
				if(!stack.isEmpty()){
					System.out.println("Rejected");
					accepted = false;
				}
				if(accepted){
					System.out.println("Accepted");
				}
			}
		}
	}
	
	public void load(File f) throws IOException{
		FileInputStream fis = new FileInputStream(f);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		String line = null;
		while((line = br.readLine()) != null){
			line = line.toLowerCase();
			testVals.add(line);
		}
		br.close();
		int index = 0;
		System.out.println("Test");
		for(String str : testVals){
			for(char val : str.toCharArray()){
				if(!(val == 'a' || val == 'b' || val == '#')){
					testVals.set("0", index);
					break;
				}
			}
			index++;
		}
		
	}
}
