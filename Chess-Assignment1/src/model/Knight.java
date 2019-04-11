package model;

import java.util.List;

import model.Piece.Statement;

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
	
	Statement breakCondition(int a, Cell destination)
	{
		if (a > MOVE_LIMIT)											return Statement.BREAK;
		else if (destination == null || isOccupied(destination))	return Statement.CONTINUE;
		else														return Statement.NOTHING;
				
	}
	
	
	//not working
//	public CellList getMoves(CellList cells, boolean rowPositive, boolean colPositive) 
//	{
//		CellList validCells = new CellList();
//		Cell destination = getLocation();
//		
//		for (int a=1; destination != null && !isOccupied(destination); a++)
//		{
//			if (a>1)
//				validCells.add(destination);
//			
//			destination = getRedirectedCell(a, (a+1)%2, cells, rowPositive, colPositive);
//			// knight is only allowed 2 moves in each direction
//			if (a == 3)
//				break;
//		}
//		return validCells;
//	}

}
