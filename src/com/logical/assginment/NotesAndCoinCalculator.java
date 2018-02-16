/**
 * 
 */
package com.logical.assginment;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Rujuta Manjrekar
 *
 */
public class NotesAndCoinCalculator {

	
	/**
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		System.out.println("Please enter amount: ");
		Scanner scanner = new Scanner(System.in);
		String value = scanner.nextLine();

		Map<Float, Integer> ansMap = new LinkedHashMap<>();
		// Initializing the bag as per given problem statement
		Map<Float, Integer> map = new LinkedHashMap<>();
		map.put(1000f, 20);
		map.put(500f, 20);
		map.put(100f, 20);
		map.put(50f, 20);
		map.put(20f, 20);
		map.put(10f, 20);
		map.put(5f, 20);
		map.put(1f, 20);
		map.put(0.5f, 20);
		map.put(0.25f, 20);
		
		Float amount = new Float(value);
		Set<Float> keys = map.keySet();
		float availableAmnt = 0.0f;
		for (Float key : keys) {
			if (key >= 1) {
				int note = key.intValue();
				int count = map.get(key);
				availableAmnt = availableAmnt + note * count;
			} else {
				int count = map.get(key);
				availableAmnt = availableAmnt + key * count;
			}			
		}
		System.out.println("Available amount in bag: " + availableAmnt);
		System.out.println("Inserted amount: " + amount);
		if (availableAmnt >= amount) {
			for (Float noteCoinVal : keys) {
				if (amount > 0) {
					int count = (int) (amount / noteCoinVal);
					int remainingCount = map.get(noteCoinVal);
					
					if (remainingCount > count && count > 0) {
						ansMap.put(noteCoinVal, count);
						int newCount = remainingCount - count;
						map.put(noteCoinVal, newCount);
						amount = amount % noteCoinVal;
					} else if (count > 0) {
						if (noteCoinVal >= 1) {
							int amnt = remainingCount * noteCoinVal.intValue();
							amount = amount - amnt;
							ansMap.put(noteCoinVal, remainingCount);
							map.put(noteCoinVal, 0);	
						} else {
							float amnt = remainingCount * noteCoinVal;
							amount = amount - amnt;
							ansMap.put(noteCoinVal, remainingCount);
							map.put(noteCoinVal, 0);	
						}
					}
				}
			}
		} else {
			System.out.println("You do not have sufficient notes & coins in bag");
			System.exit(0);	
		}
		System.out.println("Currency Note --> Count --> Total");
		Set<Float> floats = ansMap.keySet();
		int noteCount = 0;
		double totalCount = 0;
		for (Float key : floats) {
			if (key >= 1) {
				int note = key.intValue();
				int count = ansMap.get(key);
				totalCount = totalCount + note * count;
				noteCount = noteCount + count;
				System.out.println("Rs " + note + " -->\t" + count + " -->\t" + note + "X" + count);
			} else {
				int count = ansMap.get(key);
				totalCount = totalCount + key * count;
				noteCount = noteCount + count;
				System.out.println("Paise " + key + " -->\t" + count + " -->\t" + key + "X" + count);
			}
		}
		System.out.println("Total -->\t" + noteCount + " -->\t" + totalCount);
	}

}
