package view;

<<<<<<< HEAD
import model.GameBoard;
import model.Player;
import model.PlayerType;
=======
import model.Client;
>>>>>>> 2f147b86f10b8f6179814acd942b4231b8a3c58e

public class ViewRunner {

	public static void main (String[] args) {
<<<<<<< HEAD
		Player p1 = new Player("JeremyIsAwesome", "idk", PlayerType.WHITE);
		Player p2 = new Player("TheOtherJeremyIsAwesome", "something", PlayerType.BLACK);
		GameBoard board = new GameBoard(p1, p2);
		MainFrame start = new MainFrame(board);
=======
		ChessFrame start = new ChessFrame(new Client());
>>>>>>> 2f147b86f10b8f6179814acd942b4231b8a3c58e
		
	}
}
