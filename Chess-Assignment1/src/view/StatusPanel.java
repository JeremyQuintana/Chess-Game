package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import controller.ButtonListener;
import controller.CellAction;
import model.Cell;
import model.GameBoard;
import model.Player;
import model.PlayerType;

public class StatusPanel extends JPanel{
	
	private JLabel score;
	private JLabel playerName;
	
	private Box actionBox;
	private JLabel movesLeft;
	
	private Player player;
	private GameBoard board;
	
	public StatusPanel(MainFrame frame, Player player) 
	{
		this.board = frame.getBoard();
		this.player = player;
		boolean isWhite = player.getType() == PlayerType.WHITE;
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(170,80));
		setBackground(isWhite ? Color.WHITE : Color.BLACK);
		
		Font textFont = new Font("arial", Font.BOLD, 20);
		Color textColor = isWhite ? Color.LIGHT_GRAY : Color.DARK_GRAY;
		score = new JLabel("    SCORE: " + player.getScore());
		playerName = new JLabel("    Player " + player.getName());
		movesLeft = new JLabel("Moves remaining: " + board.movesRemaining());
		score.setFont(textFont);
		score.setForeground(textColor);
		playerName.setFont(textFont);
		playerName.setForeground(textColor);
		movesLeft.setFont(textFont);
		movesLeft.setForeground(textColor);
		
		
		JButton move = new JButton("Move");
		JButton merge = new JButton("merge");
		JButton split = new JButton("split");
		move.addMouseListener(new ButtonListener(CellAction.MOVE, frame));
		merge.addMouseListener(new ButtonListener(CellAction.MERGE, frame));
		split.addMouseListener(new ButtonListener(CellAction.SPLIT, frame));
		
		// to show player details
		Box playerBox = Box.createVerticalBox();
		playerBox.add(Box.createVerticalGlue());
		playerBox.add(score);
		playerBox.add(Box.createVerticalGlue());
		playerBox.add(playerName);
		playerBox.add(Box.createVerticalGlue());
		add(playerBox, BorderLayout.WEST);
		
		// to show cell actions if it's the players turn
		actionBox = Box.createHorizontalBox();
		actionBox.add(Box.createHorizontalGlue());				
		actionBox.add(move);
		actionBox.add(Box.createHorizontalGlue());
		actionBox.add(merge);
		actionBox.add(Box.createHorizontalGlue());
		actionBox.add(split);
		actionBox.add(Box.createHorizontalGlue());
		actionBox.add(movesLeft);
		actionBox.add(Box.createHorizontalGlue());
		actionBox.setVisible(board.getSelectedPlayer().getType() == player.getType()); 
		add(actionBox);
		setVisible(true);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	void switchPlayer()
	{
		score.setText("   SCORE: " + player.getScore());
		movesLeft.setText("Moves remaining: " + board.movesRemaining());
		// warn that less moves remaining
		if (board.movesRemaining() <= 6)
		{
			movesLeft.setText("Only " + board.movesRemaining() + " moves left!");
			movesLeft.setForeground(new Color(180, 40, 40, 200));
		}
		
		// change visibility if not player's turn
		actionBox.setVisible(board.getSelectedPlayer().getType() == player.getType());
		repaint();
		revalidate();
	}
	
	void endGame()
	{
		actionBox.setVisible(false);
		
		// tie
		JLabel winLoseLabel = new JLabel("It's a tie.");
		winLoseLabel.setFont(new Font("arial", Font.BOLD, 50));
		winLoseLabel.setForeground(new Color(150, 150, 150, 150));
		
		// if not a tie
		if (board.getWinner() != null)
		{
			boolean isWinner = board.getWinner().getType() == player.getType();
			winLoseLabel = new JLabel(isWinner ? "You win!" : "You've lost :(");
			
			int r = isWinner ? 60 : 200;
			int g = isWinner ? 120 : 30;
			int b = isWinner ? 200 : 30;
			winLoseLabel.setFont(new Font("arial", Font.BOLD, isWinner ? 75 : 25));
			winLoseLabel.setForeground(new Color(r,g,b, 150));
		}
		
		// special "You win" box
		Box endGameBox = Box.createHorizontalBox();
		endGameBox.add(Box.createHorizontalGlue());				
		endGameBox.add(winLoseLabel);
		endGameBox.add(Box.createHorizontalGlue());
		add(endGameBox);
		repaint();
		revalidate();
	}
}
