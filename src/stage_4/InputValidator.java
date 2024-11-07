package stage_4;

import java.util.List;

public class InputValidator {

	public static boolean validateNumericPencils(String input) {
		return input.matches("[0-9]+");
	}

	public static boolean validateInitialPencilsQuantities(String input) {
		return Integer.parseInt(input) > 0;
	}

	public static boolean validatePlayersName(String input, List<String> playersName) {
		return playersName.contains(input);
	}

	public static boolean validatePencilsTaken(String input, int pencilsTotal) {
		return Integer.parseInt(input) <= pencilsTotal;
	}

	public static boolean validateTakenRange(String input) {
		int num = Integer.parseInt(input);
		return num == 1 || num == 2 || num == 3;
	}

}
