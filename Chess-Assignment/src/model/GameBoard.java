package model;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class GameBoard {

	public GameBoard()
	{
		cells = new LinkedList<>(); 	//map NOT used, bc it requires string/double key implementation, harder
		
		//initialize list of cells
		for (int col=0; col<GRID_SIZE; col++)
		for (int row=0; row<GRID_SIZE; row++)
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
			
			// if cell taken by eg: 1st white knight
			if (getCell(row, col).getIsOccupied())
				row = GRID_SIZE-1-row;
			
			move(row, col);
		}
	}
	
	public static int GRID_SIZE = 6;
	private List<Cell> cells;
	private Piece selectedPiece;
	private List<Cell> selectedCells;
	private Player selectedPlayer;
	private Map<PlayerType, Player> players;
	




	
	public void move(int row, int col)											
	{
		Cell destination = getCell(row, col);
		
		if (selectedPiece.isValidMove(destination))
		{
			// if your trying to kill opposer's piece
			removePiece(destination);
			selectedPiece.move(destination);
		}
	}
	
	public void select(String key)
	{
		selectedPiece = selectedPlayer.getPieces().get(key);
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
	
	// alternative to 2 key map
	private Cell getCell(int row, int col)
	{
		for (Cell cell : cells)
			if (cell.getRow() == row && cell.getCol() == col)
				return cell;
		return null;
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
	
	
	/*bad method placement, move?*/
	public void printGrid()
	{
		System.out.println();
		for (Cell cell : cells)
		{
			System.out.printf("%-3s",cell.toString());
			if (cell.getRow() == GRID_SIZE-1)
				System.out.println();
		}
		System.out.println();
	}
}
