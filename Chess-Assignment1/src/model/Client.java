package model;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

/*
 * keeps a registry of all players involved
 * 
 * */

public class Client {
	

	public static void main(String[] args) 
	{
		
//		Player[] players = new Player[] 
//		{ 
//			new Player("Jeremy", "a"), 
//			new Player("OOY", "2"),
//			new Player("Rubin", "co")
//		};
//		
//		Client client = new Client(players);
//		// since there's no player to compare to.
//		Player gamer1  = client.getPlayer((Player) null);
//		int maxCount1 = client.getMaxCount(); 
//		
//		Player gamer2  = client.getPlayer(gamer1);
//		int maxCount2 = client.getMaxCount(); 
//		
//		// initialize the game board
//		GameBoard board = new GameBoard(gamer1, gamer2);
//		client.setBoard(board);
//		board.setMaxCount(maxCount1, maxCount2);
//		
//		while (!board.isGameOver())
//		{
//			client.printGrid();
//			client.selectPiece();
//			client.printGrid();
//			client.makeMove();
//		}
		
		// initialize the game board
		Player p1 = new Player("JeremyIsAwesome", "idk");
		Player p2 = new Player("TheOtherJeremyIsAwesome", "something");
		GameBoard board = new GameBoard(p1, p2);
		Client client = new Client();
		board.setMaxCount(5,4);
		client.setBoard(board);
		
		while (!board.isGameOver())
		{
			client.printGrid();
			client.selectPiece();
			client.printGrid();
			client.makeMove();
		}		
		client.endGame();
	}
	
	
	public Player getPlayer(Player opposer)
	{
		System.out.print("Enter player id: ");
		Player gamer = playerList.get(sc.next());
		
		if (gamer == null)
		{
			System.out.println("Player with this id does not exist.");
			return getPlayer(opposer);
		}
		else if (gamer == opposer)
		{
			System.out.println("This player is already selected");
			return getPlayer(opposer);
		}
		else 
			return checkPassword(gamer);
	}
	
	private Player checkPassword(Player gamer)
	{
		System.out.print("Enter player password: ");
		String pass = sc.next();
		
		if (gamer.getPassword().equals(pass))
			return gamer;
		System.out.println("Incorrect password. Try again.");
		return checkPassword(gamer);
	}
	
	public int getMaxCount()
	{
		System.out.print("Enter preferred max count: ");
		int max;
		try 
		{
			max = sc.nextInt();
			if (max > 0)
				return max;
		}
		catch (InputMismatchException e) 
		{
			sc = new Scanner(System.in);
		}
		System.out.println("please enter a valid amount greater than 0");
		return getMaxCount();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public void makeMove()
	{
		System.out.print("move to x: ");	int x = sc.nextInt();
		System.out.print("move to y: ");	int y = sc.nextInt();
		
		int oldScore = board.getSelectedPlayer().getScore();
		if (!board.move(y, x))
		{
			System.out.println("Invalid cell selected.");
			makeMove();
		}
		
		if (board.getSelectedPlayer().getScore() > oldScore)
			System.out.println("+5 points to " + board.getSelectedPlayer().toString());
		board.switchPlayer();
	}
	
	public void selectPiece()
	{
		System.out.printf("%d/%d moves left\n", board.getMoveCount(), board.getMoveLimit());
		System.out.print(board.getSelectedPlayer().toString() + " turn (Score = " + 
						 board.getSelectedPlayer().getScore() +"), choose a piece: ");
		String chosenPiece = sc.next().toLowerCase();
		if (!board.select(chosenPiece)) 
		{
			System.out.println("Invalid piece selected. ");
			selectPiece();
		}	
		System.out.println(board.getSelectedPiece().toString() + " has been selected");
	}
	
	public void printGrid()
	{
		System.out.println();
		for (Cell cell : board.getCells())
		{
			boolean isSelected = board.getSelectedCells().contains(cell);
			System.out.printf("%-3s", cell.getPrintable(isSelected));
			if (cell.getCol() == GameBoard.GRID_SIZE-1)
				System.out.println();
		}
		System.out.println();
	}	
	
	public void endGame()
	{
		System.out.println("\n\nGAME OVER!");
		if (board.getWinner() != null)
			System.out.println("Player " + board.getWinner().getid() + " wins!");
		else
			System.out.println("It's a tie.");
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public Client(Player[] players)
	{
		sc = new Scanner(System.in);
		playerList = new HashMap<>();
		for (Player p : players)
			playerList.put(p.getid(), p);
	}

	public Client()
	{
		sc = new Scanner(System.in);
	}
	private Map<String, Player> playerList;
	private GameBoard board;
	private Scanner sc;
	
	
	Player getPlayer(String id)
	{
		return playerList.get(id);
	}
	void setBoard(GameBoard board)
	{
		this.board = board;
	}
	
	GameBoard getBoard()
	{
		return board;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	void testDisplay(String pieceKey)
	{
		Player p1 = new Player("JeremyIsAwesome", "idk");
		Player p2 = new Player("TheOtherJeremyIsAwesome", "something");
		board = new GameBoard(p1, p2);
		
		// manually remove all pieces except selected piece
		for (Cell c : board.getCells())
		{
			if (c.getIsOccupied() )
			{
				Piece a = c.getOccupier();
				if (a.getKey().equals(pieceKey) && a.getPlayer().equals(board.getSelectedPlayer()))
					continue;
				c.removePiece();
				a.getPlayer().removePiece(a.getKey());
			}
		}
		
		
		Cell destination = board.getCell(2,2);
		board.removePiece(destination);
		board.select(pieceKey);
		board.getSelectedPiece().move(destination);
		board.select(pieceKey);
		
		// A central display of all possible moves/restrictions on moves
		printGrid();
	}
	
	
	
}
