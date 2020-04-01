import java.text.DecimalFormat;

public class Node {
	
	private int x, y, deltaX, deltaY;
	private double fCost, hCost, gCost;
	
	private boolean isStart, isEnd;
	
	private Node parent;
	
	private DecimalFormat costForm;
	

	public Node() {	
	}
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
		this.fCost = 0;
		this.hCost = 0;
		this.gCost = 0;
		this.isStart = false;
		this.isEnd = false;
		costForm = new DecimalFormat("###.###");
	}
	
	
	//Position within grid (0,0 is top left corner) 
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	
	//Start and end point designation
	public void setStart() {
		isStart = true;
	}
	public void setEnd() {
		isEnd = true;
	}
	public boolean isStart() {
		parent = this;
		return isStart;
	}
	public boolean isEnd() {
		return isEnd;
	}
	
	
	//Parent Node Handling
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public Node getParent() {
		return parent;
	}
	
	
	//Calculating costs
	
	
		//Distance from start (G cost)
	public double calculateGCost(Node from) {
		if (from.getX() == x || from.getY() == y) {
			return (from.getGCost() + 1);
		} else
			return (from.getGCost() + 1.41421);
	}
	public void setGCost(Node from) {
		gCost = calculateGCost(from);
	}
	public double getGCost() {
		return gCost;
	}
	
		//Distance to end (H cost)
		//No need to re calculate H cost ever because it is constant
	public void setHCost(Node endPoint) {
		
		deltaX = Math.abs(endPoint.getX() - x);
		deltaY = Math.abs(endPoint.getY() - y);
		
		hCost = 10 * Math.sqrt((Math.pow(deltaX, 2) + (Math.pow(deltaY, 2))));
		
	}
	public double getHCost() {
		return Double.parseDouble(costForm.format(hCost));
	}
	
	
		//F cost (total cost)
	public void setFCost() {
		fCost = hCost + gCost;
	}
	public double getFCost() {
		return Double.parseDouble(costForm.format(fCost));
	}
	
	
	public String toString() {
		String toString = "";
		toString += x + ", " + y;
		toString += "\nG: " + gCost;
		toString += "\nH: " + hCost;
		toString += "\nF: " + fCost;
		toString += "\nStart: " + isStart;
		toString += "\nEnd: " + isEnd;
		return toString;
	}
}