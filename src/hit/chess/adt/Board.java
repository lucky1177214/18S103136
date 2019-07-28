package hit.chess.adt;

import javax.swing.*;

/*
     棋盘
 */

public class Board extends JPanel implements Cloneable {
    //棋盘大小
    private int boardSize;

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

}
