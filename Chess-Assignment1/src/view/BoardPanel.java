
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

import controller.CellListener;
import model.GameBoard;
import model.Piece;

public class BoardPanel extends JPanel{
	List<CellPanel> cellList;
	private boolean selected = false;
	private GameBoard board;
	private MainFrame frame;
		
	public BoardPanel(GameBoard board, MainFrame frame) {
		super(new GridLayout(6,6));
		
		this.board = board;
		this.frame = frame;
		
		cellList = new ArrayList<>();
		for(int i = 0; i < 36; i++) {
			CellPanel tilePanel = new CellPanel(this, i,board,frame);
			cellList.add(tilePanel);
			add(tilePanel);
		}
// 		super(new GridLayout(gameEngine.GRID_SIZE, gameEngine.GRID_SIZE));
// 		engine = gameEngine;
		
// 		for (Cell cell : gameEngine.getCells())
// 			add(new CellPanel(this, cell));
		setPreferredSize(new Dimension(400,350));
<<<<<<< HEAD
		validate();
	}	
=======
		putAllPieces(board);
		revalidate();
	}
>>>>>>> branch 'kane' of https://github.com/s3700178/FFS.git
	
<<<<<<< HEAD
																								/*refactored into: getPieceImage and cell panel constructor
																								 * added functionality: multiple pieces in one cell
																								 * added functionality: dependency on model
																								 * bit harder to read tho*/

	private void putAllPieces() {
		for(int i = 0; i < 6 ; i++) {
			drawThePiece(i,intToPiece(i),"w");
		}
=======
	public void removeDrawing(int i) {
		CellPanel cell = cellList.get(i);
//		CellPanel newCell = new CellPanel(this, i, board, frame);
//		cellList.remove(i);
//		cellList.add(i, newCell);
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
>>>>>>> branch 'kane' of https://github.com/s3700178/FFS.git
		
		}
	
		
	}

	
	public class CellPanel extends JPanel{
		
		private final int cellId;
<<<<<<< HEAD
		public CellPanel(final BoardPanel boardPanel, final int cellId, GameBoard board) {
// 		private final int row;
// 		private final int col;
// 		private final Cell cell;
// 		public CellPanel(BoardPanel boardPanel, Cell cell) 
// 		{
=======
		public CellPanel(BoardPanel boardPanel, final int cellId, GameBoard board,MainFrame frame) {
>>>>>>> branch 'kane' of https://github.com/s3700178/FFS.git
			super(new GridLayout());
			this.row = cell.getRow();
			this.col = cell.getCol();
			this.cell = cell;
			setPreferredSize(new Dimension(10,10));
			colortheTile();
<<<<<<< HEAD
			addMouseListener(new TileListener(boardPanel, board,intToPiece(cellId),cellId));
// 
// 			setBackground(getColor());
// 			// multiple pieces due to merging
// 			for (Piece piece : cell.getOccupiers())
// 				add(getPieceLabel(piece.getType(), piece.getPlayerType()));
			
=======
			addMouseListener(new CellListener(boardPanel, board,cellId,this,frame));
>>>>>>> branch 'kane' of https://github.com/s3700178/FFS.git
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

