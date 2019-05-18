package view;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.CellAction;
import model.Cell;
import model.GameBoard;

@SuppressWarnings("serial")
public class MainFrame extends JFrame{
	
	private StatusPanel statusPanel1;
	private StatusPanel statusPanel2;
//	private JMenuBar menuBar;
	private BoardPanel boardPanel;
	private GameBoard board;
	
	public MainFrame(GameBoard board) {
		super("Chess Game");
		setLayout(new BorderLayout());
		this.board = board;
//		menuBar = createMenuBar();
		boardPanel = new BoardPanel(this);
		statusPanel1 = new StatusPanel(this, board.getSelectedPlayer());
		statusPanel2 = new StatusPanel(this, board.getOpposer());
		
		setJMenuBar(new MenuBar(this, statusPanel1, statusPanel2));
		add(boardPanel, BorderLayout.CENTER);
//		add(menuBar);
	    add(statusPanel1,BorderLayout.NORTH);
		add(statusPanel2,BorderLayout.SOUTH);
		setBounds(100, 100, 700, 650);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
//	public JMenuBar createMenuBar() {
//		JMenuBar menuBar = new JMenuBar();
//		JMenu file = new JMenu("File");
//		JMenuItem login = new JMenuItem("Log in Player");
//		JMenuItem register = new JMenuItem("Register Player");
//		JMenuItem exit = new JMenuItem("Exit Game");
//		
//		menuBar.add(file);
//		file.add(login); file.add(register); file.add(exit);
//		return menuBar;
//	}
	
	
	
//	public void cellAction(Cell cell)
//	{
//		boolean isValid = false;
//		int row = cell.getRow();
//		int col = cell.getCol();
//		switch (boardPanel.getCurrentAction())
//		{
//			case null : 	isValid = board.select(row,col);	select(cell, isValid);	break;
//			// when a player clicks any tile while selected, deselect
//			case SELECT : 										select(cell, false); 	break;
//			case MOVE : 	isValid = board.move(row,col);		move(cell, isValid);	break;
//			case MERGE : 																break;
//			case SPLIT : 																break;
//		}
//		
//		board.switchPlayer();
//		if (board.isGameOver())
//		{
//			
//		}
//	}
//	public void buttonAction(Cell cell, CellAction action)
//	{
//		// if board in selected state
//		if (boardPanel.getCurrentAction() != null)
//			displayCells(cell, action);
//	}
	
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

}

