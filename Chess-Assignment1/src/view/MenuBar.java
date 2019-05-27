package view;

import java.text.NumberFormat;

import javax.swing.AbstractButton;
import javax.swing.JFormattedTextField;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.NumberFormatter;

import controller.MenuListener;
import model.Client;
import model.Client.ClientException;
import model.Player;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar{
	private JTextField value;
	private JTextField name;
	private JPasswordField password;
	private MainFrame frame;
	private Client client;
	
	public MenuBar(MainFrame frame) {
		this.frame = frame;
		this.client = frame.getClient();
		
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		
		// create one item + listener for EACH function
		// separate instantiation not required
		for (MenuFunction function : MenuFunction.values())
		{
			JMenuItem item = new JMenuItem(function.toString());
			item.addActionListener(new MenuListener(frame, this, function));
			file.add(item);
		}
		menuBar.add(file);
		add(file);
	}
	
	
	
	
	
	
	
	
	
	
//	public Player displayRegisterDialog() throws CancelException
//	{
//		name = new JTextField();
//		password = new JPasswordField();
//		password.setEchoChar('*');
//		Object[] message = {"Player Name:", name, "Password:", password};
//		dialog("Register player", message);/*error checking? Force construction for <2 players*/
//		return new Player(name.getText(), password.getPassword().toString());
//	}
	
	// Enum since logging and registering are very common
	// Two differences: function call, and the tile "Register player" (solved by calling function.name())
	public Player playerDialog(MenuFunction function) throws CancelException		
	{
		name = new JTextField();
		password = new JPasswordField();
		password.setEchoChar('*');
		Object[] message = {"Name:", name, "Password:", password};
		dialog(function.toString(), message);					/*remove class variables*/
		
		try
		{
			String name = this.name.getText();
			String pass = new String(password.getPassword());
			Player player = function == MenuFunction.LOGIN ? client.login(name, pass) : client.register(name, pass);
			return player;
		}
		catch (ClientException e)
		{
			JOptionPane.showMessageDialog(frame, e);
			return playerDialog(function);			
		}

	}
	
	public int maxCountDialog(String playerName) throws CancelException
	{		
		return numberDialog(1,500, "Maximum moves", playerName + ", choose the maximum moves possible.");
	}
		
	public int moveRangeDialog() throws CancelException
	{
		return numberDialog(2,10, "Piece Range", "Choose the range that you'd like your pieces to move.");
	}
	
	public int exitDialog()
	{
		return JOptionPane.showConfirmDialog(frame,
		"Are you sure you want to quit?", "Quit?", JOptionPane.YES_NO_OPTION);
	}
	
	
	
	
	
	

	public int numberDialog(int min, int max, String title, String msg) throws CancelException
	{
		SpinnerModel value =   new SpinnerNumberModel(min, min, max, 1);  
	    JSpinner spinner = new JSpinner(value);
	    // can't input text
	    ((DefaultEditor) spinner.getEditor()).getTextField().setEditable(false);
	    spinner.setSize(300,300);  
	    
	    Object[] message = {msg, spinner};
	    dialog(title, message);
	    
	    return (int) spinner.getValue();
	}
	
	// generalized dialog to handle common functions
	public boolean dialog(String title, Object[] message) throws CancelException
	{

		if (JOptionPane.showConfirmDialog(frame, message, title, JOptionPane.OK_CANCEL_OPTION) 
		== JOptionPane.CANCEL_OPTION)		
			throw new CancelException();
		
		try 
		{
			for (Object field : message)
				if (field instanceof JTextField)
					if (((JTextField) field).getText().equals(""))
						throw new NullPointerException("Please fill the missing fields");
			return true;
		}
		catch (NullPointerException e)
		{	//retry
			JOptionPane.showMessageDialog(frame, e.getMessage());
			return dialog(title, message);								
		}
	}
	
	
	
	
	

	public class CancelException extends Exception {}
	
	// Logging and registering are very common eg: calling the same listeners with same parameters
	// the only difference being the model function call
	public enum MenuFunction
	{
		LOGIN, REGISTER, EXIT;
		
		// a readable version
		public String toString()
		{
			switch (this)
			{
				case LOGIN : return "Login player";
				case REGISTER : return "Register player";
				case EXIT : return "Exit Game";
			}
			return null;
		}
	}
	
	
	
}
