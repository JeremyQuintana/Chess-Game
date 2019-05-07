package model;

import java.util.LinkedList;
import java.util.List;


// implementation of pieces with combined pieceType properties
// eg: RookKnight can move both as a rook or a knight
public class MultiPiece extends Piece {
	
	public MultiPiece(SinglePiece piece) 
	{
		super(piece.getPlayer());
		setCell(piece.getLocation());
		pieces = new LinkedList<SinglePiece>();
		pieces.add(piece);
	}
	private List<SinglePiece> pieces;
	
	public String toString()
	{
		String name = "";
		for (Piece p : pieces)
			name += p.toString();
		return name;
	}
	
	String getKey()
	{
		String key = "";
		for (Piece p : pieces)
			key += p.getKey();
		return key;
	}
	
	List<SinglePiece> getPieces() 
	{
		return pieces;
	}
	
	/*these methods need to add/remove pieces from player too*/
	boolean merge(SinglePiece piece) 
	{
		for (SinglePiece p : pieces)
			if(piece.getType() == p.getType())
				return false;
		return pieces.add(piece);
	}
	
	boolean split(SinglePiece piece)
	{
		return pieces.size()>1 ? pieces.remove(piece) : false;
	}
	
	
	
}

