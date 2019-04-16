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
		board = new GameBoard(p1, p2);
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
		board.select("r1");
		board.move(0, 2); // Move forward 2 cells
		// Check if the cell is correctly updated with the piece
		Cell updatedCell = null;
		for (Cell cell: board.getCells()) {
			if (cell.getCol() == 2 && cell.getRow() == 0) {
				updatedCell = cell;
				break;
			}
		}
		assertTrue(updatedCell.getOccupier().getPieceType().equals(PieceType.ROOK));
	}
	
	
	@Test
	public void testMove2() {
		board.select("r1");
		// Rook can only move forward or sideways
		boolean success = board.move(1, 3);
		assertFalse(success);
	}
	
	@Test
	public void testMove3() {
		board.select("r1");
		// Pieces cannot move out from the board grid of 6 x 6
		boolean success = board.move(7, 3);
		assertFalse(success);
	}

	@Test
	public void testSelect1() {
		// Player WHITE's turn
		// Select Rook from the pieces
		board.select("r1");
		// Check if the default position of rook1 for player WHITE is correct
		assertTrue(board.getSelectedPiece().getLocation().getCol() ==  0 && board.getSelectedPiece().getLocation().getRow() ==  0);
	}
	
	@Test
	public void testSelect2() {
		// Select Rook2 from the Player WHITE's pieces
		// Check if the current selected piece is correct or not
		board.select("r2");
		assertEquals(board.getSelectedPiece().getPieceType(), PieceType.ROOK);
	}
	
	@Test
	public void movementBlocked() {
		System.out.println("Movement Blocked");
		// Select Rook 1 from PlayerWHITE
		// Move Rook 1 to pos (0, 2)
		board.select("r1");
		board.move(0, 2);
		// Move Rook 1 to pos (1, 2)
		board.select("r1");
		board.move(1, 2);
		// Select Knight 1 from PlayerWHITE
		board.select("k1");
		// Invalid move as Rook 1 is in the position (1, 2);
		boolean success = board.move(1, 2);
		assertFalse(success);
	}
	
	@Test
	// To check if its removing the pieces correctly from Player's pieces
	public void pieceRemoved() {
		// First turn belongs to the PlayerType.WHITE
		board.select("r1");
		board.move(0, 2);
		// Switch to Player BLACK
		board.switchPlayer();
		board.select("r1");
		board.move(0, 3);
		// Switch to Player WHITE
		board.switchPlayer();
		board.select("r1");
		board.move(0, 3); // Player WHITE's rook1 has take over Player BLACK's rook1 in row 0 col 3
		// Switch to Player BLACK
		board.switchPlayer();
		// To check if the rook1 piece is removed from Player BLACK
		assertTrue(board.getSelectedPlayer().getPieces().get("r1") == null);
	}


}
