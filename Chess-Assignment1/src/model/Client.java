package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * keeps a registry of all players involved
 * 
 * */

public class Client {
	

	public static void main(String[] args) 
	{
		
		Player[] players = new Player[] 
		{ 
			new Player("Jeremy", "abcdefg"), 
			new Player("OOY", "22f923@M)#F"),
			new Player("Ru-bin", "coolbin")
		};
		Client client = new Client(players);
//		for (Player p : players)
//			board.addPlayer(p);
		
		Scanner sc = new Scanner(System.in);
		Player[] gamers = new Player[2];
		Integer[] moveCounts = new Integer[2];
		for (int i=0; i<gamers.length; i++)
		{
			while (true)
			{
				System.out.print("Enter player id: ");
				gamers[i] = client.getPlayer(sc.next());
				
				if (gamers[i] == null)
					System.out.println("Player with this id does not exist.");
				else if (gamers[0] == gamers[1])
					System.out.println("This player is already selected");
				else break;
			} 
			
			while (true)
			{
				System.out.print("Enter preferred max count: ");
				moveCounts[i] = sc.nextInt();
				
				if (moveCounts[i] <= 0)
					System.out.println("please enter a valid amount");
				else break;
			} 
		}
		
//		GameBoard board = new GameBoard(gamers[0], gamers[1]);
//		client.setBoard(board);
//		board.setMaxCount(moveCounts[0], moveCounts[1]);
//		
		GameBoard board = new GameBoard(new Player("Jerekmy", "abcdefg"), new Player("Jeremy", "abcdefg"));
		client.setBoard(board);
		board.setMaxCount(7, 90);
		
		
		while (board.isBelowMaxCount())
		{
			board.printGrid();
			client.selectPiece(sc);
			board.printGrid();
			client.makeMove(sc);
			board.switchPlayer();
		}
		

	}
	
//	public Object choose(String message, boolean condition)
//	{
//		do {
//			System.out.print("Enter preferred " + message + ": ");
//			array[i] = sc.nextInt();
//		} 
//		while (moveCounts[i] > 0);
//	}
	
	public void makeMove(Scanner sc)
	{
		System.out.print("move to x: ");	int x = sc.nextInt();
		System.out.print("move to y: ");	int y = sc.nextInt();
		
		if (!board.move(y, x))
		{
			System.out.println("Invalid cell selected.");
			makeMove(sc);
		}
	}
	
	public void selectPiece(Scanner sc)
	{
		System.out.print(board.getSelectedPlayer().toString() + " turn, choose a piece: ");
		String chosenPiece = sc.next().toLowerCase();
		if (!board.select(chosenPiece)) 
		{
			System.out.println("Invalid piece selected. ");
			selectPiece(sc);
		}	
		System.out.println(board.getSelectedPiece().toString() + " has been selected");
	}
	
	public Client(Player[] players)
	{
		playerList = new HashMap<>();
		for (Player p : players)
			playerList.put(p.getid(), p);
	}

	private Map<String, Player> playerList;
	private GameBoard board;
	
	
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
	
	
}
