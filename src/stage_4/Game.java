package stage_4;

import java.util.Scanner;

public class Game {

	private static final String INITIAL_PENCIL_PROMPT = "How many pencils would you like to use:";

	private static final String CHOOSE_FIRST_PLAYER_PROMPT = "Who will be the first (John, Jack):";

	private static final Scanner SCANNER = new Scanner(System.in);

	private static final String NON_NUMERIC_PENCILS_ERROR = "The number of pencils should be numeric";

	private static final String NON_POSITIVE_PENCILS_ERROR = "The number of pencils should be positive";

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
		while (true) {
			String str = SCANNER.nextLine();
			if (isValidNumericPencils(str)) {
				if (isValidPositivePencils(str)) {
					pencilTotal = Integer.parseInt(str);
					break;
				}
				else {
					System.out.println(NON_POSITIVE_PENCILS_ERROR);
				}
			}
			else {
				System.out.println(NON_NUMERIC_PENCILS_ERROR);
			}
		}
	}

	private static boolean isValidPositivePencils(String str) {
		return InputValidator.validateInitialPencilsQuantities(str);
	}

	private static boolean isValidNumericPencils(String str) {
		return InputValidator.validateInitialNumericPencils(str);
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
