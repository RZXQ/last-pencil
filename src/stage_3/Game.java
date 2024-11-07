package stage_3;

import java.util.Scanner;

public class Game {

	private static final String INITIAL_PENCIL_PROMPT = "How many pencils would you like to use:";

	private static final Scanner SCANNER = new Scanner(System.in);

	private static final String CHOOSE_FIRST_PLAYER = "Who will be the first (John, Jack):";

	private static Player player1;

	private static Player player2;

	private static Player currentPlayer;

	private static int pencilTotal;

	private static int round;

	public static void main(String[] args) {
		initPencil();

		inGame();
	}

	private static void inGame() {
		while (pencilTotal > 0) {
			if (round == 0) {
				setFirstPlayer();
			}
			else {
				if (round != 1) {
					flipPlayer();
				}
				round++;
				System.out.println(currentPlayer.getName() + " 's turn:");
				int pencilsTaken = Integer.parseInt(SCANNER.nextLine());
				if (pencilsTaken <= pencilTotal) {
					pencilTotal -= pencilsTaken;
				}
				printPencil(pencilTotal);
			}
		}
	}

	private static void initPencil() {
		System.out.println(INITIAL_PENCIL_PROMPT);
		pencilTotal = Integer.parseInt(SCANNER.nextLine());
	}

	private static void setFirstPlayer() {
		round = 1;
		player1 = new Player("John");
		player2 = new Player("Jack");
		System.out.println(CHOOSE_FIRST_PLAYER);
		String firstPlayer = SCANNER.nextLine();
		currentPlayer = firstPlayer.equals(player1.getName()) ? player1 : player2;

		printPencil(pencilTotal);
		// System.out.println(currentPlayer.getName() + " is going first!");
	}

	private static void printPencil(int numOfPencils) {
		System.out.println("|".repeat(numOfPencils));
	}

	private static void flipPlayer() {
		if (round != 1) {
			currentPlayer = currentPlayer == player1 ? player2 : player1;
		}
	}

}
