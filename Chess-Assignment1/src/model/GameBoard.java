package model;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class GameBoard {

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
//		Player[] players = new Player[] 
//		{ 
//	          new Player("1", "The Shark"), 
//	          new Player("2", "The Loser"),
//	          new Player("3", "Dateum"),
//	          new Player("4", "Plinga"),
//	          new Player("5", "Rupesh"),
//	          new Player("6", "Nihao"),
//	          new Player("7", "Destroyer")
//	    };
//
//		for (Player player : players)
//			gameEngine.addPlayer(player);
		
		GameBoard board = new GameBoard();
		int moveCount = 0;
		int maxMoveCount = 30;
		
		while (moveCount < maxMoveCount)
		{
			board.printGrid();
			
			System.out.print(board.getSelectedPlayer().toString() + " turn, choose a piece: ");
			board.select(sc.next());
			
			board.printGrid();
			
			System.out.println(board.getSelectedPiece().toString() + " has been selected");
			
			System.out.print("move to x: ");	int row = sc.nextInt();
			System.out.print("move to y: ");	int col = sc.nextInt();
			
			if (board.move(col, row) == false)
			{
				System.out.println("Invalid move - try again.");
				continue;
			}
			moveCount++;
			board.switchPlayer();
		}
	}
	
	
	
	
	
	
	
	
	
	
	public GameBoard()
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
		for (PlayerType player : PlayerType.values())
			players.put(player, new Player(player));
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
	
	public static int GRID_SIZE = 8;
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
		if (destination == null)
			throw new NullPointerException("invalid row or column");
		
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
	
	public void select(String key)
	{
		Piece desiredPiece = selectedPlayer.getPieces().get(key);
		if(desiredPiece != null) 
		{
			selectedPiece = desiredPiece;
			selectedCells = selectedPiece.getValidMoves(cells);
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
}
