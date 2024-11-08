package stage_5;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game {

	private static final String INITIAL_PENCIL_PROMPT = "How many pencils would you like to use:";

	private static final String CHOOSE_FIRST_PLAYER_PROMPT = "Who will be the first (John, Jack):";

	private static final String NON_NUMERIC_PENCILS_ERROR = "The number of pencils should be numeric";

	private static final String NON_POSITIVE_PENCILS_ERROR = "The number of pencils should be positive";

	private static final String INVALID_PLAYER_NAME_ERROR = "Choose between 'John' and 'Jack'";

	private static final String INVALID_TAKE_RANGE_ERROR = "Possible values: '1', '2' or '3'";

	private static final String TOO_MANY_PENCILS_TAKEN_ERROR = "Too many pencils were taken";

	private static final Scanner SCANNER = new Scanner(System.in);

	private Player player1;

	private Player player2;

	private Player bot;

	private Player currentPlayer;

	private int pencilTotal;

	private int round;

	public void startGame() {
		setupInitialPencils();
		setupPlayers();
		setUpBot();
		setupFirstPlayer();
		displayPencils();
		executeGame();
		announceWinner();
	}

	private void setupInitialPencils() {
		System.out.println(INITIAL_PENCIL_PROMPT);
		while (true) {
			String str = SCANNER.nextLine();
			if (!InputValidator.validateNumericPencils(str)) {
				System.out.println(NON_NUMERIC_PENCILS_ERROR);
				continue;
			}
			if (!InputValidator.validateInitialPencilsQuantities(str)) {
				System.out.println(NON_POSITIVE_PENCILS_ERROR);
				continue;
			}
			pencilTotal = Integer.parseInt(str);
			break;
		}
	}

	private void setupPlayers() {
		player1 = new Player("John");
		player2 = new Player("Jack");
	}

	private void setUpBot() {
		bot = player2;
	}

	private void setupFirstPlayer() {
		System.out.println(CHOOSE_FIRST_PLAYER_PROMPT);
		while (true) {
			String firstPlayer = SCANNER.nextLine();
			if (InputValidator.validatePlayersName(firstPlayer, Arrays.asList(player1.getName(), player2.getName()))) {
				currentPlayer = firstPlayer.equals(player1.getName()) ? player1 : player2;
				round = 1;
				break;
			}
			System.out.println(INVALID_PLAYER_NAME_ERROR);
		}
	}

	private void executeGame() {
		while (pencilTotal > 0) {
			if (round != 1) {
				switchPlayer();
			}

			round++;

			System.out.printf("%s's turn!%n", currentPlayer.getName());
			if (currentPlayer == bot) {
				botTakePencils();
			}
			else {
				humanTakePencils();
			}

			displayPencils();
		}
	}

	private void humanTakePencils() {
		while (true) {
			String str = SCANNER.nextLine();
			if (!InputValidator.validateNumericPencils(str)) {
				System.out.println(INVALID_TAKE_RANGE_ERROR);
				continue;
			}
			if (!InputValidator.validateTakenRange(str)) {
				System.out.println(INVALID_TAKE_RANGE_ERROR);
				continue;
			}
			if (!InputValidator.validatePencilsTaken(str, pencilTotal)) {
				System.out.println(TOO_MANY_PENCILS_TAKEN_ERROR);
			}

			takePencil(str);
			break;
		}
	}

	private void botTakePencils() {
		int pencilsTaken;
		if (pencilTotal == 1) {
			pencilsTaken = 1;
		}
		else if (pencilTotal % 4 == 0) {
			pencilsTaken = 3;
		}
		else if ((pencilTotal + 1) % 4 == 0) {
			pencilsTaken = 2;
		}
		else if ((pencilTotal + 2) % 4 == 0) {
			pencilsTaken = 1;
		}
		else {
			pencilsTaken = new Random().nextInt(3) + 1;
		}
		pencilTotal -= pencilsTaken;
		System.out.println(pencilsTaken);
	}

	private void displayPencils() {
		if (pencilTotal > 0) {
			System.out.println("|".repeat(pencilTotal));
		}
	}

	private void switchPlayer() {
		currentPlayer = currentPlayer == player1 ? player2 : player1;
	}

	private void takePencil(String str) {
		pencilTotal -= Integer.parseInt(str);
	}

	private void announceWinner() {
		if (pencilTotal == 0) {
			System.out.println((currentPlayer == player1 ? player2.getName() : player1.getName()) + " won!");
		}
	}

}
