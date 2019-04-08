package model;

import java.util.LinkedList;
import java.util.List;

public class Rook extends Piece {

	public Rook(Player player, String key) 
	{
		super(PieceType.ROOK, player, key);
	}
	
//	/*TO IMPLEMENT*/
//	public boolean isValidMove(Cell destination, List<Cell> cells)
//	{	
//		// PieceType handles this
//		Move move = new Move(super.getCell(), destination);
//		
//		if (getPieceType().isValidMove(move) && !isBlocked()) 
//			return super.isValidMove(destination, cells);
//		return false;
//	}

	@Override
	protected boolean isBlocked(Cell destination, List<Cell> cells) 
	{
		return false;
	}

}
