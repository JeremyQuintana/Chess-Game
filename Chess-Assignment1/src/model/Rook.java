package model;

import java.util.LinkedList;
import java.util.List;

public class Rook extends InterceptablePiece {

	public Rook(String key, Player player) 
	{
		super(PieceType.ROOK, key, player);
	}
	
//	boolean allMovesAdded(CellList validCells, int a, Cell destination)			 
//	{
//		if (destination == null || isSameOccupied(destination))	
//			return true;
//		validCells.add(destination);
//		if (isOpposerOccupied(destination))						
//			return true;
//		return false;
//	}
	
	// Rook moves in a straight pattern (a,0) or (0,a)
	//	row col		row col 	
	//	(a,0)		+ 	+		
	//	(0,a)		+ 	+		messes with
	// 	(-a,0)		- 	-		original getDestination
	//	(a,0)		- 	-		
	int getDestinationRow(int a, boolean rowPositive, boolean colPositive)
	{
		int rowDist = colPositive ? a : 0;
		return getRedirectedRow(rowDist, rowPositive);
	}
	
	int getDestinationCol(int a, boolean rowPositive, boolean colPositive)
	{
		int colDist = colPositive ? 0 : a;// vvv notice
		return getRedirectedCol(colDist, rowPositive);
	}



}
