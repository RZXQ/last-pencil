package stage_4;

import java.util.List;

public class InputValidator {

	public static boolean validateInitialNumericPencils(String input) {
		return input.matches("[0-9]+");
	}

	public static boolean validateInitialPencilsQuantities(String str) {
		return Integer.parseInt(str) > 0;
	}

	public static boolean validatePlayersName(String str, List<String> playersName) {
		return playersName.contains(str);
	}

}
