package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.CellAction;
import model.Cell;
import model.Client;
import model.Client.ClientException;
import model.GameBoard;
import model.Player;

@SuppressWarnings("serial")
public class MainFrame extends JFrame{
	
	private StatusPanel statusPanel1;
	private StatusPanel statusPanel2;
	private BoardPanel boardPanel;
	private GameBoard board;
	private Client client;
	private MenuBar bar;
	
	
	public MainFrame() 
	{
		super("Chess Game");
		client = new Client();
		setLayout(new BorderLayout());
		setJMenuBar(bar = new MenuBar(this));
		
		JPanel empty = new JPanel(new BorderLayout());
		JLabel text = new JLabel("Please login players to start a game", SwingConstants.CENTER);
		text.setFont(new Font("arial", Font.BOLD, 50));
		text.setForeground(Color.LIGHT_GRAY);
		empty.add(text, BorderLayout.CENTER);
		add(empty, BorderLayout.CENTER);
		// force login	

		setBounds(100, 100, 900, 800);					/*resize as a square?*/
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	// called by menu listener to create a board and startGame
	public void startGame(Player p1, Player p2, int max1, int max2, int moveRange)
	{		
		removeAll();
		this.board = new GameBoard(p1, p2, max1, max2, moveRange);
		boardPanel = new BoardPanel(this);
		statusPanel1 = new StatusPanel(this, board.getSelectedPlayer());
		statusPanel2 = new StatusPanel(this, board.getOpposer());
		
		add(boardPanel, BorderLayout.CENTER);						/*game not starting???*/
	    add(statusPanel1,BorderLayout.NORTH);
		add(statusPanel2,BorderLayout.SOUTH);
		repaint();
		revalidate();
	}
	
	public void endGame()
	{
//		boardPanel.remove
	}
	
	
	
	
	
	
	
	
	
	
	
	public boolean select(Cell cell, boolean isValid)
	{
		// help dialog to choose correct piece
		if (cell.getIsOccupied())
			if (cell.getOccupiedType() != board.getSelectedPlayer().getType())
				JOptionPane.showMessageDialog(null, "Choose a "
				+ board.getSelectedPlayer().getType() +" piece");
		
				
				
		if (isValid)
			board.select(cell);
		boardPanel.select(cell, isValid);
		// board panel valid
		//		highlight cell, display moves, MOVE state
		// else 
		//		SELECT state
		return isValid;
	}
	
	public boolean move(Cell destination, boolean isValid) 
	{
		// warning message
		if (board.isDangerousMove(destination, isValid))
			if (JOptionPane.showConfirmDialog(null, "This will result in your piece"
			+ " being in a vulnerable position to be killed.\nMove anyway?") > 0)
				return false;
				
		if (isValid)		
			board.move(destination);
		boardPanel.move(destination, isValid);
		// check if dangerous
		return isValid;
	}
	
	public boolean merge(Cell pieceHolder, boolean isValid)
	{
		if (isValid)
			board.merge(pieceHolder);
		boardPanel.merge(pieceHolder, isValid);
		return isValid;
	}
	
	public boolean split(Cell pieceHolder, boolean isValid)
	{
		if (!board.isValidSplit())
			JOptionPane.showMessageDialog(null, "A single piece can't be split.");
		if (isValid)
			board.split();
		boardPanel.split(pieceHolder, isValid);
		return isValid;
	}
	
	public void switchPlayer()
	{
		board.switchPlayer();
		statusPanel1.switchPlayer();
		statusPanel2.switchPlayer();
	}
	
	
	
	
	
	
	
	
	
	
	
//	private void uninterruptingDialog(String message)
//	{
//		 new Thread(new Runnable()
//		 {
//	        public void run()
//	        {
//	            JOptionPane.showMessageDialog(null, message);
//	        }
//		 }).start();
//	}
	
	public BoardPanel getBoardPanel()
	{
		return boardPanel;
	}
	
	public GameBoard getBoard()
	{
		return board;
	}
	
	public Client getClient()
	{
		return client;
	}

}

