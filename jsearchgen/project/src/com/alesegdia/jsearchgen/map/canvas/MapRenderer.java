package com.alesegdia.jsearchgen.map.canvas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;

import com.alesegdia.jsearchgen.map.TileMap;
import com.alesegdia.jsearchgen.map.TileType;

public class MapRenderer extends JComponent {
	
	private TileMap map;
	
	public MapRenderer(TileMap map)
	{
		this.map = map;
	}
	
	private static Color m_tRed = new Color(255, 0, 0, 150);

	private static Color m_tGreen = new Color(0, 255, 0, 150);

	private static Color m_tBlue = new Color(0, 0, 255, 150);

	private static Font monoFont = new Font("Monospaced", Font.BOLD
			| Font.ITALIC, 36);

	private static Font sanSerifFont = new Font("SanSerif", Font.PLAIN, 12);

	private static Font serifFont = new Font("Serif", Font.BOLD, 24);

	private static ImageIcon java2sLogo = new ImageIcon("java2s.gif");

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for( int i = 0; i < this.map.cols; i++ )
		{
			for( int j = 0; j < this.map.rows; j++ )
			{
				Color c = Color.magenta;
				switch( map.Get(j, i) )
				{
				case TileType.FREE: c = Color.white; break;
				case TileType.DOOR: c = Color.yellow; break;
				case TileType.USED: c = Color.lightGray; break;
				case TileType.WALL: c = Color.green; break;
				}
				g.setColor(c);
				g.fillRect(i * 10, j * 10, 10, 10);
			}
		}

	}

	public Dimension getPreferredSize() {
		return new Dimension(400, 400);
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