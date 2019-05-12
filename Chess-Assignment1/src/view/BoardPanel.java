
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

<<<<<<< HEAD
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
=======
import model.Cell;
import model.GameBoard;
import model.Piece;
import model.PieceType;
import model.PlayerType;

public class BoardPanel extends JPanel
{
	private GameBoard engine;
		
	public BoardPanel(GameBoard gameEngine) {
		super(new GridLayout(gameEngine.GRID_SIZE, gameEngine.GRID_SIZE));
		engine = gameEngine;
		
		for (Cell cell : gameEngine.getCells())
			add(new CellPanel(this, cell));
>>>>>>> 2f147b86f10b8f6179814acd942b4231b8a3c58e
		setPreferredSize(new Dimension(400,350));
		validate();
	}	
	
//	final List<CellPanel> cellList;
//	private GameBoard engine;
//		
//	public BoardPanel(GameBoard gameEngine) {
//		super(new GridLayout(6,6));
//		cellList = new ArrayList<>();
//		engine = gameEngine;
//		
//		for (Cell cell : gameEngine.getCells())
//		{
//			CellPanel tilePanel = new CellPanel(this, cell);
//			cellList.add(tilePanel);
//			add(tilePanel);
//		}
//		setPreferredSize(new Dimension(400,350));
//		putAllPieces();
//		validate();
//	}	
																								/*refactored into: getPieceImage and cell panel constructor
																								 * added functionality: multiple pieces in one cell
																								 * added functionality: dependency on model
																								 * bit harder to read tho*/
	
<<<<<<< HEAD
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
=======
//	private void drawThePiece(int i, String pieceType, String playerType) 
//	{
//		JLabel pieceLabel = new JLabel();
//		pieceLabel.setSize(100,100);
//		
//		ImageIcon piece = new ImageIcon("images/"+pieceType+"_"+playerType+".png");
//		Image convertImageIcon = piece.getImage();
//		Image resizeImage = convertImageIcon.getScaledInstance(80, 70, Image.SCALE_SMOOTH);
//		ImageIcon resized = new ImageIcon(resizeImage);
//			
//		pieceLabel.setIcon(resized);
//		pieceLabel.setHorizontalAlignment(JLabel.CENTER);
//		cellList.get(i).add(pieceLabel);
//	}
//	
																									/*this functionality is already done by the model, so each cell knows to have a piece*/
//	// choose locations for each piece								
//	private void putAllPieces() {
//		for(int i = 0; i < 6 ; i++) {
//			drawThePiece(i,intToPiece(i),"w");
//		}
//		
//		for(int j = 30; j < 36 ; j++) {
//			drawThePiece(j,intToPiece(j),"b");
//		}
//		
//	}
//	
//	private String intToPiece(int i) {
//		String result = null;
//		
//		if(i == 0 || i == 5 || i == 30 || i == 35) {result = "rook";}
//		else if(i == 1 || i == 4 || i == 31 || i == 34) {result = "bishop";}
//		else {result = "knight";}
//		
//		return result;
//	}
>>>>>>> 2f147b86f10b8f6179814acd942b4231b8a3c58e
	
	public class CellPanel extends JPanel{
		
<<<<<<< HEAD
		private final int cellId;
		public CellPanel(final BoardPanel boardPanel, final int cellId, GameBoard board) {
=======
		private final int row;
		private final int col;
		private final Cell cell;
		public CellPanel(BoardPanel boardPanel, Cell cell) 
		{
>>>>>>> 2f147b86f10b8f6179814acd942b4231b8a3c58e
			super(new GridLayout());
			this.row = cell.getRow();
			this.col = cell.getCol();
			this.cell = cell;
			setPreferredSize(new Dimension(10,10));
<<<<<<< HEAD
			colortheTile();
			addMouseListener(new TileListener(boardPanel, board,intToPiece(cellId),cellId));
=======
			setBackground(getColor());
			// multiple pieces due to merging
			for (Piece piece : cell.getOccupiers())
				add(getPieceLabel(piece.getType(), piece.getPlayerType()));
			
>>>>>>> 2f147b86f10b8f6179814acd942b4231b8a3c58e
			validate();
		}
		
		private Color getColor() {
			
			Color white = new Color(204, 153, 102);
			Color black = new Color(77, 40, 0);
			return !(row%2==0 ^ col%2==0) ? white : black;
				
//			if(this.cellId>= 0 && this.cellId <6 || this.cellId >= 12 && this.cellId <18 || this.cellId >= 24 && this.cellId <30){
//				setBackground(this.cellId % 2 == 0 ? new Color(204, 153, 102) : new Color(77, 40, 0));
//			}
//			else {
//				setBackground(this.cellId % 2 != 0 ? new Color(204, 153, 102) : new Color(77, 40, 0));
//			}
//			
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

