package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class BoardPanel extends JPanel{
	final List<TilePanel> tilesList;
		
	public BoardPanel() {
		super(new GridLayout(6,6));
		tilesList = new ArrayList<>();
		for(int i = 0; i < 36; i++) {
			TilePanel tilePanel = new TilePanel(this, i);
			tilesList.add(tilePanel);
			add(tilePanel);
		}
		setPreferredSize(new Dimension(400,350));
		validate();
	}
	
	private class TilePanel extends JPanel{
		
		private final int tileId;
		public TilePanel(final BoardPanel boardPanel, final int tileId) {
			super(new GridLayout());
			this.tileId = tileId;
			setPreferredSize(new Dimension(10,10));
			colortheTile();
			validate();
		}
		
		private void colortheTile() {
			if(this.tileId>= 0 && this.tileId <6 || this.tileId >= 12 && this.tileId <18 || this.tileId >= 24 && this.tileId <30){
				setBackground(this.tileId % 2 == 0 ? new Color(255, 230, 204) : new Color(77, 40, 0));
			}
			else {
				setBackground(this.tileId % 2 != 0 ? new Color(255, 230, 204) : new Color(77, 40, 0));
			}
			
		}

		
	}

}


