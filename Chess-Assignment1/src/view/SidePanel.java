package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class SidePanel extends JPanel{
	
	public SidePanel() {
		setLayout(new GridLayout(5,1));
		Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
		setBorder(blackBorder);
		setBackground(Color.WHITE);
		setVisible(true);
		
	}

}
