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

	@Override
	protected boolean isBlocked(Cell destination, List<Cell> cells) 
	{
		// knight is never blocked
		return false;
	}
	

	Cell getDestination(int a, CellList cells, boolean rowPositive, boolean colPositive) 
	{
		int rowDist = a;
		int colDist = a==1 ? 2:1;
		return getRedirectedCell(rowDist, colDist, cells, rowPositive, colPositive);
	}
	
	boolean allMovesAdded(CellList validCells, int a, Cell destination)
	{
		if (destination == null || isSameOccupied(destination))	
			return false;
		validCells.add(destination);
		return false;
	}
}
