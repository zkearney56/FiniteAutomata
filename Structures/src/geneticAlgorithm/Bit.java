package geneticAlgorithm;

public class Bit {
	
		byte dat;
		
		Bit(){
			dat = randomBit();
		}
		
		void flip(){
			if (dat == 0){
				dat = (byte) 1;
			}
			else{
				dat = (byte) 0;
			}
		}	
		
		byte get(){
			return dat;
		}
		
		private byte randomBit(){
			byte returnVal;
			if(Math.random() < .5){
				returnVal = (byte) 0;
			}
			else{
				returnVal = (byte) 1;
			}
			return returnVal;
		}
	}