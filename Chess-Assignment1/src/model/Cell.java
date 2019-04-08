package model;

public class Cell {

	
	/*maybe use a map with a key string location*/
	public Cell(int rowNo, int column)
	{
		row = rowNo;
		col = column;
		isOccupied = false;
	}
	
	
	private int row;
	private int col;
	private boolean isOccupied;
	private Piece occupier;
	
	
	Piece removePiece()
	{
		Piece piece = occupier;
		occupier = null;
		return piece;
	}
	
	void setOccupied(Piece piece)
	{
		occupier = piece;
		isOccupied = piece != null;
	}
	
	public boolean getIsOccupied()
	{
		return isOccupied;
	}
	
	PlayerType getOccupiedType()
	{
		return occupier.getPlayer().getType();
	}
	
	int getRow()
	{
		return row;
	}
	int getCol()
	{
		return col;
	}
	
	public String toString()
	{
		return isOccupied ? occupier.getKey() : ".";
	}
	
}
