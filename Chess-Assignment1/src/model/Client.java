package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*
 * keeps a registry of all players involved
 * 
 * */

public class Client {
	

//	public static void main(String[] args) 
//	{
		
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
//		client.endGame();
//		
//		
//		Client client = new Client();
//	}
//	
//	
//	public Player getPlayer(Player opposer)
//	{
//		System.out.print("Enter player id: ");
//		Player gamer = playerList.get(sc.next());
//		
//		if (gamer == null)
//		{
//			System.out.println("Player with this id does not exist.");
//			return getPlayer(opposer);
//		}
//		else if (gamer == opposer)
//		{
//			System.out.println("This player is already selected");
//			return getPlayer(opposer);
//		}
//		else 
//			return checkPassword(gamer);
//	}
//
//	
//	private Player checkPassword(Player gamer)
//	{
//		System.out.print("Enter player password: ");
//		String pass = sc.next();
//		
//		if (gamer.getPassword().equals(pass))
//			return gamer;
//		System.out.println("Incorrect password. Try again.");
//		return checkPassword(gamer);
//	}
//	
//	public int getMaxCount()
//	{
//		System.out.print("Enter preferred max count: ");
//		int max;
//		try 
//		{
//			max = sc.nextInt();
//			if (max > 0)
//				return max;
//		}
//		catch (InputMismatchException e) 
//		{
//			sc = new Scanner(System.in);
//		}
//		System.out.println("please enter a valid amount greater than 0");
//		return getMaxCount();
//	}
	
	
	
	
	
	
	
	
	
	
//	
//	
//	public void makeMove()
//	{
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Move, merge, or split: ");
//		String[] command = sc.nextLine().split(" ");
//		
//		int oldScore = board.getSelectedPlayer().getScore();
//		boolean valid = false;
//		switch(command[0])
//		{
//			case "move" : 
//				int x = Integer.parseInt(command[1]);
//				int y = Integer.parseInt(command[2]);
//				valid = board.move(y,x);					
//				break;
//			case "merge" : valid = board.merge(command[1]);	break;	/*causes a bug*/
//			case "split" : valid = board.split();			break;
//		}
//		
//		if (!valid)
//		{
//			System.out.println("Invalid choice.");
//			makeMove();
//			return;
//		}
//		board.switchPlayer();
////		if (board.getSelectedPlayer().getScore() > oldScore)
////			System.out.println("+5 points to " + board.getSelectedPlayer().toString());
//	}
//	
//	public void selectPiece()
//	{
//		System.out.printf("%d/%d moves left\n", board.getMoveCount(), board.getMoveLimit());
//		System.out.print(board.getSelectedPlayer().toString() + " turn (Score = " + 
//						 board.getSelectedPlayer().getScore() +"), choose a piece: ");
//		String chosenPiece = sc.next().toLowerCase();
//		if (!board.select(chosenPiece)) 
//		{
//			System.out.println("Invalid piece selected. ");
//			selectPiece();
//		}	
//		System.out.println(board.getSelectedPiece().toString() + " has been selected");
//	}
//	
//	public void printGrid()
//	{
//		System.out.println();
//		for (Cell cell : board.getCells())
//		{
//			boolean isSelected = board.validMoves().contains(cell);
//			boolean isDangerous = board.isDangerousMove(cell, isSelected);
//			
//			System.out.printf("%-6s", cell.getPrintable(isSelected, isDangerous));
//			if (cell.getCol() == GameBoard.GRID_SIZE-1)
//				System.out.println("\n");
//		}
//		System.out.println();
//	}	
//	
//	public void endGame()
//	{
//		System.out.println("\n\nGAME OVER!");
//		if (board.getWinner() != null)
//			System.out.println("Player " + board.getWinner().getName() + " wins!");
//		else
//			System.out.println("It's a tie.");
//		
//	}
	
	
	
	
	
	
	
	
	
	
	
	
//	public Client(Player[] players)
//	{
//		
//		sc = new Scanner(System.in);
//		playerList = new HashMap<>();
//		for (Player p : players)
//			playerList.put(p.getName(), p);
//	}
	

	// get player by name
	private Map<String, Player> playerList;
	private Map<String, Player> logged;
	public Client() 
	{
		playerList = new HashMap<>();
		logged = new HashMap<>();
		
		try
		{
			register("a", "ab");
			register("b", "ab");
			register("c", "ab");
		} catch (ClientException e) {}
//		initialize the board
//		board = new GameBoard(p1, p2);
//		sc = new Scanner(System.in);
//		board.setMaxCount(15,15);
	}

	public Player login(String name, String password) throws ClientException
	{
		if (!playerList.containsKey(name))
			throw new ClientException("Player with name: " + name + " doesn't exist.");
		if (!playerList.get(name).getPassword().equals(password))
			throw new ClientException("Incorrect password.");
		if (logged.containsKey(name))
			throw new ClientException("Player already logged in.");
		/*test - what happens when trying to log same player again*/
		
		// login this player reference
		Player player = playerList.get(name);
		// reset logging process once 2nd player logged in
		if (logged.size() < 2)	logged.put(name, player);
		else 					logged.clear();
		return player;
	}
	
	public Player register(String name, String password) throws ClientException
	{
		if (password.length() < 2 || password.length() > 10)
			throw new ClientException("Please choose a password between 2 and 10 characters");
		if (password.equals(name) || password.equals("abcdefg"))
			throw new ClientException("Please choose a stronger password.");
		if (playerList.containsKey(name))
			throw new ClientException("Player already added");
		return playerList.put(name, new Player(name, password));
	}
//	
//	public void setBoard(GameBoard board)
//	{
//		this.board = board;
//	}
//	
//	public GameBoard getBoard()
//	{
//		return board;
//	}
	
	
	
	
	
	
	
	
	
	
	public class ClientException extends Exception
	{

		String message;
		public ClientException(String string) {
			message = string;
		}
		
		public String toString()
		{
			return message;
		}
		
	}
	
//	void testDisplay(String pieceKey)
//	{
//		Player p1 = new Player("JeremyIsAwesome", "idk");
//		Player p2 = new Player("TheOtherJeremyIsAwesome", "something");
//		board = new GameBoard(p1, p2);
//		
//		// manually remove all pieces except selected piece
//		for (Cell c : board.getCells())
//		{
//			if (c.getIsOccupied() )
//			{
//				Piece a = c.getOccupiers();
//				if (a.getKey().equals(pieceKey) && a.getPlayer().equals(board.getSelectedPlayer()))
//					continue;
//				c.removePiece();
//				a.getPlayer().removePiece(a.getKey());
//			}
//		}
//		
//		
//		Cell destination = board.getCell(2,2);
//		board.removePiece(destination);
//		board.select(pieceKey);
//		board.getSelectedPiece().move(destination);
//		board.select(pieceKey);
//		
//		// A central display of all possible moves/restrictions on moves
//		printGrid();
//	}
	
	
	
}

