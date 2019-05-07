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
	
	// Knights only have a count of moves
	boolean movesLeftToAdd2(int a, Cell destination)			 
	{
		return a <= MOVE_LIMIT;
	}
	
	// choose either (1,2) or (2,1)
	int getDestinationRow(int a, boolean rowPositive, boolean colPositive)
	{
		return getRedirectedRow((a-1)%2 + 1, rowPositive);
	}
	
	int getDestinationCol(int a, boolean rowPositive, boolean colPositive)
	{
		return getRedirectedCol(a%2 + 1, colPositive);
	}

}
