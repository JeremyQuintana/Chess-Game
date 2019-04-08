package model;

public enum PieceType {

	ROOK, BISHOP, KNIGHT;
	
	public String getKey()
	{
		switch (this)
		{
			case ROOK : 	return "r";
			case BISHOP : 	return "b";
			case KNIGHT :	return "k";
		}
		return null;
	}
	
	public int defaultRow()
	{
		switch (this)
		{
			case ROOK : 	return 0;
			case BISHOP : 	return 1;
			case KNIGHT :	return 2;
		}
		return -1;
	}
	
}
