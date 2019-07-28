package hit.chess;

import hit.chess.chess.ChessGame;
import hit.chess.go.GoGame;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       run();
    }

    public static void run () {
        System.out.println("Please Input GameType:(Chess or Go)");
        Scanner sc = new Scanner(System.in);
        String gameType = sc.next().toLowerCase();
        String INVALID_INPUT = "Invalid input";
        switch (gameType){
            case "chess":
                ChessGame.playChess();
                break;
            case "go":
                GoGame.playChess();
                break;
                default:
                    System.out.println(INVALID_INPUT);
        }
    }
}
