package model;

public enum PieceType {

	ROOK, BISHOP, KNIGHT;
	
	String getKey()
	{
		switch (this)
		{
			case ROOK : 	return "r";
			case BISHOP : 	return "b";
			case KNIGHT :	return "k";
		}
		throw new NullPointerException("key doesn't exist");
	}
	
	int defaultRow()
	{
		switch (this)
		{
			case ROOK : 	return 0;
			case BISHOP : 	return 1;
			case KNIGHT :	return 2;
		}
		throw new NullPointerException("piece does not exist");
	}
	
	boolean isValidMove(Move move)
	{
		int xDist = move.getXDist();
		int yDist = move.getYDist();
		switch (this)
		{
			case ROOK : 	return 	xDist == 0 || yDist == 0;
			case BISHOP : 	return 	xDist == yDist;
			case KNIGHT :	return  xDist == 1 && yDist == 2 ||
									xDist == 2 && yDist == 1;
		}
		throw new NullPointerException("piece does not exist");
	}
	
	int getMoveLimit()
	{
		switch (this)
		{
			case ROOK : 	return 	2;
			case BISHOP : 	return 	2;
			case KNIGHT :	return  2;
		}
		throw new NullPointerException("piece does not exist");
	}
	
}
