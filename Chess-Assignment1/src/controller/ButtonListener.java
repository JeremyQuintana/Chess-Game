package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import view.MainFrame;

// a listener for each move/merge/split button
// a button defined by its CellAction
// eg: ButtonListener(CellAction.MOVE, frame) is a move button
public class ButtonListener extends MouseAdapter
{
	
	private CellAction action;
	private MainFrame frame;
		
	public ButtonListener(CellAction action, MainFrame frame) 
	{
		this.action = action;
		this.frame = frame;
	}
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		frame.getBoardPanel().select(action);
	}
}
