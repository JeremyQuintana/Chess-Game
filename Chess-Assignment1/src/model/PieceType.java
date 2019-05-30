package model;

// keep as a static pre-constructed version of piece
// also allows cheaper storage of piece by only storing its type
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
	
}
