package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
	
	Player player;
	Piece piece;
	Piece piece2;
	
	@Before
	public void setUp() {
		player = new Player("o", "j");
		piece = new Rook("r1", player);
		piece2 = new Rook("r2", player);
	}
	
	@Test
	public void testRemovePiece() {
		player.add(piece);
		player.remove(piece);
		assertTrue(player.getPieces().get("r1") == null);
	}

	@Test
	public void testAddScore() {
		player.addScore(200);
		assertEquals(player.getScore(), 200);
	}
	
	@Test
	public void testPlayer() {
		assertEquals("o", player.getName());
		assertEquals("j", player.getPassword());
	}
	
	@Test
	public void testGetPieces() {
		player.add(piece);
		player.add(piece2);
		assertTrue(player.getPieces().containsKey("r1") 
				&& player.getPieces().containsKey("r2"));
	}
	
}
