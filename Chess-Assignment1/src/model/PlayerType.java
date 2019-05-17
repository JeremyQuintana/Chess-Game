package model;

import java.awt.Color;

public enum PlayerType {
	WHITE, BLACK;
	
//	public String getKey(String s)
//	{
//		switch (this)
//		{
//			case WHITE : 	return s.toUpperCase();
//			case BLACK : 	return s.toLowerCase();
//		}
//		return null;
//	}
	
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
	
	public Color getColor()
	{
		switch (this)
		{
			case WHITE : 	return Color.WHITE;
			case BLACK : 	return Color.BLACK;
		}
		return null;
	}
}
