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
	
	//not working
	public CellList getMoves(CellList cells, boolean xDirection, boolean yDirection) 
	{
		CellList validCells = new CellList();
		int y = getCell().getRow() + 1;
		int x = getCell().getCol();
		while(y < GameBoard.GRID_SIZE && y>=0) {
			if(cells.get(y, x).getIsOccupied()) break;
			else validCells.add(cells.get(y, x));
			
			y = yDirection ? y+1 : y-1;
		}
		
		y = getCell().getRow();
		x = getCell().getCol() + 1;
		while(x < GameBoard.GRID_SIZE && x>=0) {
			if(cells.get(y, x).getIsOccupied()) break;
			else validCells.add(cells.get(y, x));
			
			x = xDirection ? x+1 : x-1;
		}
		int i = 0;
		for(Cell cell : validCells) {
			i++;
		}
		System.out.println(xDirection + " " + yDirection);
		System.out.println(i);
		return validCells;
	}

}
