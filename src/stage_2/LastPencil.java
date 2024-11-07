package stage_2;

import java.util.Scanner;

public class LastPencil {

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("How many pencils would you like to use:");
		int numOfPencils = scanner.nextInt();

		System.out.println("Who will be the first (John, Jack):");
		String firstPlayer = scanner.next();

		for (int i = 0; i < numOfPencils; i++) {
			System.out.print("|");
		}
		System.out.println();

		System.out.println(firstPlayer + " is going first!");
	}

}
