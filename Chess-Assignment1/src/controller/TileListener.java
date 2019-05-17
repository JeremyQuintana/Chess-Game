package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

import model.Cell;
import model.GameBoard;
import model.Piece;
import view.BoardPanel;
import view.BoardPanel.CellPanel;
import view.MainFrame;

public class TileListener implements MouseListener{
//	private GameBoard board;
//	private String piece;
//	private int tileId;
//	private BoardPanel panel;
//	
//	public TileListener(final BoardPanel panel, GameBoard board,String piece, int tileId) {
//		this.panel = panel;
//		this.board = board;
//		this.piece = piece;
//		this.tileId = tileId;
//	}
//	@Override
//	public void mouseClicked(MouseEvent e) {
//		// TODO Auto-generated method stub
//		if(!panel.getSelected()) {
//			String oneOrTwo = null;
//			if(tileId<=6 || tileId>=30) {
//				if(tileId<=2||tileId<=32&&tileId>6) {oneOrTwo = "1";} else{oneOrTwo ="2";}
//				board.select(piece.charAt(0)+oneOrTwo);
//				List<Cell> validCell = board.validMoves(board.getSelectedPiece());
//				List<CellPanel> cellList =panel.getCellList();
//				panel.setSelected(true);
//				for(Cell cell: validCell) {
//					System.out.println(cell.getCol());
//					CellPanel cellPanel = cellList.get((cell.getCol()*6)+cell.getRow());
//					Border greenBorder = BorderFactory.createLineBorder(Color.GREEN,3);
//					cellPanel.setBorder(greenBorder);
//				}
//			}
//			
////			System.out.println("Clicked "+piece +oneOrTwo+" "+ tileId);
////			
////			System.out.println(board.getSelectedPiece());
////			System.out.println(board.getSelectedPlayer());
//		}
//		else {
//			JOptionPane.showMessageDialog(null, "You can only choose 1 piece each time", "Warning", JOptionPane.WARNING_MESSAGE);
//			System.out.println("You can only choose one piece per move");
//		}
//		
//		
//	}
	
	private Cell cell;
	private CellPanel panel;
	private MainFrame frame;
		
	// takes these attributes so CellPanel can be fully encapsulated
	public TileListener(CellPanel panel, MainFrame frame) 
	{
		this.cell = panel.getCell();
		this.panel = panel;
		this.frame = frame;
	}
	
	
	
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		boolean isValid = false;
		GameBoard board = frame.getBoard();
		switch (frame.getBoardPanel().getCurrentAction())
		{
			default :  			  frame.select(cell, board.isValidSelect(cell));	break;
			case MOVE : isValid = frame.move(cell, board.isValidMove(cell));		break;
			case MERGE :isValid = frame.merge(cell, board.isValidMerge(cell)); 		break;
			case SPLIT :isValid = frame.split(cell, board.isValidSplit()); 			break;
		}
		if (isValid)
			board.switchPlayer();
		if (board.isGameOver())
		{
			
		}
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

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
