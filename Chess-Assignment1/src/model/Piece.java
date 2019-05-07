package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public abstract class Piece {
	
	public Piece(Player player) 
	{
		this.player = player;
	}

	private Player player;
	private Cell location;
	// all pieces have a maximum of 2 moves they can make
	private static final int MOVE_LIMIT = 3;
	
	
	
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
	
	void move(Cell destination)
	{
		if (location != null)
			location.setOccupied(null);
		location = destination;
		location.setOccupied(this);
	}
	
	// facilitation of engine getValidMoves() so move checking continues relative to upgrades
	abstract List<SinglePiece> getPieces();
	abstract public String toString();
	abstract String getKey();
	
}

