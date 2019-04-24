package model;

public class Cell {

	
	/*maybe use a map with a key string location*/
	public Cell(int rowNo, int column)
	{
		row = rowNo;
		col = column;
	}
	
	
	private int row;
	private int col;
	private Piece occupier;
	
	
	Piece removePiece()
	{
		Piece piece = occupier;
		occupier = null;
		return piece;
	}
	
	public void setOccupied(Piece piece)
	{
		occupier = piece;
	}
	
	public boolean getIsOccupied()
	{
		return occupier != null;
	}
	
	PlayerType getOccupiedType()
	{
		return occupier.getPlayer().getType();
	}
	
	Piece getOccupier() {
		return occupier;
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
		return "(" + col + "," + row + ") " + getPrintable(false, false);
	}
	
	public String getPrintable(boolean isSelected, boolean isDangerous)
	{
		String occupier = ".";
		if (this.occupier != null) 
		{
			occupier = this.occupier.getKey();
			if (getOccupiedType().equals(PlayerType.BLACK))
				occupier = this.occupier.getKey().toUpperCase();
		}
		return isSelected ? (isDangerous ? "x" : "o") : occupier;
	}
	
}
