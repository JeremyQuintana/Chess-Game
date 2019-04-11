package model;

import java.util.HashMap;
import java.util.LinkedList;

public class CellList extends LinkedList<Cell>{
	
	
	Cell get(int row, int col) throws NullPointerException
	{
		for (Cell cell : this)
			if (cell.getRow() == row && cell.getCol() == col)
				return cell;
		return null;
//		throw new NullPointerException("invalid row or column");
	}
	
	

}
