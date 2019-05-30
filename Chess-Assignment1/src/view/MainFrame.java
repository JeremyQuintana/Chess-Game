package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Cell;
import model.Client;
import model.GameBoard;
import model.Player;

@SuppressWarnings("serial")
public class MainFrame extends JFrame{
	
	private StatusPanel statusPanel1;
	private StatusPanel statusPanel2;
	private BoardPanel boardPanel;
	private GameBoard board;
	private Client client;
	private SoundPlayer sound;
	// the temp panel before a game
	private JPanel empty;
	
	
	public MainFrame() 
	{
		super("Chess Game");
		client = new Client();
		setLayout(new BorderLayout());
		setJMenuBar(new MenuBar(this));
		
		empty = new JPanel(new BorderLayout());
		JLabel text = new JLabel("Please login players to start a game", SwingConstants.CENTER);
		text.setFont(new Font("arial", Font.BOLD, 50));
		text.setForeground(Color.LIGHT_GRAY);
		empty.add(text, BorderLayout.CENTER);
		add(empty, BorderLayout.CENTER);

		setBounds(50, 50, 1200, 1000);					
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	// called by menu listener to create a board and startGame
	public void startGame(Player p1, Player p2, int max1, int max2, int moveRange)
	{
		if (empty != null)			remove(empty);
		if (statusPanel1 != null)	remove(statusPanel1);
		if (statusPanel2 != null)	remove(statusPanel2);
		if (boardPanel != null)		remove(boardPanel);
		
		board = new GameBoard(p1, p2, max1, max2, moveRange);
		sound = new SoundPlayer(board);
		boardPanel = new BoardPanel(this);
		statusPanel1 = new StatusPanel(this, board.getSelectedPlayer());
		statusPanel2 = new StatusPanel(this, board.getOpposer());
		
		add(boardPanel, BorderLayout.CENTER);						/*game not starting???*/
	    add(statusPanel1,BorderLayout.NORTH);
		add(statusPanel2,BorderLayout.SOUTH);
		repaint();
		revalidate();
	}
	
	// called by menu listener to undo logging process
	public void loginFail()
	{
		client.clearLogged();
	}
	
	public void endGame()
	{
		sound.endGame();
		boardPanel.endGame();
		statusPanel1.endGame();
		statusPanel2.endGame();
		
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
		return isValid;
	}
	
	public boolean move(Cell destination, boolean isValid) 
	{
		// warning message
		if (board.isDangerousMove(destination, isValid))
			if (JOptionPane.showConfirmDialog(null, "This will result in your piece"
			+ " being in a vulnerable position to be killed.\nMove anyway?") > 0)
				return false;
		
		// place before because it needs to predict if playing a kill sound
		sound.move(destination, isValid);		
		if (isValid)		
			board.move(destination);
		boardPanel.move(destination, isValid);
		// check if dangerous
		return isValid;
	}
	
	public boolean merge(Cell pieceHolder, boolean isValid)
	{
		sound.merge(isValid);
		if (isValid)
			board.merge(pieceHolder);
		boardPanel.merge(pieceHolder, isValid);
		return isValid;
	}
	
	public boolean split(Cell pieceHolder, boolean isValid)
	{
		if (!board.isValidSplit())
			JOptionPane.showMessageDialog(null, "A single piece can't be split.");
		
		sound.split(isValid);
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

