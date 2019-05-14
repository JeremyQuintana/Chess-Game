
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.CellListener;
import model.GameBoard;
import model.Piece;

public class BoardPanel extends JPanel{
	List<CellPanel> cellList;
	private boolean selected = false;
		
	public BoardPanel(GameBoard board, MainFrame frame) {
		super(new GridLayout(6,6));
		cellList = new ArrayList<>();
		for(int i = 0; i < 36; i++) {
			CellPanel tilePanel = new CellPanel(this, i,board,frame);
			cellList.add(tilePanel);
			add(tilePanel);
		}
		setPreferredSize(new Dimension(400,350));
		putAllPieces(board);
		revalidate();
	}
	
	public void removeDrawing(int i) {
		CellPanel cell = cellList.get(i);
		JLabel pieceLabel = new JLabel();
		pieceLabel.setIcon(null);
		cell.removeAll();
		cell.add(pieceLabel);
	}
	
	public void drawThePiece(int i, String pieceType, String worb) {
		ImageIcon piece = null; 
		JLabel pieceLabel = new JLabel();
		pieceLabel.setSize(100,100);
		
		CellPanel draw = cellList.get(i);
		piece = new ImageIcon("images/"+pieceType.toLowerCase()+"_"+worb+".png");
		Image convertImageIcon = piece.getImage();
		Image resizeImage = convertImageIcon.getScaledInstance(80, 70, Image.SCALE_SMOOTH);
		ImageIcon resized = new ImageIcon(resizeImage);
			
		pieceLabel.setIcon(resized);
		pieceLabel.setHorizontalAlignment(JLabel.CENTER);
		draw.add(pieceLabel);
	}
	
	private void putAllPieces(GameBoard board) {
		for(int i = 0; i < cellList.size();i++) {
			List<Piece>pieces;
			int tileId = i;
			int y = tileId/6;
			int x = tileId-6*y;
			pieces = board.getCell(x,y).getOccupiers();
			
			if(pieces.size()>0) {
				String wob = Character.toString(pieces.get(0).getPlayerType().toString().charAt(0)).toLowerCase();
				drawThePiece(i,pieces.get(0).toString(),wob);
			}
		
		}
	
		
	}
	
	public class CellPanel extends JPanel{
		
		private final int cellId;
		public CellPanel(BoardPanel boardPanel, final int cellId, GameBoard board,MainFrame frame) {
			super(new GridLayout());
			this.cellId = cellId;
			setPreferredSize(new Dimension(10,10));
			colortheTile();
			addMouseListener(new CellListener(boardPanel, board,cellId,this,frame));
			validate();
		}
		
		private void colortheTile() {
			if(this.cellId>= 0 && this.cellId <6 || this.cellId >= 12 && this.cellId <18 || this.cellId >= 24 && this.cellId <30){
				setBackground(this.cellId % 2 == 0 ? new Color(204, 153, 102) : new Color(77, 40, 0));
			}
			else {
				setBackground(this.cellId % 2 != 0 ? new Color(204, 153, 102) : new Color(77, 40, 0));
			}
			
		}
		
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean getSelected() {
		return selected;
	}
	
	public List getCellList() {
		return cellList;
	}
}

