package stage_4;

public class InputValidator {

	public static boolean validateInitialNumericPencils(String input) {
		return input.matches("[0-9]+");
	}

	public static boolean validateInitialPencilsQuantities(String str) {
		return Integer.parseInt(str) > 0;
	}

}
