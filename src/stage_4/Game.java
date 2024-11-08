package stage_4;

import java.util.Arrays;
import java.util.Scanner;

public class Game {

	private static final String INITIAL_PENCIL_PROMPT = "How many pencils would you like to use:";

	private static final String CHOOSE_FIRST_PLAYER_PROMPT = "Who will be the first (John, Jack):";

	private static final String NON_NUMERIC_PENCILS_ERROR = "The number of pencils should be numeric";

	private static final String NON_POSITIVE_PENCILS_ERROR = "The number of pencils should be positive";

	private static final Scanner SCANNER = new Scanner(System.in);

	public static Player player1;

	public static Player player2;

	private static Player currentPlayer;

	private static int pencilTotal;

	private static int round;

	public static void startGame() {
		setupPencils();
		setupPlayers();
		setupFirstPlayer();
		displayPencils();
		executeGame();
		checkWinner();
	}

	private static void checkWinner() {
		if (pencilTotal == 0) {
			System.out.println((currentPlayer == player1 ? player2.getName() : player1.getName()) + " won!");
		}
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
		return InputValidator.validateNumericPencils(str);
	}

	private static void setupPlayers() {
		player1 = new Player("John");
		player2 = new Player("Jack");
	}

	private static void setupFirstPlayer() {
		System.out.println(CHOOSE_FIRST_PLAYER_PROMPT);
		while (true) {
			String firstPlayer = SCANNER.nextLine();
			if (InputValidator.validatePlayersName(firstPlayer, Arrays.asList(player1.getName(), player2.getName()))) {
				currentPlayer = firstPlayer.equals(player1.getName()) ? player1 : player2;
				round = 1;
				break;
			}
			// System.out.println("The name of the player should be one of the following:
			// " + player1.getName() + ", "
			//
			// + player2.getName());
			System.out.println("Choose between '" + player1.getName() + "' and '" + player2.getName() + "'");
		}
	}

	private static void executeGame() {
		while (pencilTotal > 0) {
			if (round != 1) {
				flipPlayer();
			}
			round++;
			System.out.println(currentPlayer.getName() + " 's turn!");

			takePencil();
			displayPencils();
		}
	}

	private static void takePencil() {
		while (true) {
			String str = SCANNER.nextLine();
			if (InputValidator.validateNumericPencils(str)) {
				if (InputValidator.validateTakenRange(str)) {
					if (InputValidator.validatePencilsTaken(str, pencilTotal)) {
						pencilTotal -= Integer.parseInt(str);
						break;
					}
					else {
						System.out.println("Too many pencils were taken");
					}
				}
				else {
					System.out.println("Possible values: '1', '2' or '3'");
				}
			}
			else {
				System.out.println("Possible values: '1', '2' or '3'");
			}
		}
	}

	private static void displayPencils() {
		if (pencilTotal != 0) {
			System.out.println("|".repeat(pencilTotal));
		}
	}

	private static void flipPlayer() {
		if (round != 1) {
			currentPlayer = currentPlayer == player1 ? player2 : player1;
		}
	}

}
