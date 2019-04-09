package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameBoardTest {
	
	GameBoard board = new GameBoard();
	
	@Test
	public void testMove() {
		board.select("r1");
		assertTrue(board.move(0, 3));
	}

	@Test
	public void testSelect() {
		board.select("r1");
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
