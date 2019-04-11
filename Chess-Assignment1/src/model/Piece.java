package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public abstract class Piece {

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		GameBoard board = new GameBoard();
		
		while (true)
		{
			board.printGrid();
			
			System.out.print(board.getSelectedPlayer().toString() + " turn, choose a piece: ");
			board.select(sc.next());
			
			board.printGrid();
			
			System.out.println(board.getSelectedPiece().toString() + " has been selected");
			
			System.out.print("move to x: ");	int row = sc.nextInt();
			System.out.print("move to y: ");	int col = sc.nextInt();
			
			
			System.out.println(board.move(col, row));				
			
			board.switchPlayer();
		}

	}
	
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
	
//	boolean isValidMove(Cell destination, List<Cell> cells)
//	{
//		Move move = new Move(location, destination);
//		if (pieceType.isValidMove(move))
//		{
//			// if piece is blocked by the same player piece
//			boolean isUnblocked = !isBlocked(destination, cells);
//			// piece has actually moved from position
//			boolean hasMoved = move.getXDist() != 0 || move.getYDist() != 0;
//			// if other piece at destination
//			boolean cellUnoccupied = destination.getIsOccupied() ? destination.getOccupiedType() != location.getOccupiedType() : true;
//			
//			return hasMoved && cellUnoccupied && isUnblocked;
//		}
//		return false;
//	}
	
	

	
	// Assumes destination is in in the piece path
	abstract boolean isBlocked(Cell destination, List<Cell> cells);
	// get Cell depending on its move pattern and current iteration a
	abstract Cell getDestination(int a, CellList cells, boolean rowPositive, boolean colPositive);
	// orders external loop to break or continue
	abstract Statement breakCondition(int a, Cell destination);
	
	
	
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
	
//	public abstract CellList getMoves(CellList cells, boolean rowPositive, boolean colPositive);
	
	// a method to return all valid destinations for a piece
	public CellList getValidMoves(CellList cells) {
		
		boolean[] values = {true, false};
		CellList validMoves = new CellList();
		
		// checks valid destinations in all 4 directions (+ +) (+ -) (- +) (- -)
		for (boolean rowPositive : values)
		for (boolean colPositive : values) 
		{
			int apj=0;
			// unified getMoves method for all 3 pieces
			k : for (int a=1; true; a++)
			{
				Cell destination = getDestination(a, cells, rowPositive, colPositive);
				
				// knight has slightly different break conditions
				boolean isKnight = PieceType.KNIGHT==pieceType;
				if (isKnight && a > Knight.MOVE_LIMIT)
					break;
				else if (destination == null || isOccupied(destination))
					if (isKnight) 	
						continue; 
					else 			
						break;
//				switch (breakCondition(a, destination))
//				{
//					case BREAK: 	break k;
//					case CONTINUE: 	continue k;
//					case NOTHING: 	break;
//				}
				validMoves.add(destination);
				
				if (!isKnight && destination.getIsOccupied() && destination.getOccupiedType() != getPlayer().getType())
					break;
			}
		}
		System.out.println(validMoves);
		return validMoves;
		/*account for case where there are no moves?? or will this piece together*/
	}
	
	// if another same-player piece at destination
	boolean isOccupied(Cell destination)
	{
		return destination.getIsOccupied() && destination.getOccupiedType() == getPlayer().getType();
	}
	
	// depending on direction, chooses coordinates of cell accordingly
	Cell getRedirectedCell(int rowDist, int colDist, CellList cells, boolean rowPositive, boolean colPositive)
	{
		int row = location.getRow();
		int col = location.getCol();
		int newRow = row + (rowPositive ? rowDist : -rowDist);
		int newCol = col + (colPositive ? colDist : -colDist);
		return cells.get(newRow, newCol);
	}
	
	
	enum Statement 
	{
		BREAK, CONTINUE, NOTHING
	}
	
}

