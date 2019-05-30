package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.Cell;
import model.GameBoard;
import view.BoardPanel.CellPanel;
import view.MainFrame;

public class CellListener extends MouseAdapter
{
	
	private Cell cell;
	private CellPanel panel;
	private MainFrame frame;
		
	// takes these attributes so CellPanel can be fully encapsulated
	public CellListener(CellPanel panel, MainFrame frame) 
	{
		this.cell = panel.getCell();
		this.panel = panel;
		this.frame = frame;
	}
	
	
	
	
	
	//depending on what "State"/action board is in
	// cells will act accordingly
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		boolean validTurn = false;
		GameBoard board = frame.getBoard();
		switch (frame.getBoardPanel().getCurrentAction())
		{
			default :  			  	frame.select(cell, board.isValidSelect(cell));	break;
			case MOVE : validTurn = frame.move(cell, board.isValidMove(cell));		break;
			case MERGE :validTurn = frame.merge(cell, board.isValidMerge(cell)); 	break;
			case SPLIT :validTurn = frame.split(cell, board.isValidSplit()); 		break;
		}
		// alternative to a callback class
		if (validTurn)			frame.switchPlayer();
		// instance check so no else if
		if (board.isGameOver())	frame.endGame();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) 
	{
		if (panel.getIsHighlighted())
			panel.hover(true);
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) 
	{
		if (panel.getIsHighlighted())
			panel.hover(false);
	}

}
