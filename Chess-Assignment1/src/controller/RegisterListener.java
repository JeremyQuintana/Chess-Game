package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainFrame;
import view.MenuBar;

public class RegisterListener implements ActionListener {
	
	private MenuBar menubar;
	
	public RegisterListener(MenuBar menubar) {
		this.menubar = menubar;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		int option = menubar.displayRegisterDialog();
	}

}
