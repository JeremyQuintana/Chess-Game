package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameBoardTest {
	
	GameBoard board = new GameBoard();
	
	@Test
	public void testMove1() {
		board.select("r1");
		assertTrue(board.move(0, 3));
	}
	
	@Test
	public void testMove2() {
		board.select("r1");
		assertTrue(!(board.move(1, 3)));
	}
	
	@Test (expected=NullPointerException.class)
	public void testMove3() {
		board.select("r1");
		board.move(7, 3);
	}

	@Test
	public void testSelect1() {
		board.select("r1");
		assertTrue(board.getSelectedPiece().getCell().getCol() ==  0 && board.getSelectedPiece().getCell().getRow() ==  0);
	}
	
	@Test
	public void testSelect2() {
		board.select("r1");
		board.select("JeremyIsAwesome");
		assertTrue(board.getSelectedPiece().getCell().getCol() ==  0 && board.getSelectedPiece().getCell().getRow() ==  0);
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
