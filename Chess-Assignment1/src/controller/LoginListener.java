package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MenuBar;

public class LoginListener implements ActionListener {

	private MenuBar menubar;
	
	public LoginListener(MenuBar menuBar) {
		this.menubar = menuBar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int option = menubar.displayLoginDialog();
		

	}

}
