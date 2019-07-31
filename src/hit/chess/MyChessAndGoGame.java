package hit.chess;

import java.util.Scanner;
import hit.chess.go.*;
import hit.chess.chess.*;
public class MyChessAndGoGame {
    public static void main(String[] args) {
        String INVALID_INPUT = "Invalid input";
        boolean inputIsValid = true;
        while(inputIsValid) {
            System.out.println("Please Input GameType:(chess or go)");
            Scanner sc = new Scanner(System.in);
            String gameType = sc.next().toLowerCase();
            switch (gameType){
                case "chess":
                    inputIsValid = false;
                    ChessGame chessGame1 = new ChessGame();
                    chessGame1.initGame();
                    chessGame1.playGame();
                    chessGame1.endGame();
                    System.out.println("Are you playing Go?");
                    if(sc.next().equalsIgnoreCase("yes")){
                        GoGame goGame1 = new GoGame();
                        goGame1.initGame();
                        goGame1.playGame();
                        goGame1.endGame();
                    }
                    break;
                case "go":
                    inputIsValid = false;
                    GoGame goGame2 = new GoGame();
                    goGame2.initGame();
                    goGame2.playGame();
                    goGame2.endGame();
                    System.out.println("Are you playing Chess?");
                    if(sc.next().equalsIgnoreCase("yes")){
                        ChessGame chessGame2 = new ChessGame();
                        chessGame2.initGame();
                        chessGame2.playGame();
                        chessGame2.endGame();
                    }
                    break;
                default:
                    inputIsValid = true;
                    System.out.println(INVALID_INPUT);
            }
        }



    }


}
