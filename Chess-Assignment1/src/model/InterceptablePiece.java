package model;

public abstract class InterceptablePiece extends Piece {

	public InterceptablePiece(PieceType type, String key, PlayerType playerType) 
	{
		super(type, key, playerType);
	}
	
	
	boolean movesLeftToAdd2(int a, Cell destination)			 
	{
		// attribute used in getting valid moves to notify if:
		// a piece has blocked it from travelling forward
		// it has reached the end of the board
		boolean isIntercepted = destination == null || destination.getIsOccupied();
		return a==1 || !isIntercepted;
	}
}