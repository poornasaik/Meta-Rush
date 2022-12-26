package com.metarush.game;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas {
	private static final long serialVersionUID = -292542628812214152L;
	
	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);
		Dimension windowSize = new Dimension(width,height);
		frame.setPreferredSize(windowSize);
		frame.setMaximumSize(windowSize);
		frame.setMinimumSize(windowSize);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
		
	}

}
