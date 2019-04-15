package model;

import java.util.Scanner;

public class GameEngine {

	public static void main(String[] args) {
		// Creates two players for the game
		Player p1 = new Player(PlayerType.WHITE);
		Player p2 = new Player(PlayerType.BLACK);
		
		Scanner sc = new Scanner(System.in);
	
		GameBoard board = new GameBoard(p1, p2);
		int moveCount = 0;
		int maxMoveCount = 30;
		
		while (moveCount < maxMoveCount)
		{
			board.printGrid();
			
			while (true) {
				System.out.print(board.getSelectedPlayer().toString() + " turn, choose a piece: ");
				String chosenPiece = sc.next();
				if (board.select(chosenPiece)) {
					break;
				}	
			}
			
			board.printGrid();
			
			System.out.println(board.getSelectedPiece().toString() + " has been selected");

			while (true) {
				System.out.print("move to x: ");	int row = sc.nextInt();
				System.out.print("move to y: ");	int col = sc.nextInt();
				if (board.move(col, row))
				{
					break;
				}
			}
			
			moveCount++;
			board.switchPlayer();
		}

	}

}
