package hit.chess;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       run();
    }

    public static void run () {
        System.out.println("Please Input GameType:(chess or go)");
        Scanner sc = new Scanner(System.in);
        String gameType = sc.next();
        String INVALID_INPUT = "Invalid input";

        switch (gameType){
            case "chess":
                break;
            case "go":
                GoGame.playChess();
                break;
                default:
                    System.out.println(INVALID_INPUT);
        }
    }
}
