package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {
	Player player = new Player(PlayerType.WHITE);
	
	@Test
	public void testRemovePiece() {
		player.removePiece("r1");
		assertTrue(player.getPieces().get("r1") == null);
	}

	@Test
	public void testAddScore() {
		player.addScore(200);
		assertEquals(player.getScore(), 200);
	}
	
	@Test
	public void testPlayer() {
		assertEquals(PlayerType.WHITE, player.getType());
	}

}
