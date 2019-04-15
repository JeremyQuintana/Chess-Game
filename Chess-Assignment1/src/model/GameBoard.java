package model;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class GameBoard {
	
	
	public GameBoard(Player p1, Player p2)
	{	
		//initialize list of cells
		cells = new CellList(); 
		selectedCells = new CellList();
		for (int row=0; row<GRID_SIZE; row++)
		for (int col=0; col<GRID_SIZE; col++)
			cells.add(new Cell(row,col));
		
		// instantiates the black and white players 
		// Name, password can be determined at runtime
		players = new HashMap<>();                

		players.put(p1.getType(), p1);
		players.put(p2.getType(), p2);
		selectedPlayer = players.get(PlayerType.WHITE);
		
	
		// place pieces in default locations
		for (Player player : players.values())
		for (Piece piece : player.getPieces().values())				
		{
			selectedPiece = piece;
			int col = player.getType().defaultColumn();
			int row = piece.getPieceType().defaultRow();
			
			if (piece.getKey().contains("2"))
				row = GRID_SIZE-1-row;
			
			selectedPiece.move(cells.get(row, col));
		}
		
//		test to see if returned valid cells are correct (not working)
//		select("r1");
//		for(Cell cell : selectedPiece.getValidMoves(cells)) {
//			System.out.println("y=" + cell.getRow() + " x=" + cell.getCol());
//		}
	}
	
	public static int GRID_SIZE = 6;
	private CellList cells;
	private Piece selectedPiece;
	private CellList selectedCells;
	private Player selectedPlayer;
	private Map<PlayerType, Player> players;
	public Object getCells;
	




	// returns true if the move successful
	public boolean move(int row, int col)											
	{
		boolean moveSuccess = false;
		Cell destination = cells.get(row, col);
		
		/*if input invalid row/col - test*/
		if (destination == null ) {
			System.out.println("Invalid move - try again.");
			return moveSuccess;
//			throw new NullPointerException("invalid row or column");	
		}
		else {
			if (!selectedCells.contains(destination)) {
				System.out.println("Invalid move - try again.");
				return moveSuccess;
			}	
		}
		
		if (selectedCells.contains(destination))
		{
			moveSuccess = true;
			// if your trying to kill opposer's piece
			/*piece doesn't get removed? - test*/
			removePiece(destination);
			selectedPiece.move(destination);
		}
		// deselect pieces
		selectedPiece = null;
		selectedCells = new CellList();
		return moveSuccess;
	}
	
	public boolean select(String key)
	{
		Piece desiredPiece = selectedPlayer.getPieces().get(key);
		if(desiredPiece != null) 
		{
			selectedPiece = desiredPiece;
			selectedCells = getValidMoves();
			return true;
		}
		else {
			System.out.println("Piece selected not available. Please choose another piece.");
			return false;
//			throw new NullPointerException("no piece selected");
		}
	}
	
	public void switchPlayer()
	{
		selectedPlayer = players.get(selectedPlayer.getType().getOpposer());
	}
	
	private void removePiece(Cell destination)
	{
		if (destination.getIsOccupied())
			if (destination.getOccupiedType() != selectedPiece.getPlayer().getType())
			{
				Piece piece = destination.removePiece();
			
				Player winner = selectedPlayer;
				Player loser = players.get(selectedPlayer.getType().getOpposer());
				winner.addScore(1000); 											/*how many*/
				loser.removePiece(piece.getKey());
			}
	}
	
	public Piece getSelectedPiece()
	{
		return selectedPiece;
	}
	
	public List<Cell> getSelectedCells()					/*NOT implemented*/
	{
		return selectedCells;
	}
	
	public Player getSelectedPlayer()
	{
		return selectedPlayer;
	}
	
	public CellList getCells() {
		return cells;
	}
	
	
	public void printGrid()
	{
		System.out.println();
		for (Cell cell : cells)
		{
			System.out.printf("%-3s", cell.getPrintable(selectedCells.contains(cell)));
			if (cell.getCol() == GRID_SIZE-1)
				System.out.println();
		}
		System.out.println();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// a method to return all valid destinations for a piece
	public CellList getValidMoves() {
		
		boolean[] values = {true, false};
		CellList validMoves = new CellList();
		
		// checks valid destinations in all 4 directions (+ +) (+ -) (- +) (- -)
		for (boolean rowPositive : values)
		for (boolean colPositive : values) 
			for (int a=1; a<=Piece.MOVE_LIMIT && selectedPiece.movesLeftToAdd(a); a++)
			{
				int newRow = selectedPiece.getDestinationRow(a, rowPositive, colPositive);
				int newCol = selectedPiece.getDestinationCol(a, rowPositive, colPositive);
				
				Cell destination = cells.get(newRow, newCol);
				if (selectedPiece.isValidMove2(destination))
					validMoves.add(destination);	
			}
		
		/*test cases - if the cell contains opposer piece, that cell is valid*/
		/*test cases - bishop/rook intercepted by any piece, but extra cell if its opposer piece*/
		/*test cases - knight*/
		
		System.out.println(validMoves);
		return validMoves;
	}
	
	
}
