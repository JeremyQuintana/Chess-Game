package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class CellTest extends TestCase {

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
		assertFalse(cell.getIsOccupied());
	}

	@Test
	public void testSetOccupied() {
		cell.setOccupied(piece);
		assertEquals(cell.getOccupier(), piece);
	}

}
