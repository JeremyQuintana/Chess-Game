package model;

import java.util.List;

public class Bishop extends Piece {

	public Bishop(Player player, String key) 
	{
		super(PieceType.BISHOP, player, key);
	}
	
//	/*TO IMPLEMENT*/
//	public boolean isValidMove(Cell destination, List<Cell> cells)
//	{
//		// piecetype handles this
////		Move move = new Move(super.getCell(), destination);
////		
////		if (move.getXDist() == move.getYDist()) 
////			return super.isValidMove(destination, cells);
//		return false;
//	}
	
	protected boolean isBlocked(Cell destination, List<Cell> cells) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//havent tested
	public CellList getMoves(CellList cells, boolean xDirection, boolean yDirection) 
	{
		CellList validCells = new CellList();
		
		int y = getCell().getRow();
		int x = getCell().getCol();
		while(y < GameBoard.GRID_SIZE && x < GameBoard.GRID_SIZE && x>=0 && y>=0) {
			if(cells.get(y, x).getIsOccupied()) break;
			else validCells.add(cells.get(y, x));
			
			y = yDirection ? y++ : y--;
			x = xDirection ? x++ : x--;
		}
		
		
		return validCells;
	}

}
