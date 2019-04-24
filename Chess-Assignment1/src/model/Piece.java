package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public abstract class Piece {
	
	public Piece(PieceType piece, Player player, String key) 
	{
		pieceType = piece;
		this.player = player;
		this.key = key;
	}

	private PieceType pieceType;
	private Player player;
	private String key;
	//how to get the cell and WHY to get the cell
	private Cell location;
	// all pieces have a maximum of 2 moves they can make
	private static final int MOVE_LIMIT = 3;
	
	PieceType getPieceType()
	{
		return pieceType;
	}
	Player getPlayer()
	{
		return player;
	}
	void setCell(Cell location)
	{
		this.location = location;
	}
	
	Cell getLocation()
	{
		return location;
	}
	
	boolean isValidMove(Cell destination)
	{
		moveUpdate(destination);
		if (destination == null)
			return false;
		if (destination.getIsOccupied())
			return player.getType() != destination.getOccupiedType();
		return true;
	}
	
	// adds destination if it fits rules, and breaks out of loop if no more to be added
	boolean movesLeftToAdd(int a)
	{
		if (a > Piece.MOVE_LIMIT)
			return false;
		return movesLeftToAdd2(a);
		
	}
	
	abstract void moveUpdate(Cell destination);
	abstract boolean movesLeftToAdd2(int a);
	
	
	
	void move(Cell destination)
	{
		if (location != null)
			location.setOccupied(null);
		location = destination;
		location.setOccupied(this);
	}
	
	// if white rook returns "R", black knight returns "k"
	public String toString()
	{
		return pieceType.name();
	}
	
	public String getKey()
	{
		return key;
	}
	
	
	
	
	// get row/column depending on its move pattern and current iteration a
	abstract int getDestinationRow(int a, boolean rowPositive, boolean colPositive);
	abstract int getDestinationCol(int a, boolean rowPositive, boolean colPositive);
	
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
	
	
}

