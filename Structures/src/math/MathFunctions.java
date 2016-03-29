package math;


import java.math.BigDecimal;
import java.math.RoundingMode;
import list.ArrayList;
/**
 * Math functions for use with large amounts of data.
 * 
 * @author Zachary Kearney
 * 
 * Last Edited: 11/30/2015
 */
public final class MathFunctions {

	/**
	 * Returns the min from the arraylist of doubles.
	 * 
	 * @param vals
	 *            DMArrayList containing all values
	 * @return returns the maximum
	 */

	public static double min(ArrayList<Double> vals) {
		double min = vals.get(0);
		for (int i = 0; i < vals.size(); i++) {
			if (vals.get(i) < min)
				min = vals.get(i);
		}
		return min;
	}

	/**
	 * Returns the max from the arraylist of doubles.
	 * 
	 * @param vals
	 *            DMArrayList containing all values
	 * @return returns the minimum
	 */

	public static double max(ArrayList<Double> vals) {
		double max = vals.get(0);
		for (int i = 0; i < vals.size(); i++) {
			if (vals.get(i) > max)
				max = vals.get(i);
		}
		return max;
	}

	/**
	 * Returns the mean of the arraylist of doubles.
	 * 
	 * @param vals
	 *            DMArrayList containing all values
	 * @return returns the mean
	 */

	public static double mean(ArrayList<Double> vals) {
		BigDecimal mean = new BigDecimal(0.0);
		for (int i = 0; i < vals.size(); i++) {
			try{
			mean = mean.add(BigDecimal.valueOf(vals.get(i)));
			} catch(NumberFormatException e){
				mean = mean.add(BigDecimal.valueOf(0));
			}
		}
		mean = mean.divide(BigDecimal.valueOf(vals.size()), 8, RoundingMode.HALF_UP);
		return Double.parseDouble(mean.toString());
	}

	/**
	 * Returns the stdDev from the arraylist of doubles
	 * 
	 * @param mean
	 *            Mean of the values
	 * @param vals
	 *            DMArrayList containing all values
	 * @return returns the standard deviation
	 */

	public static double stdDev(double mean, ArrayList<Double> vals) {
		BigDecimal stdDev = new BigDecimal("0.0");
		double val;
		for (int i = 0; i < vals.size(); i++) {
			val = vals.get(i) - mean;
			val = val * val;
			stdDev = stdDev.add(BigDecimal.valueOf(val));
		}
		stdDev = stdDev.divide(BigDecimal.valueOf(vals.size()), 15, RoundingMode.HALF_UP);
		return Double.parseDouble(stdDev.toString());
	}

	/**
	 * Returns the stdDev from the ArrayList of doubles. Faster to use if mean
	 * is already known.
	 * 
	 * @param vals
	 *            DMArrayList containing the values
	 * @return returns the standard deviation
	 */
	public static double stdDev(ArrayList<Double> vals) {
		double mean = mean(vals);
		BigDecimal stdDev = new BigDecimal("0.0");
		for (int i = 0; i < vals.size(); i++) {
			double val = vals.get(i);
			val = val - mean;
			val = val * val;
			try{
			stdDev = stdDev.add(BigDecimal.valueOf(val));
			}
			catch(NumberFormatException e){
				stdDev = stdDev.add(BigDecimal.valueOf(0));
			}
		}
		stdDev = stdDev.divide(BigDecimal.valueOf(vals.size()), 15, RoundingMode.HALF_UP);
		double returnVal = Double.parseDouble(stdDev.toString());
		returnVal = Math.sqrt(returnVal);
		return returnVal;
	}
	
	public static double median(ArrayList<Double> vals){
		vals.sort();
		int val = (int)((double)vals.size()/2);
		return vals.get(val);
	}
}
