package model;

public class Bishop extends InterceptablePiece {

	public Bishop(String key, Player player) 
	{
		super(PieceType.BISHOP, key, player);
	}
	
	// bishop moves in pattern (a,a) (diagonal)
	int getDestinationRow(int a, boolean rowPositive, boolean colPositive)
	{
		return getRedirectedRow(a, rowPositive);
	}
	
	int getDestinationCol(int a, boolean rowPositive, boolean colPositive)
	{
		return getRedirectedCol(a, colPositive);
	}

}
