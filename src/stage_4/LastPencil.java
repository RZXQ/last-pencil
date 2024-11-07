package stage_4;

import java.util.Scanner;

public class LastPencil {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String PLAYER_ONE = "John";
    private static final String PLAYER_TWO = "Jack";
    private static final String INVALID_PLAYER_MESSAGE = "Choose between " + PLAYER_ONE + " and " + PLAYER_TWO;
    private static final String NON_NUMERIC_PENCILS_MESSAGE = "The number of pencils should be numeric";
    private static final String NON_POSITIVE_PENCILS_MESSAGE = "The number of pencils should be positive";
    private static final String TOO_MANY_PENCILS_TAKEN_MESSAGE = "Too many pencils were taken";
    private static final String POSSIBLE_VALUES_MESSAGE = "Possible values: '1', '2', '3'";
    private static int pencils;
    private static int round;
    private static String currentPlayer;

    public static void main(String[] args) {
        initializeGame();
        selectFirstPlayer();
        printPencils();

        while (!isGameFinished()) {
            if (round != 1) {
                switchPlayer();
            }
            playRound(currentPlayer);
            round++;
        }

        getWinner();
    }

    private static int getPencils() {
        String str;
        int num;

        while (true) {
            try {
                str = SCANNER.nextLine();
                num = Integer.parseInt(str);
                if (num <= 0) {
                    throw new NonPositivePencilCountExcpetion(NON_NUMERIC_PENCILS_MESSAGE);
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println(NON_NUMERIC_PENCILS_MESSAGE);
            } catch (NonPositivePencilCountExcpetion e) {
                System.out.println(NON_POSITIVE_PENCILS_MESSAGE);
            }
        }
        return num;
    }

    private static void initializeGame() {
        System.out.println("How many pencils would you like to use:");
        pencils = getPencils();
    }

    private static void selectFirstPlayer() {
        System.out.println("Who will be the first (" + PLAYER_ONE + ", " + PLAYER_TWO + "):");

        while (true) {
            currentPlayer = SCANNER.nextLine();
            try {
                if (!PLAYER_ONE.equals(currentPlayer) && !PLAYER_TWO.equals(currentPlayer)) {
                    throw new InvalidPlayerException();
                }
                break;
            } catch (InvalidPlayerException e) {
                System.out.println(INVALID_PLAYER_MESSAGE);
            }
        }
        round = 1;
    }

    private static void switchPlayer() {
        currentPlayer = PLAYER_TWO.equals(currentPlayer) ? PLAYER_ONE : PLAYER_TWO;
    }

    private static boolean isGameFinished() {
        return pencils == 0;
    }

    public static void playRound(String player) {
        System.out.println(player + "'s turn!");

        removePencils();

        printPencils();
    }

    public static void removePencils() {
        int num;

        while (true) {
            while (true) {
                try {
                    num = Integer.parseInt(SCANNER.next());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println(POSSIBLE_VALUES_MESSAGE);
                }
            }

            if (num == 1 || num == 2 || num == 3) {
                if (pencils - num >= 0) {
                    pencils = pencils - num;
                    break;
                } else {
                    try {
                        throw new TooManyPencilsTakenException();
                    } catch (TooManyPencilsTakenException e) {
                        System.out.println(TOO_MANY_PENCILS_TAKEN_MESSAGE);
                    }
                }
            } else {
                try {
                    throw new PossibleValuesException();
                } catch (PossibleValuesException e) {
                    System.out.println(POSSIBLE_VALUES_MESSAGE);
                }
            }

        }
    }

    public static void printPencils() {
        if (pencils != 0) {
            for (int i = 0; i < pencils; i++) {
                System.out.print("|");
            }
            System.out.println();

        }
    }

    private static void getWinner() {
        System.out.println((PLAYER_ONE.equals(currentPlayer) ? PLAYER_TWO : PLAYER_ONE) + " won!");
    }
}