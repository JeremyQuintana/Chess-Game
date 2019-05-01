package view;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
	
	public MainFrame() {
		super("Chess Game");
		setBounds(100, 100, 700, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}

