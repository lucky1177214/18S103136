package hit.chess.go;

import hit.chess.go.GoBoard.State;
public class GoPosition {

    public int current_step = 1;
    private final int SIZE;
    private GoPiece[][] pieces;

    public GoPosition(int size) {
        SIZE = size;
        pieces = new GoPiece[SIZE][SIZE];
    }

    // 下子
    public void addStone(int row, int col, State state) {
        GoPiece newPiece = new GoPiece(row, col, state, current_step);
        pieces[row][col] = newPiece;
        // Check near_stone_set
        GoPiece[] near_piece_set = new GoPiece[4];
        // Don't check outside the board
        if (row > 0) {
            near_piece_set[0] = pieces[row - 1][col];
        }
        if (row < SIZE - 1) {
            near_piece_set[1] = pieces[row + 1][col];
        }
        if (col > 1) {
            near_piece_set[2] = pieces[row][col - 1];
        }
        if (col < SIZE - 1) {
            near_piece_set[3] = pieces[row][col + 1];
        }
        // Prepare Chain for this new Stone
        GoAction finalAction = new GoAction(newPiece.state);
        for (GoPiece near_piece : near_piece_set) {
            if (row == 0 || row == SIZE-1)
            {
                newPiece.Qi--; //
            }
            if (col == 0 || col == SIZE-1)
            {
                newPiece.Qi--;
            }
            if (near_piece == null) {
                continue;//null near_stone skip 邻近为空则跳过
            }
            newPiece.Qi--;
            near_piece.Qi--;
            // If it's different color than newStone check him
            if (near_piece.state != newPiece.state) {
                checkStone(near_piece);
                continue;
            }

            if (near_piece.action != null) {
                finalAction.join(near_piece.action);
            }
        }
        finalAction.addStone(newPiece);
        current_step++;
    }

    public void checkStone(GoPiece piece) {
        // Every Stone is part of a Chain so we check total Qi
        if (piece.action.getQi() == 0) {
            for (GoPiece p : piece.action.pieces) {
                p.action = null;
                pieces[p.row][p.col] = null;  // empty the link of stone makes it dead
            }
        }
    }

    public boolean isOccupied(int row, int col)
    {
        return pieces[row][col] != null;
    }
    // check empty place is not occupied
    public State getState(int row, int col) {
        GoPiece piece = pieces[row][col];
        if (piece == null) {
            return null;
        } else {
            return piece.state;
        }
    }

    public int getsteps(int row, int col){
        GoPiece piece = pieces[row][col];
        if (piece == null){
            return 0;
        }
        else {
            return piece.step_count;
        }
    }

    public boolean issuicide(int row, int col, State state)
    {
        byte emptyflag = 0;
        byte surrounding_flag = 0;
        GoPiece[] near_piece_set = new GoPiece[4];
        if (row > 0) {
            near_piece_set[0] = pieces[row - 1][col];
        }
        if (row < SIZE - 1) {
            near_piece_set[1] = pieces[row + 1][col];
        }
        if (col > 1) {
            near_piece_set[2] = pieces[row][col - 1];
        }
        if (col < SIZE - 1) {
            near_piece_set[3] = pieces[row][col + 1];
        }
        for (GoPiece near_piece : near_piece_set) {
            if (near_piece == null) {
                emptyflag++;
                continue;
            }
            State opp_state = (state == State.BLACK)? State.WHITE : State.BLACK;
            if (near_piece.state == opp_state){
                surrounding_flag++;
            }
        }
        return (surrounding_flag + emptyflag == 4 && surrounding_flag >= 1 && emptyflag <= 1)? true:false;
    }

    // check place already been put but eaten later state:empty or occupied
}
