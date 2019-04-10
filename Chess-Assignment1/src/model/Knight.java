package model;

import java.util.List;

public class Knight extends Piece {

	public Knight(Player player, String key) 
	{
		super(PieceType.KNIGHT, player, key);
	}
	
	
//	/*TO IMPLEMENT*/
//	public boolean isValidMove(Cell destination, List<Cell> cells)
//	{
//		// piecetype handles this
////		Move move = new Move(super.getCell(), destination);
////		
////		if (move.getXDist() == 1 && move.getYDist() == 2 ||
////			move.getXDist() == 2 && move.getYDist() == 1) 
////			return super.isValidMove(destination, cells);
//		return false;
//		
//	}

	@Override
	protected boolean isBlocked(Cell destination, List<Cell> cells) 
	{
		// knight is never blocked
		return false;
	}
	
	//not working
	public CellList getMoves(CellList cells, boolean xDirection, boolean yDirection) 
	{
		CellList validCells = new CellList();
		
		int y = getCell().getRow();
		int x = getCell().getCol();
		
		int moveY = xDirection ? 2 : -2;
		int moveX = yDirection ? 1 : -1;
		if(!(cells.get(y+moveY, x+moveX).getIsOccupied())) validCells.add(cells.get(y+moveY, x+moveX));
		
		moveY = xDirection ? 1 : -1;
		moveX = yDirection ? 2 : -2;
		if(!(cells.get(y+moveY, x+moveX).getIsOccupied())) validCells.add(cells.get(y+moveY, x+moveX));
		
		return validCells;
	}

}
