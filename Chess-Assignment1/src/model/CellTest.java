package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CellTest {
	
	Cell cell;
	Piece piece;
	
	@Before
	public void setUp() {
		cell = new Cell(0,0);
		Player player = new Player(PlayerType.WHITE);
		piece = new Rook(player, "r1");
	}
	
	@Test
	public void testRemovePiece() {
		cell.setOccupied(piece);
		cell.removePiece();
		assertTrue(!(cell.getIsOccupied()));
	}

	@Test
	public void testSetOccupied() {
		cell.setOccupied(piece);
		assertEquals(cell.getOccupier(), piece);
	}

}
