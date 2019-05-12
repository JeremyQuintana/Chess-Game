package view;

import model.GameBoard;
import model.Player;
import model.PlayerType;
import model.Client;

public class ViewRunner {

	public static void main (String[] args) {
		Player p1 = new Player("JeremyIsAwesome", "idk", PlayerType.WHITE);
		Player p2 = new Player("TheOtherJeremyIsAwesome", "something", PlayerType.BLACK);
		GameBoard board = new GameBoard(p1, p2);
		MainFrame start = new MainFrame(board);

//		ChessFrame start = new ChessFrame(new Client());

		
	}
}
