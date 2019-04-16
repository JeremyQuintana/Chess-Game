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
		cells = new LinkedList<>(); 
		selectedCells = new LinkedList<>();
		for (int row=0; row<GRID_SIZE; row++)
		for (int col=0; col<GRID_SIZE; col++)
			cells.add(new Cell(row,col));       
		
//		playerList = new LinkedList<>();
		moveCount = 0;
		
		p1.setType(PlayerType.WHITE);
		p2.setType(PlayerType.BLACK);
		
		gamers = new HashMap<>();   
		gamers.put(p1.getType(), p1);
		gamers.put(p2.getType(), p2);
		selectedPlayer = gamers.get(PlayerType.WHITE);
	
		// place pieces in default locations
		for (Player player : gamers.values())
		for (Piece piece : player.getPieces().values())				
		{
			selectedPiece = piece;
			int col = player.getType().defaultColumn();
			int row = piece.getPieceType().defaultRow();
			
			if (piece.getKey().contains("2"))
				row = GRID_SIZE-1-row;
			
			selectedPiece.move(getCell(row, col));
		}
//		test to see if returned valid cells are correct (not working)
//		select("r1");
//		for(Cell cell : selectedPiece.getValidMoves(cells)) {
//			System.out.println("y=" + cell.getRow() + " x=" + cell.getCol());
//		}
	}
	
//	public void startGame(Player p1, Player p2)
//	{
//		p1.setType(PlayerType.WHITE);
//		p2.setType(PlayerType.BLACK);
//		
//		gamers = new HashMap<>();   
//		gamers.put(p1.getType(), p1);
//		gamers.put(p2.getType(), p2);
//		selectedPlayer = gamers.get(PlayerType.WHITE);
//	
//		// place pieces in default locations
//		for (Player player : gamers.values())
//		for (Piece piece : player.getPieces().values())				
//		{
//			selectedPiece = piece;
//			int col = player.getType().defaultColumn();
//			int row = piece.getPieceType().defaultRow();
//			
//			if (piece.getKey().contains("2"))
//				row = GRID_SIZE-1-row;
//			
//			selectedPiece.move(cells.get(row, col));
//		}
//	}
	
	public static int GRID_SIZE = 9;
	private List<Cell> cells;
	private Piece selectedPiece;
	private List<Cell> selectedCells;
	private Player selectedPlayer;
	private Map<PlayerType, Player> gamers;
	// contains all registered players
//	private List<Player> playerList;
	private int moveCount;
	private int maxCount;
	




	// returns true if the move successful
	public boolean move(int row, int col)											
	{
		Cell destination = getCell(row, col);
		
		if (selectedCells.contains(destination))
		{
			removePiece(destination);
			selectedPiece.move(destination);
			moveCount++;
			deSelect();
			return true;
		}
		deSelect();
		return false;
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
		return false;
	}
	
	private void deSelect()
	{
		selectedPiece = null;
		selectedCells = new LinkedList<>();
	}
	
	public void switchPlayer()
	{
		selectedPlayer = gamers.get(selectedPlayer.getType().getOpposer());
	}
	
	private void removePiece(Cell destination)
	{
		if (destination.getIsOccupied())
			if (destination.getOccupiedType() != selectedPiece.getPlayer().getType())
			{
				Piece piece = destination.removePiece();
			
				Player winner = selectedPlayer;
				Player loser = gamers.get(selectedPlayer.getType().getOpposer());
				winner.addScore(5); 											
				loser.removePiece(piece.getKey());
			}
	}
	
	// a method to return all valid destinations for a piece
		public List<Cell> getValidMoves() {
			
			boolean[] values = {true, false};
			List<Cell> validMoves = new LinkedList<>();
			
			// checks valid destinations in all 4 directions (+ +) (+ -) (- +) (- -)
			for (boolean rowPositive : values)
			for (boolean colPositive : values) 
				for (int a=1; a<=Piece.MOVE_LIMIT && selectedPiece.movesLeftToAdd(a); a++)
				{
					int newRow = selectedPiece.getDestinationRow(a, rowPositive, colPositive);
					int newCol = selectedPiece.getDestinationCol(a, rowPositive, colPositive);
					
					Cell destination = getCell(newRow, newCol);
					if (selectedPiece.isValidMove2(destination))
						validMoves.add(destination);	
				}
			
			/*test cases - if the cell contains opposer piece, that cell is valid*/
			/*test cases - bishop/rook intercepted by any piece, but extra cell if its opposer piece*/
			/*test cases - knight*/
			
			System.out.println(validMoves);
			return validMoves;
		}
	
	public Piece getSelectedPiece()
	{
		return selectedPiece;
	}
	
	public Player getSelectedPlayer()
	{
		return selectedPlayer;
	}
	
	Cell getCell(int row, int col) 
	{
		for (Cell cell : cells)
			if (cell.getRow() == row && cell.getCol() == col)
				return cell;
		return null;
	}
	
	
	
	boolean isBelowMaxCount()
	{
		return moveCount < maxCount;
	}
	
	int setMaxCount(int maxCount1, int maxCount2)
	{
		maxCount = (maxCount1+maxCount2)/2;
		return maxCount;
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
}
