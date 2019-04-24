package model;

import java.util.List;

public class Knight extends Piece {

	public Knight(Player player, String key) 
	{
		super(PieceType.KNIGHT, player, key);
	}
	
	// move limit for knight for each direction
	// ie. distance of (1,2) or (2,1)
	static final int MOVE_LIMIT = 2;
	
	// Knights can't get intercepted
	void moveUpdate(Cell destination)
	{
	}
	
	// Knights only have a count of moves
	boolean movesLeftToAdd2(int a)			 
	{
		return a <= MOVE_LIMIT;
	}
	

//	Cell getDestination(int a, CellList cells, boolean rowPositive, boolean colPositive) 
//	{
//		int rowDist = a;
//		int colDist = a==1 ? 2:1;
//		return getRedirectedCell(rowDist, colDist, cells, rowPositive, colPositive);
//	}
//	
//	boolean allMovesAdded(CellList validCells, int a, Cell destination)
//	{
//		if (destination == null || isSameOccupied(destination))	
//			return false;
//		validCells.add(destination);
//		return false;
//	}
	
	// choose either (1,2) or (2,1)
	int getDestinationRow(int a, boolean rowPositive, boolean colPositive)
	{
		return getRedirectedRow(a, rowPositive);
	}
	
	int getDestinationCol(int a, boolean rowPositive, boolean colPositive)
	{
		return getRedirectedCol(a==1 ? 2:1, colPositive);
	}

}
