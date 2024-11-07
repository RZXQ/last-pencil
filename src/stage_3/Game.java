package stage_3;

import java.util.Scanner;

public class Game {

	private static final String INITIAL_PENCIL_PROMPT = "How many pencils would you like to use:";

	private static final String CHOOSE_FIRST_PLAYER_PROMPT = "Who will be the first (John, Jack):";

	private static final Scanner SCANNER = new Scanner(System.in);

	private static Player player1;

	private static Player player2;

	private static Player currentPlayer;

	private static int pencilTotal;

	private static int round;

	public static void startGame() {
		setupPencils();
		setupPlayers();
		setupFirstPlayer();
		displayPencils();
		executeGame();
	}

	private static void setupPencils() {
		System.out.println(INITIAL_PENCIL_PROMPT);
		pencilTotal = Integer.parseInt(SCANNER.nextLine());
	}

	private static void setupPlayers() {
		player1 = new Player("John");
		player2 = new Player("Jack");
	}

	private static void setupFirstPlayer() {
		System.out.println(CHOOSE_FIRST_PLAYER_PROMPT);
		String firstPlayer = SCANNER.nextLine();
		currentPlayer = firstPlayer.equals(player1.getName()) ? player1 : player2;
		round = 1;
	}

	private static void executeGame() {
		while (pencilTotal > 0) {
			if (round != 1) {
				flipPlayer();
			}
			round++;
			System.out.println(currentPlayer.getName() + " 's turn:");

			int pencilsTaken = Integer.parseInt(SCANNER.nextLine());
			takePencil(pencilsTaken);
			displayPencils();
		}
	}

	private static void takePencil(int pencilsTaken) {
		if (pencilsTaken <= pencilTotal) {
			pencilTotal -= pencilsTaken;
		}
	}

	private static void displayPencils() {
		System.out.println("|".repeat(pencilTotal));
	}

	private static void flipPlayer() {
		if (round != 1) {
			currentPlayer = currentPlayer == player1 ? player2 : player1;
		}
	}

}
