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

public class TileListener implements MouseListener{
	private GameBoard board;
	private String piece;
	private int tileId;
	private BoardPanel panel;
	private CellPanel thisCell;
	private List<CellPanel> cellList;
	public TileListener(final BoardPanel panel, GameBoard board,String piece, int tileId, CellPanel thisCell) {
		this.panel = panel;
		this.board = board;
		this.piece = piece;
		this.tileId = tileId;
		this.thisCell=thisCell;
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
			
			board.move(y,x);
			String wob = Character.toString(board.getSelectedPlayer().toString().charAt(0)).toLowerCase();
			panel.removeDrawing(sourceTileId);
			panel.drawThePiece(tileId, board.getSelectedPiece().toString(), wob);
			panel.setSelected(false);
			for(CellPanel cell: cellList) {
				cell.setBorder(null);
			}
			panel.revalidate();
		}
		else if(!panel.getSelected()) {
			String oneOrTwo = null;
			if(tileId<=6 || tileId>=30) {
				if(tileId<=2||tileId<=32&&tileId>6) {oneOrTwo = "1";} else{oneOrTwo ="2";}
				board.select(piece.charAt(0)+oneOrTwo);
				List<Cell> validCell = board.validMoves(board.getSelectedPiece());
				panel.setSelected(true);
				for(Cell cell: validCell) {
					CellPanel cellPanel = cellList.get((cell.getCol()*6)+cell.getRow());
					Border greenBorder = BorderFactory.createLineBorder(Color.GREEN,3);
					cellPanel.setBorder(greenBorder);
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
