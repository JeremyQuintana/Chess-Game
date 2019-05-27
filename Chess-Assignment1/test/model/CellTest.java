package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class CellTest extends TestCase {

	Cell cell;
	Piece piece;
	Piece piece2;
	
	@Before
	public void setUp() {
		cell = new Cell(0,0);
		Player player = new Player("JeremyIsAwesome", "idk");
		piece = new Rook("r1", player);
		piece2 = new Rook("k1", player);
	}
	
	@Test
	public void testRemovePiece() {
		cell.addOccupier(piece);
		cell.removeOccupiers();
		assertFalse(cell.getIsOccupied());
	}
	
	@Test
	public void testRemovePiece2() {
		cell.addOccupier(piece);
		cell.addOccupier(piece2);
		cell.removeOccupiers();
		assertFalse(cell.getIsOccupied());
	}

	@Test
	public void testSetOccupied() {
		cell.addOccupier(piece);
		assertTrue(cell.getOccupiers().contains(piece));
	}
	
	@Test
	public void testSetOccupied2() {
		cell.addOccupier(piece);
		cell.addOccupier(piece2);
		assertTrue(cell.getOccupiers().contains(piece) && cell.getOccupiers().contains(piece2));
	}

}
