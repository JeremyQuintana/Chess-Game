package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

// a piece 
public abstract class Piece {
	
	public Piece(PieceType piece, String key, PlayerType playerType) 
	{
		type = piece;
		this.playerType = playerType;
		this.key = key;
	}
	
	private String key;
	private PieceType type;
	private PlayerType playerType;
	private Cell location;
	// all pieces have a maximum of 2 moves they can make
	static final int MOVE_LIMIT = 2;
	
	
	
	public Cell getLocation()
	{
		return location;
	}
	
	// moving a merged/single piece
	public void move(Cell destination)
	{
		List<Piece> a = removeAndRetrieveLinks();
		for (Piece piece : a)															/*creating a copy?*/
			moveLink(piece, destination);
	}
	
	
	// when splitting, all pieces (except selected) move to different cells 	
	// split pieces don't have a common cell
	public void split(Cell[] destinations)																		/*create a warning that piece location will be changed*/
	{
		int i=0;
		for (Piece piece : removeAndRetrieveLinks())
		{
			boolean isSamePiece = type == piece.getType();
			moveLink(piece, isSamePiece ? location : destinations[i]);
			if (!isSamePiece)
				i++;
		}
	}
	
	// tell the piece requesting merge to move to this location
	public void merge(Piece piece)
	{
		piece.move(location);
	}
	
	PieceType getType()
	{
		return type;
	}
	
	public PlayerType getPlayerType()
	{
		return playerType;
	}
	
	// if white rook returns "R", black knight returns "k"
	public String toString()
	{
		return type.name();
	}
	
	public String getKey()
	{
		return key;
	}
	
	// returns multiple pieces if it is merged
	List<Piece> getLinks()
	{
		return location.getOccupiers();
	}
	// empties the cell of its occupiers
	private List<Piece> removeAndRetrieveLinks()
	{
		return location!=null ? 
				location.removeOccupiers() : 
			Collections.singletonList(this);
	}
	
	// a link is a piece that it may have merged with
	// a piece is its own link
	private void moveLink(Piece link, Cell destination)
	{
		link.location = destination;
		destination.addOccupier(link);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// get row/column depending on its move pattern and current iteration a
	abstract int getDestinationRow(int a, boolean rowPositive, boolean colPositive);
	abstract int getDestinationCol(int a, boolean rowPositive, boolean colPositive);
	
	
	// adds destination if it fits rules, and breaks out of loop if no more to be added
	boolean movesLeftToAdd(int a, Cell destination)
	{
		if (a > Piece.MOVE_LIMIT)
			return false;
		return movesLeftToAdd2(a, destination);
	}
	
	int getRedirectedRow(int rowDist, boolean rowPositive)
	{
		int oldRow = location.getRow();
		int newRow = oldRow + (rowPositive ? rowDist : -rowDist);
		return newRow;
	}
	
	int getRedirectedCol(int colDist, boolean colPositive)
	{
		int oldCol = location.getCol();
		int newCol = oldCol + (colPositive ? colDist : -colDist);
		return newCol;
	}
	
	boolean isValidMove(Cell destination)
	{
		if (destination == null)
			return false;
		if (destination.getIsOccupied())
			return playerType != destination.getOccupiedType();
		return true;
	}
	
	abstract boolean movesLeftToAdd2(int a, Cell destination);
	
}