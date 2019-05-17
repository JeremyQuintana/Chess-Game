package model;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class GameBoard {
	
	
	public GameBoard(Player p1, Player p2)
	{	
		//initialize list of cells
		cells = new LinkedList<>();
		for (int row=0; row<GRID_SIZE; row++)
		for (int col=0; col<GRID_SIZE; col++)
			getCells().add(new Cell(row,col));       
		
//		playerList = new LinkedList<>();
		moveCount = 0;
		
		
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
	
	public static int GRID_SIZE = 6;
	private List<Cell> cells;
	private Piece selectedPiece;
	private Player selectedPlayer;
	private Map<PlayerType, Player> players;
	// contains all registered players
//	private List<Player> playerList;
	private int moveCount;
	private int moveLimit;
	


	public void switchPlayer()
	{
		selectedPiece = null;
		moveCount++;
		selectedPlayer = getOpposer();
	}
	
	
	
	
	
	
	
	// chooses a row and column to allow view/GUI compatibility
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
		if (cell.getTotalSinglePieces() == 0)
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
	
	
	
	
	
	
	
	

//	public boolean select(String key)
//	{
//		Piece piece = selectedPlayer.getPieces().get(key);
//		if (piece == null)
//			return false;
//		
//		selectedPiece = piece;
//		return true;
//	}
//
//	public boolean merge(String key)
//	{
//		// check if possible to merge: aka no repeating piece types
//		Piece piece = selectedPlayer.getPieces().get(key);
//		for (Piece p1 : piece.getLinks())
//		for (Piece p2 : selectedPiece.getLinks())
//			if(p1.getType() == p2.getType())
//					return false;
//		
//		selectedPiece.merge(piece);
//		return true;
//	}
	
	
//	public boolean select(Cell cell)
//	{
//		if (!cell.getIsOccupied())
//			return false;
//		if (cell.getOccupiedType() != selectedPlayer.getType())
//			return false;
//		selectedPiece = cell.getOccupiers().get(0);
//		return true;
//	}
//																		
//	// returns true if split successful
//	// design choice : all split pieces change location
//	public boolean split()
//	{	
//		if (selectedPiece.getLinks().size() < 2)
//			return false;
//		selectedPiece.split(splitDestinations());
//		return true;
//	}
//	
//	public boolean merge(Cell pieceHolder)
//	{
//		if (!isValidMerge(pieceHolder))
//			return false;
//		selectedPiece.merge(pieceHolder.getOccupiers().get(0));
//		return true;
//	}
//	
//	// returns true if the move successful
//	public boolean move(Cell destination)											
//	{
//		if (!validMoves().contains(destination))
//			return false;
//		awardAndRemove(destination);
//		selectedPiece.move(destination);											
//		return true;
//	}
	
	/*
	 * throw exceptions for failed operations?
	 *merge tests: 	can merge 1 piece with a merged piece and vice versa
	 * 				pieces move to selected piece's location
	 *split tests: 	can't split 1 piece
	 *				splitting moves unselected pieces to special locations
	 *UI tests: selecting another same-type piece when moving, causes it to select that piece
	 *			selecting pieces when selected
	 *			selecting opposite pieces (NOT valid) triggers a message
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// a method to return all valid destinations for a piece
	public List<Cell> validMoves() 
	{
		return validMoves(selectedPiece);
	}
	
	public List<Cell> validMoves(Piece piece)
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
		
		/*test cases - if the cell contains opposer piece, that cell is valid*/
		/*test cases - bishop/rook intercepted by any piece, but extra cell if its opposer piece*/
		/*test cases - knight*/
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
			selectedPlayer.addScore(5 * destination.getTotalSinglePieces());
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
		for (int row=0;  row<GRID_SIZE; row++)
		{
			boolean occupied = getCell(row, col).getIsOccupied();
			if (!occupied)
				destinations[i++] = getCell(row, col);
			if (i==destinations.length-1)	
				break;
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
		return moveCount >= moveLimit || noPiecesLeft;
	}
	
	Player getWinner()
	{
		int whiteScore = players.get(PlayerType.WHITE).getScore();
		int blackScore = players.get(PlayerType.BLACK).getScore();
		
		if (whiteScore != blackScore)
			return players.get(whiteScore > blackScore ? PlayerType.WHITE : PlayerType.BLACK);
		else return null;
	}
	
	public int getMoveCount()
	{
		return moveCount;
	}
	
	int getMoveLimit()
	{
		return moveLimit;
	}
	
	public int setMaxCount(int maxCount1, int maxCount2)
	{
		moveLimit = (maxCount1+maxCount2)/2;
		return moveLimit;
	}
	
}
