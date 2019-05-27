package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameBoardTest {
	
	
	GameBoard board;
	Player p1;
	Player p2;
	
	@Before
	public void setUp() {
		// When game board is created, two players are added into the game.
		p1 = new Player("JeremyIsAwesome", "idk");
		p2 = new Player("TheOtherJeremyIsAwesome", "something");
		board = new GameBoard(p1, p2, 2, 2, 2);
	}
	
	@Test
	public void testSwitchPlayer() {
		// Test if the player is switched correctly
		if(board.getSelectedPlayer().getType() == PlayerType.BLACK) {
			board.switchPlayer();
			assertEquals(board.getSelectedPlayer().getType(), PlayerType.WHITE);
		}
		else {
			board.switchPlayer();
			assertEquals(board.getSelectedPlayer().getType(), PlayerType.BLACK);
		}
	}
	
	@Test
	public void testMove1() {
		Cell cellInitial = board.getCell(0, 0);
		Cell cellDestination = board.getCell(0, 2);
		Piece piece = board.getSelectedPlayer().getPieces().get("r1");
		board.select(cellInitial);
		board.move(cellDestination); // Move forward 2 cells
		// Check if the cell is correctly updated with the piece
		assertTrue(cellInitial.getOccupiers().isEmpty() 
				&& cellDestination.getOccupiers().contains(piece));
	}
	
	//test move failure
	@Test
	public void testMove2() {
		Cell cellInitial = board.getCell(0, 0);
		Cell cellDestination = board.getCell(1, 2);
		Piece piece = board.getSelectedPlayer().getPieces().get("r1");
		board.select(cellInitial);
		board.move(cellDestination); // Move forward 2 cells
		// Check if the cell is correctly updated with the piece
		assertTrue(cellInitial.getOccupiers().contains(piece)
				&& cellDestination.getOccupiers().isEmpty());
	}
	
	//test merge move
	@Test
	public void testMove3() {
		Cell cellInitial = board.getCell(0, 0);
		Cell cellDestination = board.getCell(0, 2);
		Cell cellMerge = board.getCell(1, 0);
		Piece piece = board.getSelectedPlayer().getPieces().get("r1");
		Piece piece2 = board.getSelectedPlayer().getPieces().get("b1");
		board.select(cellInitial);
		board.merge(cellMerge);
		board.move(cellDestination); // Move forward 2 cells
		// Check if the cell is correctly updated with the piece
		assertTrue(cellDestination.getOccupiers().contains(piece)
				&& cellDestination.getOccupiers().contains(piece2)
				&& cellInitial.getOccupiers().isEmpty());
	}
	
	//test merge move failure
	@Test
	public void testMove4() {
		Cell cellInitial = board.getCell(0, 0);
		Cell cellDestination = board.getCell(1, 2);
		Cell cellMerge = board.getCell(1, 0);
		Piece piece = board.getSelectedPlayer().getPieces().get("r1");
		Piece piece2 = board.getSelectedPlayer().getPieces().get("b1");
		board.select(cellInitial);
		board.merge(cellMerge);
		board.move(cellDestination); // Move forward 2 cells
		// Check if the cell is correctly updated with the piece
		assertTrue(cellInitial.getOccupiers().contains(piece)
				&& cellInitial.getOccupiers().contains(piece2)
				&& cellDestination.getOccupiers().isEmpty());
	}
	
	@Test
	public void testSelect1() {
		// Player WHITE's turn
		// Select Rook from the pieces
		Cell cell = board.getCell(0, 0);
		board.select(cell);
		// Check if the default position of rook1 for player WHITE is correct
		assertTrue(board.getSelectedPiece().getLocation().getCol() ==  0 && board.getSelectedPiece().getLocation().getRow() ==  0);
	}
	
	@Test
	public void testSelect2() {
		// Select Rook2 from the Player WHITE's pieces
		// Check if the current selected piece is correct or not
		Cell cell = board.getCell(0, 0);
		board.select(cell);
		assertEquals(board.getSelectedPiece().getType(), PieceType.ROOK);
	}
	
	@Test
	public void testMovementBlocked() {
		// Select Rook 1 from PlayerWHITE
		// Move Rook 1 to pos (0, 2)
		Cell cellInitial = board.getCell(0, 0);
		Cell cellDestination = board.getCell(0, 2);
		Cell cellDestination2 = board.getCell(1, 2);
		board.select(cellInitial);
		Piece piece = board.getSelectedPiece();
		board.move(cellDestination);
		// Move Rook 1 to pos (1, 2)
		board.select(cellDestination);
		board.move(cellDestination2);
		// Select Knight 1 from PlayerWHITE
		Cell cellInitial2 = board.getCell(2, 0);
		board.select(cellInitial2);
		// Invalid move as Rook 1 is in the position (1, 2);
		board.move(cellDestination2);
		assertTrue(cellDestination2.getOccupiers().contains(piece));
	}
	
	@Test
	// To check if its removing the pieces correctly from Player's pieces
	public void testPieceRemoved() {
		// First turn belongs to the PlayerType.WHITE
		Cell cellInitial = board.getCell(0, 0);
		Cell cellDestination = board.getCell(0, 2);
		board.select(cellInitial);
		board.move(cellDestination);
		// Switch to Player BLACK
		board.switchPlayer();
		Cell cellInitial2 = board.getCell(0, 5);
		Cell cellDestination2 = board.getCell(0, 3);
		board.select(cellInitial2);
		board.move(cellDestination2);
		// Switch to Player WHITE
		board.switchPlayer();
		board.select(cellDestination);
		board.move(cellDestination2); // Player WHITE's rook1 has take over Player BLACK's rook1 in row 0 col 3
		// Switch to Player BLACK
		board.switchPlayer();
		// To check if the rook1 piece is removed from Player BLACK
		assertTrue(board.getSelectedPlayer().getPieces().get("r1") == null);
	}
	
	//test merge
	@Test
	public void testMerge1() {
		Cell cellInitial = board.getCell(0, 0);
		Cell cellInitial2 = board.getCell(1, 0);
		Piece piece1 = board.getSelectedPlayer().getPieces().get("r1");
		Piece piece2 = board.getSelectedPlayer().getPieces().get("b1");
		
		board.select(cellInitial);
		board.merge(cellInitial2);
		assertTrue(board.getCell(0, 0).getOccupiers().contains(piece1)
				&& board.getCell(0, 0).getOccupiers().contains(piece2));
	}
	
	//test failed merge
	@Test
	public void testMerge2() {
		Cell cellInitial = board.getCell(0, 0);
		Cell cellInitial2 = board.getCell(5, 0);
		Piece piece1 = board.getSelectedPlayer().getPieces().get("r1");
		Piece piece2 = board.getSelectedPlayer().getPieces().get("b1");
		
		board.select(cellInitial);
		board.merge(cellInitial2);
		assertTrue(board.getCell(0, 0).getOccupiers().size() == 1);
	}
	
	//test split
	@Test
	public void testSplit1() {
		Cell cellInitial = board.getCell(0, 0);
		Cell cellInitial2 = board.getCell(5, 0);
		Piece piece1 = board.getSelectedPlayer().getPieces().get("r1");
		
		board.select(cellInitial);
		board.merge(cellInitial2);
		board.split();
		assertTrue(board.getCell(0, 0).getOccupiers().contains(piece1));
	}
	
	//test invalid split
	@Test
	public void testSplit2() {
		Cell cellInitial = board.getCell(0, 0);
		Piece piece1 = board.getSelectedPlayer().getPieces().get("r1");
		
		board.select(cellInitial);
		board.split();
		assertTrue(board.getCell(0, 0).getOccupiers().contains(piece1));
	}
}
