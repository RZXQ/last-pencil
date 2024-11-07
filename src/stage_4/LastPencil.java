package stage_4;

import java.util.Scanner;

public class LastPencil {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String PLAYER_ONE = "John";
    private static final String PLAYER_TWO = "Jack";
    private static int pencils;
    private static int round;
    private static String currentPlayer;

    public static void main(String[] args) {
        initializeGame();
        selectFirstPlayer();
        printPencils();

        while (true) {
            if (isGameFinished()) {
                getWinner();
                break;
            } else {
                if (round != 1) {
                    switchPlayer();
                }
                playRound(currentPlayer);
                round++;
            }
        }
    }

    private static int getPencils() {
        String str;
        int num;

        while (true) {
            try {
                str = SCANNER.nextLine();
                num = Integer.parseInt(str);
                if (num <= 0) {
                    throw new InitialValueException();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("The number of pencils should be numeric");
            } catch (InitialValueException e) {
                System.out.println("The number of pencils should be positive");
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
                System.out.println("Choose between " + PLAYER_ONE + " and " + PLAYER_TWO);
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
                    System.out.println("Possible values: '1', '2', '3'");
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
                        System.out.println("Too many pencils were taken");
                    }
                }
            } else {
                try {
                    throw new PossibleValuesException();
                } catch (PossibleValuesException e) {
                    System.out.println("Possible values: '1', '2', '3'");
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