package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class SidePanel extends JPanel{
	
	public SidePanel() {
		setLayout(new GridLayout(3,1));
		Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
		setBorder(blackBorder);
		setPreferredSize(new Dimension(170,100));
		setBackground(Color.white);
		
		JLabel score = new JLabel("Score: ");
		JLabel playerList = new JLabel("Player List: ");
		JLabel history = new JLabel("History: ");
		
		score.setBorder(BorderFactory.createLineBorder(Color.blue));
		playerList.setBorder(BorderFactory.createLineBorder(Color.red));
		history.setBorder(BorderFactory.createLineBorder(Color.green));
	
		add(playerList); add(score);  add(history);
		setVisible(true);
		
	}

}
