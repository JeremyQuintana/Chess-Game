package view;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import model.GameBoard;

public class MainFrame extends JFrame{
	
	public MainFrame(GameBoard board) {
		super("Chess Game");
		setLayout(new BorderLayout());
		JMenuBar menuBar = createMenuBar();
		BoardPanel boardPanel = new BoardPanel(board);
		
		add(boardPanel, BorderLayout.CENTER);
		add(menuBar,BorderLayout.NORTH);
	    add(new StatusPanel(),BorderLayout.NORTH);
		add(new StatusPanel(),BorderLayout.SOUTH);
		
		setBounds(100, 100, 700, 650);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem login = new JMenuItem("Log in Player");
		JMenuItem register = new JMenuItem("Register Player");
		JMenuItem exit = new JMenuItem("Exit Game");
		
		menuBar.add(file);
		file.add(login); file.add(register); file.add(exit);
		return menuBar;
	}
	

}

