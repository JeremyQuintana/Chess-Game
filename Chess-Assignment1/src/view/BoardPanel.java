
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
import model.Cell;
import model.GameBoard;
import model.Piece;
import model.PieceType;
import model.PlayerType;

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
// 		super(new GridLayout(gameEngine.GRID_SIZE, gameEngine.GRID_SIZE));
// 		engine = gameEngine;
		
// 		for (Cell cell : gameEngine.getCells())
// 			add(new CellPanel(this, cell));
		setPreferredSize(new Dimension(400,350));
		validate();
	}	
	
																								/*refactored into: getPieceImage and cell panel constructor
																								 * added functionality: multiple pieces in one cell
																								 * added functionality: dependency on model
																								 * bit harder to read tho*/

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
// 		private final int row;
// 		private final int col;
// 		private final Cell cell;
// 		public CellPanel(BoardPanel boardPanel, Cell cell) 
// 		{
			super(new GridLayout());
			this.row = cell.getRow();
			this.col = cell.getCol();
			this.cell = cell;
			setPreferredSize(new Dimension(10,10));
			colortheTile();
			addMouseListener(new TileListener(boardPanel, board,intToPiece(cellId),cellId));
// 
// 			setBackground(getColor());
// 			// multiple pieces due to merging
// 			for (Piece piece : cell.getOccupiers())
// 				add(getPieceLabel(piece.getType(), piece.getPlayerType()));
			
			validate();
		}
		
		private Color getColor() {
			
			Color white = new Color(204, 153, 102);
			Color black = new Color(77, 40, 0);
			return !(row%2==0 ^ col%2==0) ? white : black;
		}
		
		// multiple piece images can appear in a cell due to merging
		private JLabel getPieceLabel(PieceType pieceType, PlayerType playerType)
		{
			JLabel pieceLabel = new JLabel();
			pieceLabel.setSize(100,100);
			
			ImageIcon piece = new ImageIcon("images/"+pieceType.name()+"_"+playerType.name().charAt(0)+".png");
			Image convertImageIcon = piece.getImage();
			Image resizeImage = convertImageIcon.getScaledInstance(80, 70, Image.SCALE_SMOOTH);
			ImageIcon resized = new ImageIcon(resizeImage);
			
			pieceLabel.setIcon(resized);
			pieceLabel.setHorizontalAlignment(JLabel.CENTER);
			return pieceLabel;
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

