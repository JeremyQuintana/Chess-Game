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
	

	// Rook moves in a straight pattern (a,0) or (0,a)
	//	row col		row col 	
	//	(a,0)		+ 	+		
	//	(0,a)		+ 	+		messes with
	// 	(-a,0)		- 	-		original getDestination
	//	(a,0)		- 	-		
	Cell getDestination(int a, CellList cells, boolean rowPositive, boolean colPositive) 
	{
		
		int rowDist = colPositive ? a : 0;
		int colDist = colPositive ? 0 : a; 
		//													fix				vvv
		return getRedirectedCell(rowDist, colDist, cells, rowPositive, rowPositive);
	}
	
	boolean allMovesAdded(CellList validCells, int a, Cell destination)			 
	{
		if (destination == null || isSameOccupied(destination))	
			return true;
		validCells.add(destination);
		if (isOpposerOccupied(destination))						
			return true;
		return false;
	}

}
