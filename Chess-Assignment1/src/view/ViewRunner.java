package view;

import model.Client;
import model.GameBoard;
import model.Player;
import model.PlayerType;

public class ViewRunner {

	public static void main (String[] args) {
		Player p1 = new Player("JeremyIsAwesome", "idk", PlayerType.WHITE);
		Player p2 = new Player("TheOtherJeremyIsAwesome", "something", PlayerType.BLACK);
		
		GameBoard board = new GameBoard(p1, p2);
		MainFrame start = new MainFrame(board);
//		Client client = new Client();
//		board.setMaxCount(15,15);
//		client.setBoard(board);
//		
//		while (!board.isGameOver())
//		{
//			client.printGrid();
//			client.selectPiece();
//			client.printGrid();
//			client.makeMove();
//		}		
//		client.endGame();
		
		
	}
}
