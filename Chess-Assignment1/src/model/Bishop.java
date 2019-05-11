package model;

import java.util.List;

public class Bishop extends InterceptablePiece {

	public Bishop(String key, PlayerType playerType) 
	{
		super(PieceType.BISHOP, key, playerType);
	}
	
	
	
	
//	// Bishop moves in a diagonal pattern (a, a)
//	Cell getDestination(int a, CellList cells, boolean rowPositive, boolean colPositive) 
//	{
//		return getRedirectedCell(a, a, cells, rowPositive, colPositive);
//	}
//	
//	boolean allMovesAdded(CellList validCells, int a, Cell destination)
//	{
//		if (destination == null || isSameOccupied(destination))		
//			return true;
//		validCells.add(destination);
//		if (isOpposerOccupied(destination))							
//			return true;
//		return false;
//	}

//	//havent tested
//	public CellList getMoves(CellList cells, boolean rowPositive, boolean colPositive) 
//	{
//		CellList validCells = new CellList();
//		
//		int y = getCell().getRow();
//		int x = getCell().getCol();
//		while(y < GameBoard.GRID_SIZE && x < GameBoard.GRID_SIZE && x>=0 && y>=0) {
//			if(cells.get(y, x).getIsOccupied()) break;
//			else validCells.add(cells.get(y, x));
//			
//			y = yDirection ? y++ : y--;
//			x = xDirection ? x++ : x--;
//		}
//		Cell destination = getLocation();
//		for (int a=1; destination != null && !isOccupied(destination); a++)
//		{
//			if (a>1)
//				validCells.add(destination);
//			
//			destination = super.a(a, a, cells, rowPositive, colPositive);
//		}
//		return validCells;
//	}
	
	// bishop moves in pattern (a,a)
	int getDestinationRow(int a, boolean rowPositive, boolean colPositive)
	{
		return getRedirectedRow(a, rowPositive);
	}
	
	int getDestinationCol(int a, boolean rowPositive, boolean colPositive)
	{
		return getRedirectedCol(a, colPositive);
	}

}
