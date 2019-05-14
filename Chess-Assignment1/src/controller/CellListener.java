package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
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

public class CellListener implements MouseListener{
	private GameBoard board;
	private int tileId;
	private BoardPanel panel;
	private CellPanel thisCell;
	private MainFrame frame;
	private List<CellPanel> cellList;
	
	public CellListener(BoardPanel panel, GameBoard board, int tileId, CellPanel thisCell,MainFrame frame) {
		this.panel = panel;
		this.board = board;
		this.tileId = tileId;
		this.thisCell=thisCell;
		this.frame = frame;
		cellList = panel.getCellList();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(thisCell.getBorder()!=null) {
			int sourceX = board.getSelectedPiece().getLocation().getCol();
			int sourceY = board.getSelectedPiece().getLocation().getRow();
			int sourceTileId = sourceX*6+sourceY;
			
			int y = tileId/6;
			int x = tileId-6*y;
			
			board.move(x,y);
			
			String wob = Character.toString(board.getSelectedPlayer().toString().charAt(0)).toLowerCase();
			panel.removeDrawing(sourceTileId);
			CellPanel test = cellList.get(tileId);
			if(test.getComponentCount()==1) {System.out.println("kill");
			panel.removeDrawing(tileId);}
					
			panel.drawThePiece(tileId, board.getSelectedPiece().toString(), wob);
			
			
		    for(CellPanel cell: cellList) {
			cell.setBorder(null);
			}
			board.switchPlayer();
		    panel.setSelected(false);
		}
		else if(!panel.getSelected()) {
			System.out.println("tileId = "+tileId);
			int y = tileId/6;
			int x = tileId-6*y;
			System.out.println("x = "+x+", y = "+y);
			if(thisCell.getComponentCount()!=0) {
				List<Piece> pieces = board.getCell(x,y).getOccupiers();
				System.out.println(pieces.toString());
				String key = pieces.get(0).getKey();
				board.select(key);
				List<Cell> validCell = board.validMoves(board.getSelectedPiece());
				panel.setSelected(true);
				for(Cell cell: validCell) {
					CellPanel cellPanel = cellList.get((cell.getCol()*6)+cell.getRow());
					Border greenBorder = BorderFactory.createLineBorder(Color.GREEN,3);
					Border redBorder = BorderFactory.createLineBorder(Color.RED,3);
					
					boolean isSelected = board.validMoves().contains(cell);
					boolean isDangerous = board.isDangerousMove(cell, isSelected);
					
					if(isDangerous) {
						cellPanel.setBorder(redBorder);
					}
					else {
						cellPanel.setBorder(greenBorder);
					}
					

				}
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "You can only choose 1 piece each time", "Warning", JOptionPane.WARNING_MESSAGE);
		}
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
