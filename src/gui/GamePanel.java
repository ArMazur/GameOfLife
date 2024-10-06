package gui;

import model.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serial;

public class GamePanel extends JPanel {

	@Serial
	private static final long serialVersionUID = 1L;

	private final static int CELLSIZE = 20;

	private final static Color backgroundColor = Color.BLACK;
	private final static Color foregroundColor = Color.GREEN;
	private final static Color gridColor = Color.GRAY;

	private int topBottomMargin;
	private int leftRightMargin;

	private World world;

	public GamePanel() {
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = (e.getY() - topBottomMargin) / CELLSIZE;
				int col = (e.getX() - leftRightMargin) / CELLSIZE;

				if (row >= world.getRows() || col >= world.getColumns()) {
					return;
				}

				System.out.println(world.getRows());

				boolean status = world.getCell(row, col);
				world.setCell(row, col, !status);

				repaint();
			}

		});
	}

	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		int width = getWidth();
		int height = getHeight();

		leftRightMargin = ((width % CELLSIZE) + CELLSIZE) / 2;
		topBottomMargin = ((height % CELLSIZE) + CELLSIZE) / 2;

		int columns = (width - (leftRightMargin * 2)) / CELLSIZE;
		int rows = (height - (topBottomMargin * 2)) / CELLSIZE;

		if (world == null) {
			world = new World(rows, columns);
		} else {
			if (world.getRows() != rows || world.getColumns() != columns) {
				world = new World(rows, columns);
			}
		}

		g2.setColor(backgroundColor);
		g2.fillRect(0, 0, width, height);

		drawGrid(g2, width, height);

		for (int col = 0; col < columns; col++) {
			for (int row = 0; row < rows; row++) {
				boolean status = world.getCell(row, col);
				fillCell(g2, row, col, status);
			}
		}

	}

	// method to make active cell green

	private void fillCell(Graphics2D g2, int row, int col, boolean status) {

		Color color = status ? foregroundColor : backgroundColor;
		g2.setColor(color);

		int x = leftRightMargin + (col * CELLSIZE);
		int y = topBottomMargin + (row * CELLSIZE);

		g2.fillRect(x + 2, y + 2, CELLSIZE - 3, CELLSIZE - 3);

	}

	private void drawGrid(Graphics2D g2, int width, int height) {
		g2.setColor(gridColor);

		for (int x = leftRightMargin; x < width - leftRightMargin; x += CELLSIZE) {
			g2.drawLine(x, topBottomMargin, x, height - topBottomMargin);
		}

		for (int y = topBottomMargin; y < height - topBottomMargin; y += CELLSIZE) {
			g2.drawLine(leftRightMargin, y, width - leftRightMargin, y);
		}

	}

	public void randomize() {
		world.randomize();
		repaint();

	}

	public void clear() {
		world.clear();
		repaint();

	}

	public void next() {
		world.next();
		repaint();

	}

}
