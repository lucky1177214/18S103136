package hit.chess.chess;

import hit.chess.adt.Piece;

import java.util.ArrayList;

public abstract  class ChessPiece extends  Piece{
    private int color;
    private String id=null;
    private String path;
    protected ArrayList<ChessBoard> possiblemoves = new ArrayList<ChessBoard>();  //Protected (access from child classes)
    public abstract ArrayList<ChessBoard> move(ChessBoard pos[][],int x,int y);  //Abstract Function. Must be overridden

    //Id Setter
    public void setId(String id)
    {
        this.id=id;
    }

    //Path Setter
    public void setPath(String path)
    {
        this.path=path;
    }

    //Color Setter
    public void setColor(int c)
    {
        this.color=c;
    }

    //Path getter
    public String getPath()
    {
        return path;
    }

    //Id getter
    public String getId()
    {
        return id;
    }

    //Color Getter
    public int getcolor()
    {
        return this.color;
    }

    //Function to return the a "shallow" copy of the object. The copy has exact same variable value but different reference
    public Piece getcopy() throws CloneNotSupportedException
    {
        return (Piece) this.clone();
    }
}
