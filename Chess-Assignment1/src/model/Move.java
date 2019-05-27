package model;

public class Move {

	// takes up more memory
	public Move(Cell location, Cell destination) 
	{
		this.location = location;
		this.destination = destination;
	}
	private Cell location;
	private Cell destination;
	
	// this is because for all 3 pieces, -/+ve does not matter
	public int getXDist()
	{
		return Math.abs(destination.getCol() - location.getCol());
	}
	
	public int getYDist()
	{
		return Math.abs(destination.getRow() - location.getRow());
	}
	
}
