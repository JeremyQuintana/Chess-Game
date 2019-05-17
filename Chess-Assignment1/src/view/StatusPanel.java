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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import controller.CellAction;
import model.Cell;
import model.GameBoard;
import model.Player;
import model.PlayerType;

public class StatusPanel extends JPanel{
	
	private JLabel score;
	private JLabel playerName;
	
	private Player player;
	private GameBoard board;
	
	public StatusPanel(MainFrame frame, Player player) 
	{
		this.board = frame.getBoard();
		this.player = player;
		setLayout(new BorderLayout());
		
//		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setPreferredSize(new Dimension(170,80));
		setBackground(player.getType().getColor());
		
		score = new JLabel("    Score: " + player.getScore());
		playerName = new JLabel("    " + player.getid());
		score.setForeground(player.getType().getOpposer().getColor());
		playerName.setForeground(player.getType().getOpposer().getColor());
//		JLabel history = new JLabel("History: ");
//		
//		score.setBorder(BorderFactory.createLineBorder(Color.blue));
//		playerList.setBorder(BorderFactory.createLineBorder(Color.red));
//		history.setBorder(BorderFactory.createLineBorder(Color.green));
		
		Box playerBox = Box.createVerticalBox();
		playerBox.add(Box.createVerticalGlue());
		playerBox.add(score);
		playerBox.add(Box.createVerticalGlue());
		playerBox.add(playerName);
		playerBox.add(Box.createVerticalGlue());
		add(playerBox, BorderLayout.WEST);
		setVisible(true);
		
	}
	

	
//	private Box box(Component comp)
//	{
//		Box box = Box.createVerticalBox();
//		box.add(Box.createVerticalGlue());
//		box.add(comp);
//		box.add(Box.createVerticalGlue());
//		return box;
//	}
	
	void move(Cell cell, boolean isValid)
	{
		if (isValid)
			score.setText("    Score: " + player.getScore());
	}

}
