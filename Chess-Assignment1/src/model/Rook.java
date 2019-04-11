package model;

import java.util.LinkedList;
import java.util.List;

import model.Piece.Statement;

public class Rook extends Piece {

	public Rook(Player player, String key) 
	{
		super(PieceType.ROOK, player, key);
	}
	
//	/*TO IMPLEMENT*/
//	public boolean isValidMove(Cell destination, List<Cell> cells)
//	{	
//		// PieceType handles this
//		Move move = new Move(super.getCell(), destination);
//		
//		if (getPieceType().isValidMove(move) && !isBlocked()) 
//			return super.isValidMove(destination, cells);
//		return false;
//	}

	@Override
	protected boolean isBlocked(Cell destination, List<Cell> cells) 
	{
		return false;
	}
	
	
//	//not working
//	public CellList getMoves(CellList cells, boolean rowPositive, boolean colPositive) 
//	{
//		CellList validCells = new CellList();
////		int y = getCell().getRow() + 1;
////		int x = getCell().getCol();
////		while(y < GameBoard.GRID_SIZE && y>=0) {
////			if(cells.get(y, x).getIsOccupied()) break;
////			else validCells.add(cells.get(y, x));
////			
////			y = yDirection ? y+1 : y-1;
////		}
////		
////		y = getCell().getRow();
////		x = getCell().getCol() + 1;
////		while(x < GameBoard.GRID_SIZE && x>=0) {
////			if(cells.get(y, x).getIsOccupied()) break;
////			else validCells.add(cells.get(y, x));
////			
////			x = xDirection ? x+1 : x-1;
////		}
////		int i = 0;
////		for(Cell cell : validCells) {
////			i++;
////		}
////		System.out.println(xDirection + " " + yDirection);
////		System.out.println(i);
////		return validCells;
//		
//		Cell destination = getLocation();
//		for (int a=1; destination != null && !isOccupied(destination); a++)
//		{
//			if (a>1)
//				validCells.add(destination);
//			
//			int rowDist = rowPositive ? a : 0;
//			int colDist = rowPositive ? 0 : a;
//			destination = super.a(rowDist, colDist, cells, rowPositive, colPositive);
//		}
//		return validCells;
//		
////		int a=1;
////		Cell destination;
////		
////		do {
////			
////			// what about finding another piece
////			try
////			{
////				int rowDist = rowPositive ? a : 0;
////				int colDist = colPositive ? 0 : a;
////				destination = super.a(rowDist, colDist, cells, rowPositive, colPositive);
////				validCells.add(destination);
////			}
////			catch (NullPointerException e)
////			{
////				// reached the end of the board
////				break;
////			}
////			a++;
////		} 
////		while (!super.isOccupied(destination));
////		return validCells;
//	}

	// Rook moves in a straight pattern (a,0) or (0,a)
	//	row col		row col 	
	//	(a,0)		+ 	+		
	//	(0,a)		+ 	+		messes with
	// 	(-a,0)		- 	-		original getDestination
	//	(a,-x)		- 	-		
	Cell getDestination(int a, CellList cells, boolean rowPositive, boolean colPositive) 
	{
		
		int rowDist = colPositive ? a : 0;
		int colDist = colPositive ? 0 : a; 
		//													fix				vvv
		return getRedirectedCell(rowDist, colDist, cells, rowPositive, rowPositive);
	}
	
	Statement breakCondition(int a, Cell destination)			/*now fix the block detection rooks cant detect each other and passes thru*/ 
	{
		if (destination == null || isOccupied(destination))	return Statement.BREAK;
		else												return Statement.NOTHING;
	}

}
