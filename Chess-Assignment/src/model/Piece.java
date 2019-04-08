package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Piece {

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
			
			System.out.print("move to row: ");	int row = sc.nextInt();
			System.out.print("move to col: ");	int col = sc.nextInt();
			
			/*this is how "isValidMove method called"*/
			board.move(row, col);				/*this is how "isValidMove method called"*/
			
			board.switchPlayer();
		}
//		board.isValidMove(sc.next());

	}

									

	
	// in future when we create actual rook, knight, bishop classes
	public Piece(Player player)
	{
		this.player = player;
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
	
	public PieceType getPieceType()
	{
		return pieceType;
	}
	public Player getPlayer()
	{
		return player;
	}
	public void setCell(Cell location)
	{
		this.location = location;
	}
	
	public Cell getCell()
	{
		return location;
	}
	
	
	/*TO IMPLEMENT*/
	public boolean isValidMove(Cell destination)
	{
		int row = destination.getRow();
		int col = destination.getCol();
		if (pieceType.getKey().equals("r")) {
			if (row != location.getRow() && col != location.getCol()) {
				return false;
			}
			if (row != location.getRow() && col == location.getCol()) {
				return true;
			}
			if (col != location.getCol() && row == location.getRow()) {
				return true;
			}
		}
		
		else if (pieceType.getKey().equals("b")) {
			if (row != location.getRow() && col != location.getCol()) {
				return true;
			}
			return false;
		}
		else if (pieceType.getKey().equals("k")) {
			if (row == location.getRow()+2) {
				if (col == location.getCol()+1 || col == location.getCol()-1) {
					return true;
				}
			}
			else if (row == location.getRow()-2) {
				if (col == location.getCol()+1 || col == location.getCol()-1) {
					return true;
				}
			}
			return false;
		}
		//	figure out general rules for any piece
		// eg: if (row>5 && col>5)
		//		return false;
		return true;
	}
	
	/*NO NEED TO IMPLEMENT YET*/
//	// filter possible destination cells
//	public List<Cell> possibleMoves(List<Cell> destinations)
//	{
//		
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

}

