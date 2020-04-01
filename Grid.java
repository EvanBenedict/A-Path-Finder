import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

public class Grid extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private int gridSize, blockSize;
	
	private Node start, end;
	private ArrayList<Node> open, closed, obstacles, path;
	
	public Grid(int gridSize, Node start, Node end) {
		blockSize = 0;
		this.gridSize = gridSize;
		
		this.start = start;
		this.end = end;
		open = new ArrayList<Node>();
		closed = new ArrayList<Node>();
		obstacles = new ArrayList<Node>();
		path = new ArrayList<Node>();
	}
		
	public void paint(Graphics g) {
		Node element;
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getHeight(), this.getWidth());
		g.setColor(Color.white);
			
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
							
				if (start.getX() == j && start.getY() == i) 
					g.setColor(Color.blue);
				else if (end.getX() == j && end.getY() == i)
					g.setColor(Color.blue);
				else {
					g.setColor(Color.white);
					
					for (Iterator<Node> it = open.iterator(); it.hasNext(); ) {
						element = it.next();
						if (element.getX() == j && element.getY() == i)
								g.setColor(Color.green);
					}
					for (Iterator<Node> it = closed.iterator(); it.hasNext();) {
						element = it.next();
						if (element.getX() == j && element.getY() == i)
								g.setColor(Color.red);
					}
					for (Iterator<Node> it = obstacles.iterator(); it.hasNext();) {
						element = it.next();
						if (element.getX() == j && element.getY() == i)
							g.setColor(Color.black);
					}
					for (int k = 0; k < path.size(); k++) {
						element = path.get(k);
						if (element.getX() == j && element.getY() == i) {
							g.setColor(Color.blue);
							
						}
							
					}
				}
					
				g.fillRect((j * blockSize) + 1, (i * blockSize) + 1, blockSize - 1, blockSize - 1);
				
			}
		}
	}
	
	public void updateBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}
	
	public void updateLists(ArrayList<Node> open, ArrayList<Node> closed, ArrayList<Node> obstacles) {
		this.open = open;
		this.closed = closed;
		this.obstacles = obstacles;
	}
	
	public void sendPath(ArrayList<Node> path) {
		this.path = path;
	}
	
	
}

