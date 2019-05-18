package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.ExitListener;
import controller.LoginListener;
import controller.RegisterListener;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar{
	private JTextField id;
	private JTextField name;
	private JPasswordField password;
	private MainFrame frame;
	
	public MenuBar(MainFrame frame, StatusPanel statusPanel1, StatusPanel statusPanel2) {
		this.frame = frame;
		
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem login = new JMenuItem("Log in Player");
		JMenuItem register = new JMenuItem("Register Player");
		JMenuItem exit = new JMenuItem("Exit Game");
		
		exit.addActionListener(new ExitListener());
		login.addActionListener(new LoginListener(this));
		register.addActionListener(new RegisterListener(this));
		
		menuBar.add(file);
		file.add(login); file.add(register); file.add(exit);
		
		add(file);
	}
	
	public int displayRegisterDialog()
	{
		id = new JTextField();
		name = new JTextField();
		password = new JPasswordField();
		password.setEchoChar('*');
		Object[] message = {"Player ID:", id, "Player Name:", 
				name, "Password:", password};
		int option = JOptionPane.showConfirmDialog(frame, message, "Register Player", JOptionPane.DEFAULT_OPTION);
		return option;
	}
	
	public int displayLoginDialog()
	{
		id = new JTextField();
		password = new JPasswordField();
		password.setEchoChar('*');
		Object[] message = {"Player ID:", id, "Password:", password};
		int option = JOptionPane.showConfirmDialog(frame, message, "Login", JOptionPane.DEFAULT_OPTION);
		return option;
	}
	
	public String getID() {
		return id.getText();
	}
	
	public char[] getPassword() {
		return password.getPassword();
	}
	
	public String getName() {
		return name.getText();
	}
	
}
