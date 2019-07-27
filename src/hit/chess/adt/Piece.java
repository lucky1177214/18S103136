package hit.chess.adt;

/*
    棋子
 */
public class Piece {
    //棋子种类
    private String pieceType;

    //棋子数量
    private int pieceNumber;

    public String getPieceType() {
        return pieceType;
    }

    public void setPieceType(String pieceType) {
        this.pieceType = pieceType;
    }

    public int getPieceNumber() {
        return pieceNumber;
    }

    public void setPieceNumber(int pieceNumber) {
        this.pieceNumber = pieceNumber;
    }
}
