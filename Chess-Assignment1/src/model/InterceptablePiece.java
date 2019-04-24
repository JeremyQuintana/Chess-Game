package model;

public abstract class InterceptablePiece extends Piece {

	public InterceptablePiece(PieceType piece, Player player, String key) 
	{
		super(piece, player, key);
		isIntercepted = false;
	}
	
	// attribute used in getting valid moves to notify if:
	// a piece has blocked it from travelling forward
	// it has reached the end of the board
	private boolean isIntercepted;
	
	void moveUpdate(Cell destination) 
	{
		if (destination == null || destination.getIsOccupied())
			isIntercepted = true;
	}
	
	boolean movesLeftToAdd2(int a)			 
	{
		boolean isClear = !isIntercepted;
		// reset isIntercepted
		if (isIntercepted == true)
			isIntercepted = false;
		return isClear;
	}
}