package model.pieces;

import java.util.List;

import model.Cell;
import model.Piece;
import model.PieceType;
import model.Player;
import model.PlayerType;

public class Rook extends Piece {

	public Rook(Player player) {
		super(player);

		this.player = player;
	}
	
	private Player player;
	
	/*TO IMPLEMENT*/
	public boolean isValidMove(Cell destination, List<Cell> cells)
	{
		// moves specific to the piece
		//...
		int row = destination.getRow();
		int col = destination.getCol();
		if (row != getCell().getRow() && col != getCell().getCol()) {
			return false;
		}
		if (row != getCell().getRow() && col == getCell().getCol()) {
			for (Cell cell: cells) {
				if (cell.getIsOccupied()) {
					if (cell.getCol() == col && cell.getRow() < destination.getRow()) {
						return false;
					}
				}
			}
			return true;
		}
		if (col != getCell().getCol() && row == getCell().getRow()) {
			for (Cell cell: cells) {
				if (cell.getIsOccupied()) {
					if (cell.getRow() == row && cell.getCol() < destination.getCol()) {
						return false;
						
					}
				}
			}
			return true;
		}
		return false;
		
	}
	
	/*CREATE THE KNIGHT AND BISHOP CLASSES AND IMPLEMENT THERE TOO*/

}
