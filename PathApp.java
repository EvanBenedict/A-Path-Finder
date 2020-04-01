import java.util.ArrayList;

import javax.swing.JOptionPane;

public class PathApp {
	//number of blocks square
	private static int GRID_SIZE = 25;
	//SPEED to draw grid
	//1 Slowest
	private static int SPEED = 1;
	private boolean inputError, pathFound;
	
	private Node current, start, end, reTrace;
	private Node[][] grid;
	private ArrayList<Node> open, closed, obstacles, path;
	
	private Grid gridWindow;
	
	
	public PathApp() {
		//initialize variables
		inputError = false;
		pathFound = false;
		
		//initialize ArrayLists
		open = new ArrayList<Node>();
		closed = new ArrayList<Node>();
		obstacles = new ArrayList<Node>();
		path = new ArrayList<Node>();
		
		//create grid
		grid = new Node[GRID_SIZE][GRID_SIZE];	
	}
	
	public void createGrid() {
		
		for (int row = 0; row < GRID_SIZE; row++) {
			for (int col = 0; col < GRID_SIZE; col++) {
				grid[col][row] = new Node(col, row);
			}
		}
		setStart();
		setEnd();
		current = start;
		current.setHCost(end);
		current.setFCost();
		
		//Set obstacles
		drawObstacles();
		

	}
	
	public void drawObstacles() {
		
		//Draws a line from (3,2) to (3,24)
		for (int i = 2; i < GRID_SIZE; i++) {
			obstacles.add(grid[3][i]);
		}
		
		for (int i = 0; i < GRID_SIZE - 2; i++) {
			obstacles.add(grid[7][i]);
		}
		
		for (int i = 2; i < GRID_SIZE; i++) {
			obstacles.add(grid[17][i]);
		}
		
	}
	
	public void setStart() {
		int x = 0, y = 0;
		do {
			try {
			x = Integer.parseInt(JOptionPane.showInputDialog(null, "Start x:\n Zero Refrence", "Start Position", JOptionPane.PLAIN_MESSAGE));
			inputError = false;
			} catch (NumberFormatException e) {
				inputError = true;
			}
		} while (inputError);
		inputError = false;
		do {
			try {
			y = Integer.parseInt(JOptionPane.showInputDialog(null, "Start y:\n Zero Refrence", "Start Position", JOptionPane.PLAIN_MESSAGE));
			inputError = false;
			} catch (NumberFormatException e) {
				inputError = true;
			}
		} while (inputError);
		grid[x][y].setStart();
		start = grid[x][y];
		open.add(start);
		
	}
	
	public void setEnd() {
		int x = 0, y = 0;
		do {
			try {
			x = Integer.parseInt(JOptionPane.showInputDialog(null, "End x:\n Zero Refrence", "Start Position", JOptionPane.PLAIN_MESSAGE));
			inputError = false;
			} catch (NumberFormatException e) {
				inputError = true;
			}
		} while (inputError);
		inputError = false;
		do {
			try {
			y = Integer.parseInt(JOptionPane.showInputDialog(null, "End y:\n Zero Refrence", "Start Position", JOptionPane.PLAIN_MESSAGE));
			inputError = false;
			} catch (NumberFormatException e) {
				inputError = true;
			}
		} while (inputError);
		if(!grid[x][y].isStart())
			grid[x][y].setEnd();
		else {
			JOptionPane.showMessageDialog(null, "Ending node can not be the starting node", "Warning", JOptionPane.ERROR_MESSAGE, null);
			setEnd();
		}
		end = grid[x][y];
	}
	
	public void findPath() {
		int x, y;
		
		//Main loop
		do {
			//Sort open list by lowest F and H costs
			open = Sorter.sort(open);
			if (open.size() != 0)
				current = open.get(0);
			else {
				JOptionPane.showMessageDialog(null, "No Path Found", "Erroe", JOptionPane.ERROR_MESSAGE, null);
				return;
			}
			open.remove(0);
			closed.add(current);
			
			//exits if a path is found
			if (current.isEnd()) {
				pathFound = true;
				return;
			}
			
			x = current.getX() - 1;
			y = current.getY() - 1;
			
			for (int r = 0; r < 3; r++) {
				for (int c = 0; c < 3; c++)  {
					
					//Gets all neighboring nodes
					if (x + c >= 0 && y + r >= 0 && x + c < GRID_SIZE && y + r < GRID_SIZE) {
						//excludes any nodes outside of the defined grid bounds
						
						if (!closed.contains(grid[x + c][y + r]) && !obstacles.contains(grid[x + c][y + r])) {
							//excludes any known obstacles or closed nodes
							
							//Adds node to open list if not already in or resets G and F costs if lower
							if (!open.contains(grid[x + c][y + r])) {
								grid[x + c][y + r].setParent(current);
								grid[x + c][y + r].setGCost(current);
								grid[x + c][y + r].setHCost(end);
								grid[x + c][y + r].setFCost();
								open.add(grid[x + c][y + r]);
							} else if (grid[x + c][y + r].calculateGCost(current) < grid[x + c][y + r].getGCost()) {
								grid[x + c][y + r].setParent(current);
								grid[x + c][y + r].setGCost(current);
								grid[x + c][y + r].setFCost();
							}	
						}
						gridWindow.updateLists(open, closed, obstacles);
						gridWindow.repaint();
						try {
							Thread.sleep(SPEED);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
			}
			
		} while (!pathFound);
	}
	
	public void printPath() {
		
		reTrace = end;
		
		do {
			path.add(reTrace);
			gridWindow.sendPath(path);
			gridWindow.repaint();
			try {
				Thread.sleep(SPEED);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reTrace = reTrace.getParent();
			
		} while(!reTrace.equals(start));
		
	}
	
	
	public static void main(String[] args) {
		
		PathApp path = new PathApp();
		
		path.createGrid();
		path.gridWindow = new Grid(GRID_SIZE, path.start, path.end);
		
		GUI gui = new GUI(path.gridWindow, GRID_SIZE);
		path.gridWindow.updateBlockSize(gui.getBlockSize());
		
		path.findPath();
		path.printPath();
	}
}