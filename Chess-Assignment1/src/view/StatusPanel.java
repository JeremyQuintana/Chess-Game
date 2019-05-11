package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class StatusPanel extends JPanel{
	
	public StatusPanel() {
		setLayout(new GridLayout(1,3));
		Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
		setBorder(blackBorder);
		setPreferredSize(new Dimension(170,80));
		setBackground(Color.white);
		
		JLabel score = new JLabel("Score: ");
		JLabel playerList = new JLabel("Player Name: ");
		JLabel history = new JLabel("History: ");
		
		score.setBorder(BorderFactory.createLineBorder(Color.blue));
		playerList.setBorder(BorderFactory.createLineBorder(Color.red));
		history.setBorder(BorderFactory.createLineBorder(Color.green));
	
		add(playerList); add(score);  add(history);
		setVisible(true);
		
	}

}
