package view;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class MainFrame extends JFrame{
	
	public MainFrame() {
		super("Chess Game");
		setLayout(new BorderLayout());
		JMenuBar menuBar = createMenuBar();
		BoardPanel boardPanel = new BoardPanel();
		add(boardPanel, BorderLayout.CENTER);
		add(new SidePanel(),BorderLayout.WEST);
		add(menuBar,BorderLayout.NORTH); 
		setBounds(100, 100, 700, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		menuBar.add(file);
		return menuBar;
	}
	

}

