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
		// When game board is created, two players is added into the game.
		p1 = new Player(PlayerType.WHITE);
		p2 = new Player(PlayerType.BLACK);
		board = new GameBoard(p1, p2);
	}
	
	@Test
	public void testMove1() {
		// 
		board.select("r1");
		board.move(0, 3);
		assertTrue(board.getCells().get(0, 3).getOccupier() == board.getSelectedPiece());
	}
	
	@Test (expected=InvalidMoveException.class)
	public void testMove2() {
		board.select("r1");
		board.move(1, 3);
	}
	
	@Test (expected=NullPointerException.class)
	public void testMove3() {
		board.select("r1");
		board.move(7, 3);
	}

	@Test
	public void testSelect1() {
		board.select("r1");
		assertTrue(board.getSelectedPiece().getLocation().getCol() ==  0 && board.getSelectedPiece().getLocation().getRow() ==  0);
	}
	
	@Test
	public void testSelect2() {
		board.select("r1");
		board.select("JeremyIsAwesome");
		assertTrue(board.getSelectedPiece().getLocation().getCol() ==  0 && board.getSelectedPiece().getLocation().getRow() ==  0);
	}

	@Test
	public void testSwitchPlayer() {
		if(board.getSelectedPlayer().getType() == PlayerType.BLACK) {
			board.switchPlayer();
			assertEquals(board.getSelectedPlayer().getType(), PlayerType.WHITE);
		}
		else {
			board.switchPlayer();
			assertEquals(board.getSelectedPlayer().getType(), PlayerType.BLACK);
		}
	}

}
