
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

import controller.TileListener;
import model.GameBoard;

public class BoardPanel extends JPanel{
	final List<CellPanel> cellList;
	private boolean selected = false;
		
	public BoardPanel(GameBoard board) {
		super(new GridLayout(6,6));
		cellList = new ArrayList<>();
		for(int i = 0; i < 36; i++) {
			CellPanel tilePanel = new CellPanel(this, i,board);
			cellList.add(tilePanel);
			add(tilePanel);
		}
		setPreferredSize(new Dimension(400,350));
		putAllPieces();
		validate();
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
	
	private void putAllPieces() {
		for(int i = 0; i < 6 ; i++) {
			drawThePiece(i,intToPiece(i),"w");
		}
		
		for(int j = 30; j < 36 ; j++) {
			drawThePiece(j,intToPiece(j),"b");
		}
		
	}
	
	private String intToPiece(int i) {
		String result = null;
		
		if(i == 0 || i == 5 || i == 30 || i == 35) {result = "rook";}
		else if(i == 1 || i == 4 || i == 31 || i == 34) {result = "bishop";}
		else if(i==2 || i==3 || i==32 || i==33){result = "knight";}
		
		return result;
	}
	
	public class CellPanel extends JPanel{
		
		private final int cellId;
		public CellPanel(final BoardPanel boardPanel, final int cellId, GameBoard board) {
			super(new GridLayout());
			this.cellId = cellId;
			setPreferredSize(new Dimension(10,10));
			colortheTile();
			addMouseListener(new TileListener(boardPanel, board,intToPiece(cellId),cellId,this));
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

