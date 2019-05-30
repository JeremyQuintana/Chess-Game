package model;

public enum PlayerType {
	WHITE, BLACK;
	
	public int defaultColumn()
	{
		switch (this)
		{
			case WHITE : 	return 0;
			case BLACK : 	return GameBoard.GRID_SIZE-1;
		}
		return -1;
	}
	
	public PlayerType getOpposer()
	{
		switch (this)
		{
			case WHITE : 	return BLACK;
			case BLACK : 	return WHITE;
		}
		return null;
	}
}
