
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.Cell;
import model.GameBoard;
import model.Piece;
import model.PieceType;
import model.PlayerType;
import controller.CellAction;
import controller.CellListener;
import model.GameBoard;
import model.Piece;

public class BoardPanel extends JPanel
{
	List<CellPanel> cellList;
	// what the board detects when a cell is clicked
	private CellAction currentAction;
	private GameBoard board;
	private MainFrame frame;
		
	
	public BoardPanel(MainFrame frame) 
	{
		super(new GridLayout(GameBoard.GRID_SIZE,GameBoard.GRID_SIZE));
		
		this.board = frame.getBoard();
		this.frame = frame;
		currentAction = CellAction.SELECT;
		cellList = new ArrayList<>();
		
		for (Cell cell : board.getCells()) 
		{	
			CellPanel cellPanel = new CellPanel(cell);
			cellList.add(cellPanel);
			add(cellPanel);
		}
		setPreferredSize(new Dimension(400,350));
		validate();
	}
	
	// move/merge/split button press
	public void highlightCells(CellAction action)
	{
		for (CellPanel cp : cellList)
			cp.reset();
		
		for (Cell cell : validCells(action))
		{
			boolean isDangerous = board.isDangerousMove(cell, action == CellAction.MOVE);
			getCellPanel(cell).highlight(isDangerous);
		}
	}
	
	
	
	void select(Cell cell, boolean isValid)
	{ 
		if (isValid && currentAction == CellAction.SELECT)
			currentAction = CellAction.MOVE;
		// highlight valid moves, default go to
		highlightCells(currentAction);
	}
	
	// called by ButtonListener
	public void select(CellAction action)
	{
		if (board.isSelected())
			highlightCells(currentAction = action);
	}
	
	void move(Cell cell, boolean isValid)
	{
		// reset before recalling listeners
		currentAction = CellAction.SELECT;
		// for ease of use, clicking on the same piecetype REDISPLAYS the moves
		boolean clickedSameType = !isValid 
		&& cell.getOccupiedType() == board.getSelectedPlayer().getType();
		if (clickedSameType)
			getCellPanel(cell).getMouseListeners()[0].mouseClicked(null);
		
		highlightCells(currentAction);
	}
	
	// merge and split simply call a visual update, as the board is already updated
	void merge(Cell cell, boolean isValid)
	{
		highlightCells(currentAction = CellAction.SELECT);
	}
	
	void split(Cell cell, boolean isValid)
	{
		highlightCells(currentAction = CellAction.SELECT);
	}
	
	void endGame()
	{
		// don't allow player to make more moves
		for (CellPanel panel : cellList)
		{
			for (MouseListener listener : panel.getMouseListeners())
				panel.removeMouseListener(listener);
			if (board.getWinner() != null)
				panel.endGame(board.getWinner().getType());
		}
	}
	
	
	

	
	
	
	
	
	
	public CellAction getCurrentAction() 
	{
		return currentAction;
	}
	
	private List<Cell> validCells(CellAction action)
	{
		switch (action)
		{
			case MOVE : return board.validMoves(); 							
			case MERGE: return board.validMerges(); 							
			case SPLIT: return Collections.singletonList(board.getSelectedPiece().getLocation()); 	
			default:	return new ArrayList<>();
		}
	}
	
	private CellPanel getCellPanel(Cell cell)
	{
		for (CellPanel panel : cellList)
			if (panel.getCell().getRow() == cell.getRow() && 
				panel.getCell().getCol() == cell.getCol())
				return panel;
		return null;
	}
	
//	List<CellPanel> cellList;
//	private boolean selected = false;
//	private GameBoard board;
//	private MainFrame frame;
//	public BoardPanel(GameBoard board, MainFrame frame) {
//	super(new GridLayout(GameBoard.GRID_SIZE,GameBoard.GRID_SIZE));
//	
//	this.board = board;
//	this.frame = frame;
//	
//	cellList = new ArrayList<>();
//	for(int i = 0; i < 36; i++) {
//		CellPanel tilePanel = new CellPanel(this, i,board,frame);
//		cellList.add(tilePanel);
//		add(tilePanel);
//	}
//	setPreferredSize(new Dimension(400,350));
//	putAllPieces(board);
//	revalidate();
//}
//	public void removeDrawing(int i) {
//		CellPanel cell = cellList.get(i);
//		JLabel pieceLabel = new JLabel();
//		pieceLabel.setIcon(null);
//		cell.removeAll();
//		cell.add(pieceLabel);
//	}
//	
//	public void drawThePiece(int i, String pieceType, String worb) {
//		ImageIcon piece = null; 
//		JLabel pieceLabel = new JLabel();
//		pieceLabel.setSize(100,100);
//		
//		CellPanel draw = cellList.get(i);
//		piece = new ImageIcon("images/"+pieceType.toLowerCase()+"_"+worb+".png");
//		Image convertImageIcon = piece.getImage();
//		Image resizeImage = convertImageIcon.getScaledInstance(80, 70, Image.SCALE_SMOOTH);
//		ImageIcon resized = new ImageIcon(resizeImage);
//			
//		pieceLabel.setIcon(resized);
//		pieceLabel.setHorizontalAlignment(JLabel.CENTER);
//		draw.add(pieceLabel);
//	}
//	
//	private void putAllPieces(GameBoard board) {
//		for(int i = 0; i < cellList.size();i++) {
//			List<Piece>pieces;
//			int tileId = i;
//			int y = tileId/6;
//			int x = tileId-6*y;
//			pieces = board.getCell(x,y).getOccupiers();
//			
//			if(pieces.size()>0) {
//				String wob = Character.toString(pieces.get(0).getPlayerType().toString().charAt(0)).toLowerCase();
//				drawThePiece(i,pieces.get(0).toString(),wob);
//			}
//		}
//	}

	public class CellPanel extends JPanel{
		
//		private final int cellId;
//		public CellPanel(BoardPanel boardPanel, final int cellId, GameBoard board,MainFrame frame) {
//			super(new GridLayout());
//			this.cellId = cellId;
//			setPreferredSize(new Dimension(10,10));
//			colortheTile();
//			addMouseListener(new CellListener(boardPanel, board,cellId,this,frame));
//			validate();
//		}
//		
//		private void colortheTile() {
//			if(this.cellId>= 0 && this.cellId <6 || this.cellId >= 12 && this.cellId <18 || this.cellId >= 24 && this.cellId <30){
//				setBackground(this.cellId % 2 == 0 ? new Color(204, 153, 102) : new Color(77, 40, 0));
//			}
//			else {
//				setBackground(this.cellId % 2 != 0 ? new Color(204, 153, 102) : new Color(77, 40, 0));
//			}
//			
//		}
//		public void setSelected(boolean selected) {
//			this.selected = selected;
//		}
//
//		public boolean getSelected() {
//			return selected;
//		}
//		
//		public List<CellPanel> getCellList() {
//			return cellList;
//		}
		
		// accessed by board panel
		private Cell cell;
		private Color color;
		private Color highlightColor;
		private boolean isHighlighted;
		
		private CellPanel(Cell cell) 
		{
			super(new GridLayout());
	
			int row = cell.getRow();
			int col = cell.getCol();
			Color white = new Color(160,160,160);	
			Color black = new Color(50,50,50);	
			// checkered pattern based on row/col number
			color = !(row%2==0 ^ col%2==0) ? white : black;
			isHighlighted = false;
			this.cell = cell;
			
			setPreferredSize(new Dimension(10,10));
			setBackground(color);
			addMouseListener(new CellListener(this, frame));
			reset();
		}
		
		// called by board panel
		private void reset()
		{
			removeAll();
			isHighlighted = false;
			setBackground(color);
			// multiple pieces due to merging
			for (Piece piece : cell.getOccupiers())
				add(getPieceLabel(piece.getType(), piece.getPlayerType()));
			repaint();
			revalidate();
		}
		
		
		
		
		
		
		
		
		
		
		
		// called by board panel to highlight cell if valid for CellAction
		// parameter not a color to encapsulate this attribute
		private void highlight(boolean isDangerous)
		{
			isHighlighted = true;
			int r = color.getRed() 	+ (isDangerous ? 30 : -10);
			int g = color.getGreen()+ (isDangerous ? -20 : 40);
			int b = color.getBlue()	+ (isDangerous ? -20 : 40);
			setBackground(highlightColor = new Color(r,g,b));
		}
		
		// called by listener when highlighted
		// parameter not a color to encapsulate this attribute
		public void hover(boolean onHover)
		{
			int r = highlightColor.getRed()		+ 30;
			int g = highlightColor.getGreen()	+ 30;
			int b = highlightColor.getBlue()	+ 30;
			setBackground(onHover ? new Color(r,g,b) : highlightColor);
		}
		
		// brighten/dampen color based on winner
		public void endGame(PlayerType winner)
		{
			int val = color.getRed() + (winner==PlayerType.WHITE ? 40 : -40);
			setBackground(new Color(val, val, val));
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		// multiple piece images can appear in a cell due to merging
		private JLabel getPieceLabel(PieceType pieceType, PlayerType playerType)
		{
			JLabel pieceLabel = new JLabel();
			pieceLabel.setSize(100,100);
			
			// setup image
			ImageIcon piece = new ImageIcon("images/"+pieceType.name()+"_"+playerType.name().charAt(0)+".png");
			Image convertImageIcon = piece.getImage();
			Image resizeImage = convertImageIcon.getScaledInstance(60, 50, Image.SCALE_SMOOTH);
			ImageIcon resized = new ImageIcon(resizeImage);
			
			pieceLabel.setIcon(resized);
			pieceLabel.setHorizontalAlignment(JLabel.CENTER);
			return pieceLabel;
		}
		public Cell getCell()
		{
			return cell;
		}

		public boolean getIsHighlighted() 
		{
			return isHighlighted;
		}

	}
	
}

