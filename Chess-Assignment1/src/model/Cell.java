package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


// handles knowledge of merge/split operations
// merge by linking: o-o-o   split by delinking:o o o
public class Cell {

	
	public Cell(int rowNo, int column)
	{
		row = rowNo;
		col = column;
		occupiers = new ArrayList<>();
	}
	
	int row;
	int col;
	private List<Piece> occupiers;
	
	public void addOccupier(Piece piece)
	{
		occupiers.add(piece);
	}
	
	public boolean getIsOccupied()
	{
		return !occupiers.isEmpty();
	}
	
	public List<Piece> removeOccupiers()
	{
		List<Piece> pieces = new ArrayList<>();
		for (Piece piece : occupiers)
			pieces.add(piece);
		occupiers.clear();
		return pieces;
	}
	
	// if there are more than one pieces, means the pieces are merged
	public List<Piece> getOccupiers()
	{
		return occupiers;
	}
	
	// multiple pieces (aka merged/split) can only be of same type
	PlayerType getOccupiedType()	
	{
		return occupiers.isEmpty() ? null : occupiers.get(0).getPlayerType();
	}
	// returns the number of individual single pieces
	int getTotalSinglePieces() 			
	{
		return occupiers.size();
	}	
	
	public int getRow()
	{
		return row;
	}
	public int getCol()
	{
		return col;
	}
	
	public String toString()
	{
		String string = "(" + col + "," + row + ") " + getPrintable(false, false);
		if (col==GameBoard.GRID_SIZE-1)
			string += "\n";
		return string;
	}
	
	public String getPrintable(boolean isSelected, boolean isDangerous)  
	{
		String occupier = "";
		for (Piece piece : occupiers)			occupier += piece.getKey();
		if (getOccupiedType() == PlayerType.BLACK)	occupier.toUpperCase();
		if (occupiers.isEmpty())					occupier = ".";
		return isSelected ? (isDangerous ? "x" : "o") : occupier;
	}
	
}
