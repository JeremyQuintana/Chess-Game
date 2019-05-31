package model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Similar to information expert
 * "Logic" handler of all Actions (move/merge/split/select)
 */

public class GameBoard {
	
	// maxCounts are player choices for maximum moves
	// moveRange is the range any piece (rook/bishop) can move
	public GameBoard(Player p1, Player p2, int maxCount1, int maxCount2, int moveRange)
	{	
		
		//initialize list of cells
		cells = new LinkedList<>();
		for (int row=0; row<GRID_SIZE; row++)
		for (int col=0; col<GRID_SIZE; col++)
			getCells().add(new Cell(col, row));       
		
		moveCount = 0;
		moveLimit = (maxCount1+maxCount2)/2;
		Piece.MOVE_LIMIT = moveRange;
		
		p1.setType(PlayerType.WHITE);
		p2.setType(PlayerType.BLACK);
		players = new HashMap<>();   
		players.put(p1.getType(), p1);
		players.put(p2.getType(), p2);
		selectedPlayer = players.get(PlayerType.WHITE);
	
		// place pieces in default locations
		for (Player player : players.values())
		for (Piece piece : player.getPieces().values())				
		{
			int col = player.getType().defaultColumn();
			int row = piece.getType().defaultRow();
			
			if (piece.getKey().contains("2"))
				row = GRID_SIZE-1-row;
			piece.move(getCell(row, col));
		}

	}
	
	public static int GRID_SIZE = 6;
	private List<Cell> cells;
	Piece selectedPiece;
	Player selectedPlayer;
	private Map<PlayerType, Player> players;
	private int moveCount;
	private int moveLimit;
	
	public void switchPlayer()
	{
		selectedPiece = null;
		moveCount++;
		selectedPlayer = getOpposer();
	}
	
	// chooses a cell to allow view/GUI compatibility
	public boolean isValidSelect(Cell cell)
	{
		if (!cell.getIsOccupied())
			return false;
		return cell.getOccupiedType() == selectedPlayer.getType();
	}

	public boolean isValidMove(Cell destination)
	{
		return validMoves().contains(destination);
	}

	public boolean isValidMerge(Cell cell)
	{
		if (cell.getOccupiers().size() == 0)
			return false;
		for (Piece p1 : cell.getOccupiers())
		for (Piece p2 : selectedPiece.getLinks())
			if(p1.getType() == p2.getType())
					return false;
		return true;
	}
	
	public boolean isValidSplit()
	{
		return selectedPiece.getLinks().size() > 1;
	}
	
	
	// contract: only called when valid
	public void select(Cell cell)
	{
		selectedPiece = cell.getOccupiers().get(0);
	}
	
	public void merge(Cell cell)
	{
		Piece piece = cell.getOccupiers().get(0);
		selectedPiece.merge(piece);
	}
	
	public void split()
	{
		selectedPiece.split(splitDestinations());
	}
	
	public void move(Cell destination)
	{
		awardAndRemove(destination);
		selectedPiece.move(destination);
	}
	
	// a method to return all valid destinations for a piece
	public List<Cell> validMoves() 
	{
		return validMoves(selectedPiece);
	}
	
	private List<Cell> validMoves(Piece piece)
	{
		boolean[] values = {true, false};
		List<Cell> validMoves = new ArrayList<>();
		Cell destination = null;
		
		if (piece == null)
			return validMoves;
		
		// check all move possibilities of piece with multiple types
		for (Piece p : piece.getLinks())
			// checks valid destinations in all 4 directions (+ +) (+ -) (- +) (- -)
			for (boolean rowPositive : values) 
			for (boolean colPositive : values) 
				for (int a=1; p.movesLeftToAdd(a, destination); a++)
				{
					int newRow = p.getDestinationRow(a, rowPositive, colPositive);
					int newCol = p.getDestinationCol(a, rowPositive, colPositive);
					
					destination = getCell(newRow, newCol);
					if (p.isValidMove(destination))
						validMoves.add(destination);	
				}
		return validMoves;
	}
	
	public List<Cell> validMerges()
	{
		List<Cell> validMoves = new ArrayList<>();
		
		if (selectedPiece == null)
			return validMoves;
		
		for (Piece piece : selectedPlayer.getPieces().values()) 
			if (isValidMerge(piece.getLocation()))
				validMoves.add(piece.getLocation());
		return validMoves;
	}
	
	// if moving to destination leaves piece vulnerable
	public boolean isDangerousMove(Cell destination, boolean isSelected)
	{
		// if it kills one opposer, the piece is newly vulnerable to other opposers originally being blocked
		List<Piece> killedOccupiers = destination.removeOccupiers();
		boolean isDangerousMove = isSelected ? canOpposerMoveTo(destination) : false;
		// leave the original board unchanged, replace the pieces
		for (Piece piece : killedOccupiers)
			destination.addOccupier(piece);
		return isDangerousMove;
	}
	
// specialist functions
	
	
	private void awardAndRemove(Cell destination)
	{
		if (destination.getIsOccupied())
		{
			selectedPlayer.addScore(5 * destination.getOccupiers().size());
			for (Piece piece : destination.removeOccupiers())
				getOpposer().remove(piece);
		}
	}
	
	// allocate split pieces to default locations
	private Cell[] splitDestinations()
	{
		Cell[] destinations = new Cell[selectedPiece.getLinks().size()];
		int i=0;
		int col = selectedPiece.getPlayerType().defaultColumn();
		for (int row=0; i<destinations.length; row++)
		{
			boolean occupied = getCell(row, col).getIsOccupied();
			if (!occupied)
				destinations[i++] = getCell(row, col);
		}
		return destinations;		
	}
	
	
	private boolean canOpposerMoveTo(Cell destination)
	{
		Player opposer = players.get(selectedPlayer.getType().getOpposer());
		for (Piece opposingPiece : opposer.getPieces().values())
			if (validMoves(opposingPiece).contains(destination))
				return true;
		return false;
	}
	
	
	public Piece getSelectedPiece()
	{
		return selectedPiece;
	}
	
	public Player getSelectedPlayer()
	{
		return selectedPlayer;
	}
	
	public Player getOpposer()
	{
		return players.get(selectedPlayer.getType().getOpposer());
	}
	
	public Map<PlayerType, Player> getPlayers()
	{
		return players;
	}
	
	public boolean isSelected()
	{
		return selectedPiece != null;
	}
	
	public Cell getCell(int row, int col) 
	{
		for (Cell cell : getCells())
			if (cell.getRow() == row && cell.getCol() == col)
				return cell;
		return null;
	}
	
	public List<Cell> getCells() {
		return cells;
	}
	
	public boolean isGameOver()
	{
		boolean noPiecesLeft = false;
		for (Player player : players.values())
			if (player.getPieces().isEmpty())
				noPiecesLeft = true;
		return movesRemaining() <= 0 || noPiecesLeft;
	}
	
	// for displaying the moves left
	// encapsulates the actual count and limit
	public int movesRemaining()
	{
		return moveLimit - moveCount;
	}
	
	public Player getWinner()
	{
		int whiteScore = players.get(PlayerType.WHITE).getScore();
		int blackScore = players.get(PlayerType.BLACK).getScore();
		
		if (whiteScore != blackScore)
			return players.get(whiteScore > blackScore ? PlayerType.WHITE : PlayerType.BLACK);
		// implies no winner/ out of moves
		else return null;
	}
}
