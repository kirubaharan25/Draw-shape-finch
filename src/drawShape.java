import java.io.IOException;
import java.util.Scanner; //Imports the Scanner class. This is used to obtain inputs.

public class drawShape {
	static Scanner input = new Scanner(System.in);

	public static void main(String args[]) throws IOException {
		draw draw = new draw();
		String userChoice = ""; // Creates a string called 'userChoice'

		//// FORMAT AND ANOTATE ////

		while (true) {

			while (true) { // This while loop will run until 'userChoice' is equal to S, T or Q.
				System.out.println(
						"Please enter one of the following:\nT (to draw a Triangle)\nS (to draw a Square or Rectangle)\nP (to draw a Pentagon)\nH1 (to draw a Hexagon)\nH2 (to draw a Heptagon)\nO (to draw a Octogan)\nN (to draw a Nonagon)\nD (to draw a Decagon)\nQ (to Quit and write a log file)");
				userChoice = input.next();
				userChoice = userChoice.toUpperCase();// Converts all the lower case into upper case
				if (userChoice.equals("T") || userChoice.equals("S") || userChoice.equals("P")
						|| userChoice.equals("H1") || userChoice.equals("H2") || userChoice.equals("O")
						|| userChoice.equals("D") || userChoice.equals("Q")) {
					break; // Used to break the loop
				}
				System.out.println("ERROR: Invalid Input");
			}

			if (userChoice.equals("T")) {
				draw.triangle();
			} else if (userChoice.equals("S")) {
				draw.square();
			} else if (userChoice.equals("P") || userChoice.equals("H1") || userChoice.equals("H2")
					|| userChoice.equals("O") || userChoice.equals("N") || userChoice.equals("D")) {
				draw.otherShapes(userChoice);
			} else if (userChoice.equals("Q")) {
				draw.writeToFile();
				System.out.println("ERROR: Program Terminated");
				break;
			}
		}
	}
}
