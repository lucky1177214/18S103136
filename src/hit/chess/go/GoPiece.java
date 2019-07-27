package hit.chess.go;

import hit.chess.go.GoBoard.State;
public class GoPiece {
    public GoAction action;
    public State state;
    public int Qi;
    // Row and col are need to remove (set to null) this Stone from Grid
    public int row;
    public int col;
    public int step_count;

    public GoPiece(int row, int col, State state, int step_count) {
        action = null;
        this.step_count = step_count;
        this.state = state;
        Qi = 4;
        this.row = row;
        this.col = col;
    }
}
