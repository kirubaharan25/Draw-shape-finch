
import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.lang.Math;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class draw {
	ArrayList<Integer> timeList = new ArrayList<Integer>();
	ArrayList<String> shapesDrawn = new ArrayList<String>();

	get get = new get();
	Scanner input = new Scanner(System.in);

	int index = 0;
	int squareCounter = 0;
	int triangleCounter = 0;
	int pentagonCounter = 0;
	int hexagonCounter = 0;
	int heptagonCounter = 0;
	int octagonCounter = 0;
	int nonagonCounter = 0;
	int decagonCounter = 0;
	int StringToInt = 0;

	boolean isInteger1;
	boolean isInteger2;
	boolean isInteger3;

	String input1;
	String input2;
	String input3;

	public void triangle() {
		Finch myFinch = new Finch();
		DecimalFormat format = new DecimalFormat("0.00");

		int triangleLength1 = 0;
		int triangleLength2 = 0;
		int triangleLength3 = 0;
		double angleA = 0;
		double angleB = 0;
		double angleC = 0;

		while (true) {

			while (true) {
				System.out.println("Input the length of side 1 (Between 15cm to 85cm)");
				input1 = input.next();
				System.out.println("Input the length of side 2 (Between 15cm to 85cm)");
				input2 = input.next();
				System.out.println("Input the length of side 3 (Between 15cm to 85cm)");
				input3 = input.next();
				isInteger1 = integerCheck(input1);
				isInteger2 = integerCheck(input2);
				isInteger3 = integerCheck(input3);
				if (isInteger1 == true && isInteger2 == true && isInteger3 == true) {
					triangleLength1 = Integer.parseInt(input1);
					triangleLength2 = Integer.parseInt(input2);
					triangleLength3 = Integer.parseInt(input3);
					if (triangleLength1 >= 15 && triangleLength2 >= 15 && triangleLength3 >= 15 && triangleLength1 <= 85
							&& triangleLength2 <= 85 && triangleLength3 <= 85) {
						break;
					} else {
						System.out.println("ERROR: Invalid Input");
					}
				} else {
					System.out.println("ERROR: Invalid Input. Enter an integer for the lengths.");
				}
			}

			if (triangleLength1 + triangleLength2 > triangleLength3
					&& triangleLength1 + triangleLength3 > triangleLength2
					&& triangleLength2 + triangleLength3 > triangleLength1) {
				break;
			}
			System.out.println("ERROR: Triangle cannot be drawn with the inputted measurements");
		}

		angleA = 180
				- Math.acos((Math.pow(triangleLength1, 2) + Math.pow(triangleLength2, 2) - Math.pow(triangleLength3, 2))
						/ (2 * triangleLength1 * triangleLength2)) * (180 / Math.PI);
		angleB = 180
				- Math.acos((Math.pow(triangleLength2, 2) + Math.pow(triangleLength3, 2) - Math.pow(triangleLength1, 2))
						/ (2 * triangleLength2 * triangleLength3)) * (180 / Math.PI);
		angleC = 180
				- Math.acos((Math.pow(triangleLength1, 2) + Math.pow(triangleLength3, 2) - Math.pow(triangleLength2, 2))
						/ (2 * triangleLength1 * triangleLength3)) * (180 / Math.PI);

		double movementTime1 = get.movementTime(triangleLength1);
		double movementTime2 = get.movementTime(triangleLength2);
		double movementTime3 = get.movementTime(triangleLength3);

		double turnTime1 = get.turnTime(angleA);
		double turnTime2 = get.turnTime(angleB);
		double turnTime3 = get.turnTime(angleC);

		int totalTime = (int) movementTime1 + (int) movementTime2 + (int) movementTime3 + (int) turnTime1
				+ (int) turnTime2 + (int) turnTime3;

		myFinch.setLED(19, 66, 237, totalTime);
		myFinch.setWheelVelocities(100, 100, (int) movementTime1);
		myFinch.setWheelVelocities(100, -100, (int) turnTime1);
		myFinch.setWheelVelocities(100, 100, (int) movementTime2);
		myFinch.setWheelVelocities(100, -100, (int) turnTime2);
		myFinch.setWheelVelocities(100, 100, (int) movementTime3);
		myFinch.setWheelVelocities(100, -100, (int) turnTime3);
		myFinch.buzz(440, 1000);
		myFinch.quit();

		timeList.add(totalTime);
		shapesDrawn.add("Triangle," + triangleLength1 + "," + triangleLength2 + "," + triangleLength3 + ",(angles,"
				+ format.format(180 - angleA) + "," + format.format(180 - angleB) + "," + format.format(180 - angleC)
				+ ")");

		triangleCounter = triangleCounter + 1;
		index = index + 1;
	}

	public void square() {
		Finch myFinch = new Finch();

		int squareLength1 = 0;
		int squareLength2 = 0;
		int angle = 90;
		int movementTime;
		int movementTime2;
		int turnTime;
		int totalTime;
		double time;
		String input1;
		String input2;

		while (true) {
			System.out.println("Input the length of side 1 (Between 15cm to 85cm)");
			input1 = input.next();
			System.out.println("Input the length of side 2 (Between 15cm to 85cm)");
			input2 = input.next();
			isInteger1 = integerCheck(input1);
			isInteger2 = integerCheck(input2);
			if (isInteger1 == true && isInteger2) {
				squareLength1 = Integer.parseInt(input1);
				squareLength2 = Integer.parseInt(input2);
				if (squareLength1 >= 15 && squareLength2 >= 15 && squareLength1 <= 85 && squareLength2 <= 85) {
					break;
				} else {
					System.out.println("ERROR: Invalid Input");
				}
			} else {
				System.out.println("ERROR: Invalid Input. Enter an integer for the lengths.");
			}
		}

		time = get.movementTime(squareLength1);// Passes on the variable to the finchMovementTime class.
		movementTime = (int) time;// Converts the double into an integer

		time = get.movementTime(squareLength2);
		movementTime2 = (int) time;

		time = get.turnTime(angle); // Passes the variable to the finchTurnTime class.
		turnTime = (int) time;// Converts double into an integer

		totalTime = (((movementTime + movementTime2) + (turnTime * 2)) * 2);

		myFinch.setLED(19, 66, 237, totalTime);

		for (int i = 0; i < 2; ++i) {
			 myFinch.setWheelVelocities(100, 100, movementTime);// Makes the Finch move forwards
			 myFinch.setWheelVelocities(100, -100, turnTime);// Makes the Finch rotate
			 myFinch.setWheelVelocities(100, 100, movementTime2);// Makes the Finch move forwards
			 myFinch.setWheelVelocities(100, -100, turnTime);// Makes the Finch rotate
		}
		myFinch.buzz(440, 1000);
		myFinch.quit();

		timeList.add(totalTime);
		if (squareLength1 == squareLength2) {
			shapesDrawn.add("Square," + squareLength1);
		} else {
			shapesDrawn.add("Rectangle," + squareLength1 + "," + squareLength2);
		}

		squareCounter = squareCounter + 1;
		index = index + 1;
	}

	public void otherShapes(String userChoice) {
		Finch myFinch = new Finch();

		String shape = "";
		int noOfSides = 0;
		int length = 0;
		double angle = 0;
		int movementTime = 0;
		int turnTime;
		int totalTime;
		double time;

		switch (userChoice) {
		case "P":
			shape = "Pentagon";
			noOfSides = 5;
			angle = 72;
			pentagonCounter = pentagonCounter + 1;
			break;
		case "H1":
			shape = "Hexagon";
			noOfSides = 6;
			angle = 60;
			hexagonCounter = hexagonCounter + 1;
			break;
		case "H2":
			shape = "Heptagon";
			noOfSides = 7;
			angle = 51.429;
			heptagonCounter = heptagonCounter + 1;
			break;
		case "O":
			shape = "Octagon";
			noOfSides = 8;
			angle = 45;
			octagonCounter = octagonCounter + 1;
			break;
		case "N":
			shape = "Nonagon";
			noOfSides = 9;
			angle = 40;
			nonagonCounter = nonagonCounter + 1;
			break;
		case "D":
			shape = "Decagon";
			noOfSides = 10;
			angle = 36;
			decagonCounter = decagonCounter + 1;
			break;
		}

		while (true) {
			System.out.println("Input the length of side 1 (Between 15cm to 85cm)");
			input1 = input.next();
			isInteger1 = integerCheck(input1);
			if (isInteger1 == true && isInteger2) {
				length = Integer.parseInt(input1);
				if (length >= 15 && length <= 85) {
					break;
				} else {
					System.out.println("ERROR: Invalid Input");
				}
			} else {
				System.out.println("ERROR: Invalid Input. Enter an integer for the lengths.");
			}
		}

		time = get.movementTime(length);// Passes on the variable to the finchMovementTime class.
		movementTime = (int) time;// Converts the double into an integer

		time = get.turnTime(angle); // Passes the variable to the finchTurnTime class.
		turnTime = (int) time;// Converts double into an integer

		totalTime = (movementTime + turnTime) * noOfSides;

		myFinch.setLED(19, 66, 237, totalTime);
		for (int i = 0; i < noOfSides; ++i) {
			myFinch.setWheelVelocities(100, 100, movementTime);// Makes the Finch move forwards
			myFinch.setWheelVelocities(100, -100, turnTime);// Makes the Finch rotate
		}
		myFinch.buzz(440, 1000);
		myFinch.quit();
		timeList.add(totalTime);
		shapesDrawn.add(shape + "," + length);

		index = index + 1;
	}

	public void writeToFile() throws IOException {
		float largestArea = 0;
		float area = 0;
		int averageTime;
		int totalTime = 0;
		int length = 0;
		int length2;
		double angle;
		String largestShape = "";
		String shapeName;

		BufferedWriter bufferedWriter = new BufferedWriter(
				new FileWriter("C:/Users/kirub/OneDrive/HP Documents/Computer Science/drawShape.txt", true));

		if (index == 0) {
			System.out.println("ERROR: Program Terminated");
			System.exit(0);
		}

		for (int i = 0; i < shapesDrawn.size(); ++i) {
			String shape = shapesDrawn.get(i);
			bufferedWriter.write(shape + ", ");
		}

		bufferedWriter.newLine();

		for (int i = 0; i < shapesDrawn.size(); ++i) {
			String[] shape = shapesDrawn.get(i).split(",");
			shapeName = shape[0].toString();
			length = Integer.parseInt(shape[1].toString());

			switch (shapeName) {
			case "Triangle":
				length2 = Integer.parseInt(shape[2].toString());
				angle = Double.parseDouble(shape[5].toString());
				area = (float) (0.5 * length * length2 * Math.sin(180 - angle));
				break;
			case "Square":
				area = (float) length * length;
				break;
			case "Pentagon":
				area = (float) (Math.sqrt(5 * (5 + 2 * (Math.sqrt(5)))) * length * length) / 4;
				break;
			case "Hexagon":
				area = (float) ((3 * Math.sqrt(3) * (length * length) / 2));
				break;
			case "Heptagon":
				angle = 128.571;
				area = (float) ((7 / 4) * (1.0 / Math.tan(180 / 7) * length * length));
			case "Octagon":
				area = (float) (2 * (1 + Math.sqrt(2)) * length * length);
				break;
			case "Nonagon":
				area = (float) ((6.1818 * (length * length)));
				break;
			case "Decagon":
				area = (float) (5 * length * length * (Math.sqrt(5 + (2 * Math.sqrt(5)))) / 2);
				break;
			}

			if (area > largestArea) {
				largestArea = area;
				largestShape = shapesDrawn.get(i);
			}

		}

		bufferedWriter.write("Largest Shape: " + largestShape);
		bufferedWriter.newLine();

		if (triangleCounter > squareCounter && triangleCounter > pentagonCounter && triangleCounter > hexagonCounter
				&& triangleCounter > heptagonCounter && triangleCounter > octagonCounter
				&& triangleCounter > nonagonCounter && triangleCounter > decagonCounter) {
			bufferedWriter.write("Triangle: " + triangleCounter);
		} else if (squareCounter > triangleCounter && squareCounter > pentagonCounter && squareCounter > hexagonCounter
				&& squareCounter > heptagonCounter && squareCounter > octagonCounter && squareCounter > nonagonCounter
				&& squareCounter > decagonCounter) {
			bufferedWriter.write("Square: " + squareCounter);
		} else if (pentagonCounter > triangleCounter && pentagonCounter > squareCounter
				&& pentagonCounter > hexagonCounter && pentagonCounter > heptagonCounter
				&& pentagonCounter > octagonCounter && pentagonCounter > nonagonCounter
				&& pentagonCounter > decagonCounter) {
			bufferedWriter.write("Pentagon: " + pentagonCounter);
		} else if (hexagonCounter > triangleCounter && hexagonCounter > squareCounter
				&& hexagonCounter > pentagonCounter && hexagonCounter > heptagonCounter
				&& hexagonCounter > octagonCounter && hexagonCounter > nonagonCounter
				&& hexagonCounter > decagonCounter) {
			bufferedWriter.write("Hexagon: " + hexagonCounter);
		} else if (heptagonCounter > triangleCounter && heptagonCounter > squareCounter
				&& heptagonCounter > pentagonCounter && heptagonCounter > hexagonCounter
				&& heptagonCounter > octagonCounter && heptagonCounter > nonagonCounter
				&& heptagonCounter > decagonCounter) {
			bufferedWriter.write("Heptagon: " + heptagonCounter);
		} else if (octagonCounter > triangleCounter && octagonCounter > squareCounter
				&& octagonCounter > pentagonCounter && octagonCounter > hexagonCounter
				&& octagonCounter > heptagonCounter && octagonCounter > nonagonCounter
				&& octagonCounter > decagonCounter) {
			bufferedWriter.write("Octagon: " + octagonCounter);
		} else if (nonagonCounter > triangleCounter && nonagonCounter > squareCounter
				&& nonagonCounter > pentagonCounter && nonagonCounter > hexagonCounter
				&& nonagonCounter > heptagonCounter && nonagonCounter > octagonCounter
				&& nonagonCounter > decagonCounter) {
			bufferedWriter.write("Nonagon: " + nonagonCounter);
		} else if (decagonCounter > triangleCounter && decagonCounter > squareCounter
				&& decagonCounter > pentagonCounter && decagonCounter > hexagonCounter
				&& decagonCounter > heptagonCounter && decagonCounter > nonagonCounter
				&& decagonCounter > octagonCounter) {
			bufferedWriter.write("Decagon: " + decagonCounter);
		}
		bufferedWriter.newLine();

		for (int i = 0; i < index; ++i) {
			int time = timeList.get(i);
			totalTime = totalTime + time;
		}
		averageTime = totalTime / index;
		bufferedWriter.write("Average Time: " + averageTime);
		bufferedWriter.newLine();
		bufferedWriter.close();
	}

	public boolean integerCheck(String input) {
		try {
			StringToInt = Integer.parseInt(input);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
