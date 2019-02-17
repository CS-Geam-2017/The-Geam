package com.Geam.main;


import java.awt.Canvas;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;


public class Window extends Canvas{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	
	public Window(int width, int height, String title, Geam geam) {
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(10, 10));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.add((Component) geam);
		frame.setVisible(true);
		((Geam) geam).start();
		
		frame.addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent componentEvent) {
		        Geam.WIDTH = frame.getWidth()-15;
		        Geam.HEIGHT = frame.getHeight()-38;
		    }
		});
	}
}
