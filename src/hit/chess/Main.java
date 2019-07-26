package hit.chess;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       run();
    }

    public static void run () {
        Scanner sc = new Scanner(System.in);
        String gameType = sc.next();
        String INVALID_INPUT = "Invalid input";

        switch (gameType){
            case "chess":
                break;
            case "go":
                break;
                default:
                    System.out.println(INVALID_INPUT);
        }
    }
}
