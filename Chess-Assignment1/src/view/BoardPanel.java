
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

public class BoardPanel extends JPanel
{
	private GameBoard engine;
		
	public BoardPanel(GameBoard gameEngine) {
		super(new GridLayout(gameEngine.GRID_SIZE, gameEngine.GRID_SIZE));
		engine = gameEngine;
		
		for (Cell cell : gameEngine.getCells())
			add(new CellPanel(this, cell));
		setPreferredSize(new Dimension(400,350));
		validate();
	}	
	
																								/*refactored into: getPieceImage and cell panel constructor
																								 * added functionality: multiple pieces in one cell
																								 * added functionality: dependency on model
																								 * bit harder to read tho*/

	
	private class CellPanel extends JPanel{
		
		private final int row;
		private final int col;
		private final Cell cell;
		public CellPanel(BoardPanel boardPanel, Cell cell) 
		{
			super(new GridLayout());
			this.row = cell.getRow();
			this.col = cell.getCol();
			this.cell = cell;
			setPreferredSize(new Dimension(10,10));
			setBackground(getColor());
			// multiple pieces due to merging
			for (Piece piece : cell.getOccupiers())
				add(getPieceLabel(piece.getType(), piece.getPlayerType()));
			
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

}

