package hit.chess.go;

import java.util.ArrayList;
import hit.chess.go.GoBoard.State;

public class GoAction {
    public ArrayList<GoPiece> pieces;   // ArrayList to store multiple stones
    public State state;

    public GoAction(State state) {
        pieces = new ArrayList<>();
    }

    public int getQi() {
        int total = 0;
        for (GoPiece piece : pieces) {
            total += piece.Qi;
        }
        return total;
    }

    public void addStone(GoPiece piece) {
        piece.action = this;
        pieces.add(piece);
    }

    public void join(GoAction action) {
        for (GoPiece stone : action.pieces) {
            addStone(stone);
        }
    }
}
