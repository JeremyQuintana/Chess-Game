package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Player;
import view.MainFrame;
import view.MenuBar;
import view.MenuBar.CancelException;
import view.MenuBar.MenuFunction;

// Listener for each menu function: register, login, exit
// handles external game operations
public class MenuListener implements ActionListener {

	private MenuBar menubar;
	private MainFrame frame;
	private MenuFunction function;
	
	public MenuListener(MainFrame frame, MenuBar menuBar, MenuFunction function) {
		this.menubar = menuBar;
		this.frame = frame;
		this.function = function;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		try 
		{
			switch (function)
			{
				case LOGIN : 
					frame.getClient().clearLogged();
					Player p1 = menubar.playerDialog(function);
					int max1  = menubar.maxCountDialog(p1.getName());
					Player p2 = menubar.playerDialog(function);
					int max2  = menubar.maxCountDialog(p2.getName());
					int range = menubar.moveRangeDialog();
					frame.startGame(p1, p2, max1, max2, range);
					break;
					
				case REGISTER : 
					menubar.playerDialog(function);
					break;
					
				case EXIT :
					boolean isQuitting = menubar.exitDialog() == JOptionPane.YES_OPTION;
					if (isQuitting)
						System.exit(0);
					break;
			}
		}
		catch (CancelException ex)
		{
			// do nothing and cancel the operation
		}
	}
	
}
