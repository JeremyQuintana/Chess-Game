package view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.*;
import javax.sound.sampled.DataLine.Info;

import controller.CellAction;
import model.Cell;
import model.GameBoard;
import model.Piece;

// a single audio class to handle all audio
public class SoundPlayer {
	
	private GameBoard board;
	
	public SoundPlayer(GameBoard board)
	{
		this.board = board;
	}
	
	void move(Cell cell, boolean isValid)
	{
		if (isValid)
		{
			playSound(CellAction.MOVE.name());
		
			// means the cell's occupiers are about to be killed
			if (cell.getIsOccupied())
			{
				List<Piece> killers = board.getSelectedPiece().getLocation().getOccupiers();
				// play a sound for every killer
				for (Piece piece : killers)
					playSound(piece.getType().name());
			}
		}
	}
	
	void merge(boolean isValid)
	{
		if (isValid)
			playSound(CellAction.MERGE.name());
	}
	
	void split(boolean isValid)
	{
		if (isValid)
			playSound(CellAction.SPLIT.name());
	}
	
	void endGame()
	{
		playSound("endGame");
	}
	
	
	
	private void playSound(String fileName)
	{
		try
		{
		    AudioInputStream stream = AudioSystem.getAudioInputStream(new File("sounds/" + fileName + ".wav"));
		    AudioFormat format = stream.getFormat();
		    Info info = new Info(Clip.class, format);
		    Clip clip = (Clip) AudioSystem.getLine(info);
		    clip.open(stream);
		    clip.start();
		}
		catch (Exception e) {};
	    
	}

}
