package stage_3;

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

        while (!isGameFinished()) {
            if (round != 1) {
                switchPlayer();
            }
            executeRound(currentPlayer);
            round++;
        }
    }

    private static void initializeGame() {
        System.out.println("How many pencils would you like to use:");
        pencils = SCANNER.nextInt();
    }

    private static void selectFirstPlayer() {
        System.out.println("Who will be the first (" + PLAYER_ONE + ", " + PLAYER_TWO + "):");
        currentPlayer = SCANNER.next();
        round = 1;
    }

    private static void switchPlayer() {
        currentPlayer = PLAYER_TWO.equals(currentPlayer) ? PLAYER_ONE : PLAYER_TWO;
    }

    private static boolean isGameFinished() {
        return pencils == 0;
    }

    public static void executeRound(String player) {
        System.out.println(player + "'s turn:");
        if (!removePencils(SCANNER.nextInt())) {
            System.out.println("wrong");
        }
        printPencils();
    }

    public static boolean removePencils(int num) {
        if (pencils - num >= 0) {
            pencils = pencils - num;
            return true;
        } else {
            return false;
        }
    }

    public static void printPencils() {
        for (int i = 0; i < pencils; i++) {
            System.out.print("|");
        }
        System.out.println();
    }
}