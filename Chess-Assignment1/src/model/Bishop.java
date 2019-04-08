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
	

}
