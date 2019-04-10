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
	
	Cell getCell()
	{
		return location;
	}
	
	boolean isValidMove(Cell destination, List<Cell> cells)
	{
		Move move = new Move(location, destination);
		if (pieceType.isValidMove(move))
		{
			// if piece is blocked by the same player piece
			boolean isUnblocked = !isBlocked(destination, cells);
			// piece has actually moved from position
			boolean hasMoved = move.getXDist() != 0 || move.getYDist() != 0;
			// if other piece at destination
			boolean cellUnoccupied = destination.getIsOccupied() ? destination.getOccupiedType() != location.getOccupiedType() : true;
			
			return hasMoved && cellUnoccupied && isUnblocked;
		}
		return false;
	}
	

	
	// Assumes destination is in in the piece path
	abstract boolean isBlocked(Cell destination, List<Cell> cells);
	
	/*NO NEED TO IMPLEMENT YET*/
//	// filter possible destination cells
//	List<Cell> possibleMoves(CellList cells)
//	{
//		List<Cell> validCells = new LinkedList<>();
//		for (int a=1; ; a++)
//		{
//			
//			int row = location.getRow() + a;
//			int col = location.getCol() + a;
//			
//			if (cells.get(row,col).getIsOccupied())
//				break;
//			if (cells.)
//			validCells.add(cells.get(y,x));
//		}
//	}
	
	
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
	
	//not working
	public abstract CellList getMoves(CellList cells, boolean xDirection, boolean yDirection);
	//not working
	public CellList getValidMoves(CellList cells) {
		
		boolean[] values = {true, false};
		CellList validCells = new CellList();
		
		for (boolean rowPositive : values)
			for (boolean colPositive : values) {
				for(Cell cell : getMoves(cells, rowPositive, colPositive)) validCells.add(cell);
			}
		return validCells; //what happens when cell is fully blocked
		
	}

}

