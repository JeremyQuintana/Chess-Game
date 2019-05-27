package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

import model.Cell;
import model.GameBoard;
import model.Piece;
import view.BoardPanel;
import view.MainFrame;

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
