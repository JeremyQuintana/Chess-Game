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

}
