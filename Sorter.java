import java.util.ArrayList;

public class Sorter {
	
	public Sorter() {
		
	}
	
	
	public static ArrayList<Node> sort(ArrayList<Node> nodes) {
		
		nodes = sortFCost(nodes);
		return nodes;
	}
	
	public static ArrayList<Node> sortFCost(ArrayList<Node> nodes) {
		
		Node temp;
		for (int i = 0; i < nodes.size(); i++) {
			for (int j = 0; j < nodes.size() - i - 1; j++) {
				if (nodes.get(j).getFCost() > nodes.get(j + 1).getFCost()) {
					temp = nodes.get(j);
					nodes.set(j, nodes.get(j + 1));
					nodes.set(j + 1, temp);
					
				}
			}
		}
		nodes = sortEqualF(nodes);
		return nodes;
	}
	
	public static ArrayList<Node> sortEqualF(ArrayList<Node> nodes) {
		
		Node temp;
		for (int i = 0; i < nodes.size(); i++) {
			for (int j = 0; j <nodes.size() - i - 1; j++) {
				if (nodes.get(j).getFCost() == nodes.get(j + 1).getFCost()) {
					if (nodes.get(i).getHCost() > nodes.get(j + 1).getHCost()) {
						temp = nodes.get(j);
						nodes.set(j, nodes.get(j + 1));
						nodes.set(j + 1, temp);
					}
					
				}
			}
		}	
		return nodes;
	}
	
}