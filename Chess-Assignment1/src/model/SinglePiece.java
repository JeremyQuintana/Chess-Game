package model;

import java.util.Collections;
import java.util.List;
public abstract class SinglePiece extends Piece {
	
	public SinglePiece(PieceType piece, Player player, String key) 
	{
		super(player);
		pieceType = piece;
		this.key = key;
	}

	
	private PieceType pieceType;
	private String key;
	// all pieces have a maximum of 2 moves they can make
	private static final int MOVE_LIMIT = 3;
	
	
	
	PieceType getType()
	{
		return pieceType;
	}
	
	// if white rook returns "R", black knight returns "k"
	public String toString()
	{
		return pieceType.name();
	}
	
	String getKey()
	{
		return key;
	}
	
	List<SinglePiece> getPieces()
	{
		return Collections.singletonList(this);
	}
	
	// get row/column depending on its move pattern and current iteration a
	abstract int getDestinationRow(int a, boolean rowPositive, boolean colPositive);
	abstract int getDestinationCol(int a, boolean rowPositive, boolean colPositive);
	
	int getRedirectedRow(int rowDist, boolean rowPositive)
	{
		int oldRow = getLocation().getRow();
		int newRow = oldRow + (rowPositive ? rowDist : -rowDist);
		return newRow;
	}
	
	int getRedirectedCol(int colDist, boolean colPositive)
	{
		int oldCol = getLocation().getCol();
		int newCol = oldCol + (colPositive ? colDist : -colDist);
		return newCol;
	}
	
	boolean isValidMove(Cell destination)
	{
		if (destination == null)
			return false;
		if (destination.getIsOccupied())
			return getPlayer().getType() != destination.getOccupiedType();
		return true;
	}
	
	// adds destination if it fits rules, and breaks out of loop if no more to be added
	boolean movesLeftToAdd(int a, Cell destination)
	{
		if (a > SinglePiece.MOVE_LIMIT)
			return false;
		return movesLeftToAdd2(a, destination);
	}
	abstract boolean movesLeftToAdd2(int a, Cell destination);
}

