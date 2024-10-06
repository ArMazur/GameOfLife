package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private final GamePanel gamePanel = new GamePanel();
	
	public MainFrame() {
		super("Game of Life");
		setLayout(new BorderLayout());
		add(gamePanel, BorderLayout.CENTER);
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				
				int code = e.getKeyCode();
				
				switch(code) {
				case 32:
					// spacebar
					gamePanel.next();
					break;
				case 8: 
					// backspace
					gamePanel.clear();
					break;
				case 10:
					// enter
					gamePanel.randomize();
					break;
				}
			}
			
		});
		
		setSize(803, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}