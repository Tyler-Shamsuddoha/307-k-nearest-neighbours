
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

	private static ArrayList<IrisNode> training = new ArrayList<IrisNode>();
	private static ArrayList<IrisNode> test = new ArrayList<IrisNode>();
	public static void main(String[] args) {
		loadTraining(new File("iris-training.txt"));
		loadTest(new File("iris-test.txt"));
		chooseKValue();
	}
	
	private static void chooseKValue() {
		boolean quit = false;
		Scanner scan = new Scanner(System.in);
		System.out.println("\nEnter an integer to use as the k-value:");
		while(!quit) {
			try {
			int kVal = scan.nextInt();
			scan.nextLine();
			kNearest(kVal);
			}catch(Exception e) {
				System.out.println("Invlaid Input, please enter an integer for the k_value");
			}
		}
		scan.close();
	}
	
	public static void loadTest(File tempFile) {
		try {
			Scanner scanner = new Scanner(tempFile);
			while(scanner.hasNext()) {
				ArrayList<Double> attributes = new ArrayList<Double>();
				double sepalLength = scanner.nextDouble();
				double sepalWidth = scanner.nextDouble();
				double petalLength = scanner.nextDouble();
				double petalWidth = scanner.nextDouble();
				String name = scanner.next();
				attributes.add(sepalLength);
				attributes.add(sepalWidth);
				attributes.add(petalLength);
				attributes.add(petalWidth);
				test.add(new IrisNode(attributes, name));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void loadTraining(File tempFile) {

		try {
			Scanner scanner = new Scanner(tempFile);
			while (scanner.hasNext()) {
				ArrayList<Double> attributes = new ArrayList<Double>();
				double sepalLength = scanner.nextDouble();
				double sepalWidth = scanner.nextDouble();
				double petalLength = scanner.nextDouble();
				double petalWidth = scanner.nextDouble();
				String name = scanner.next();
				attributes.add(sepalLength);
				attributes.add(sepalWidth);
				attributes.add(petalLength);
				attributes.add(petalWidth);

				training.add(new IrisNode(attributes, name));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static double calculateDistance(IrisNode test, IrisNode train) {
		double amount = 0;
		for (int i = 0; i < test.getAttributes().size(); i++) {
			double temp = test.getAttributes().get(i);
			double temp2 = train.getAttributes().get(i);
			amount = amount + Math.pow(temp - temp2, 2);
		}
		return Math.sqrt(amount);
	}

	public static void kNearest(int kNumber) {
		int matches = 0;
		int nonMatches = 0;
		for (IrisNode t : test) {
			ArrayList<IrisNode> closest = new ArrayList<IrisNode>();
			for (IrisNode train : training) {
				double distance = calculateDistance(t, train); //	calculate the distance away from the node
				train.setDistance(distance); //	set the distance to the new calculated distance
				closest.add(train); //	add the training data with the updated distance to the list before sorting
			}
			if(closest!=null) {
				Collections.sort(closest); // sort into ascending order	
			}
			for (int i = 0; i < kNumber; i++) {
				if (closest.get(i).getName().equals(t.getName())) {
					matches++;
					System.out.println("predicated class: " + t.getClass() + " is correct");
				}else {
					nonMatches++;
					System.out.println("predicted class: " + t.getClass() + " was incorrect");
				}
			}
		}
		
		System.out.println("\n");
		
		matches = matches / kNumber;
		nonMatches = nonMatches / kNumber;
		
		// Calculate the accuracy percentage
		
		double accuracy = ((double) (75 - nonMatches)) / 75;
		accuracy = accuracy*100;
		System.out.println("------------------------------");
		System.out.println(accuracy + "% is accurate");
		System.out.println("Number of matches is " + matches);
		System.out.println("Number of nonMatches is " + nonMatches);
		System.out.println("K was set at " + kNumber);
		System.out.println("------------------------------\n\n");
		
		chooseKValue();
	}
} 








