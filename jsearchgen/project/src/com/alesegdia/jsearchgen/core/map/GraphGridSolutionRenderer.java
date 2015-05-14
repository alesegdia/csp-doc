package com.alesegdia.jsearchgen.core.map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;

import com.alesegdia.jsearchgen.core.room.RoomInstance;
import com.alesegdia.jsearchgen.mapgen.GraphGridSolution;

public class GraphGridSolutionRenderer extends JComponent {
	
	private GraphGridSolution ggs;
	private TileMap map;
	private Dimension dimension;
	
	public GraphGridSolutionRenderer(GraphGridSolution ggs)
	{
		this.ggs = ggs;
		this.map = ggs.CreateTileMapWithDoors();

		this.dimension = new Dimension(map.cols * 10, map.rows * 10);
		map.Render();

	}
	
    Font f = new Font("Sans", Font.BOLD, 20);
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	     g.setFont(f); 

		for( int i = 0; i < this.map.cols; i++ )
		{
			for( int j = 0; j < this.map.rows; j++ )
			{
				Color c = Color.magenta;
				switch( map.Get(j, i) )
				{
				case TileType.FREE:	c = Color.white; 			break;
				case TileType.DOOR:	c = Color.yellow; 			break;
				case TileType.DOORL: 	c = Color.cyan; 			break;
				case TileType.DOORH: 	c = Color.magenta;			break;
				case TileType.USED: 	c = Color.lightGray; 		break;
				case TileType.WALL: 	c = Color.green; 			break;
				}
				g.setColor(c);
				g.fillRect(i * 10, j * 10, 10, 10);
			}
		}
		for( RoomInstance ri : ggs.added_rooms ) {
		     g.setColor(new Color(255,0,0));
			g.drawString(Integer.toString(ri.id), ri.globalPosition.x * 10 + 60, ri.globalPosition.y * 10+ 60);
		}
	}

	public Dimension getPreferredSize() {
		return this.dimension;
	}

	public Dimension getMinimumSize() {
		return getPreferredSize();
	}
	
	public void Show()
	{
		JFrame mainFrame = new JFrame("Map renderer");
		mainFrame.getContentPane().add(this);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

}