package beak.radius_1967;

import java.util.ArrayList;
import java.util.Scanner;

public class Radius_Chan {
	
	static int treeRadius = 0;
	static Node endPoint = null;
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		
		Node[] nodelist = new Node[n];
		Node root = new Node(0);
		nodelist[0] = root;
		
		for (int i = 0; i < n - 1; i++) {
			int parent = scan.nextInt() - 1;
			int id = scan.nextInt() - 1;
			int len = scan.nextInt();
			
			Node node = new Node(nodelist[parent], id, len);
			nodelist[parent].addChild(node);
			nodelist[id] = node;
		}
		
		scan.close();
		
		dfs(root, 0);
		treeRadius = 0;
		root.resetVisit();
		
		dfs(endPoint, 0);
		
		System.out.println(treeRadius);
	}
	
	static public void dfs(Node node, int length) {

		node.visited = true;
		ArrayList<Node> list = node.getNext();
		
		Node p = node.parent;
		
		if(p != null && !p.visited) {
			dfs(p, length + node.length);
		}
		
		if(list.isEmpty()) {
			if(treeRadius < length) {
				treeRadius = length;
				endPoint = node;
			}
			
			return;
		}
		
		for (Node n : list) {
			dfs(n, length + n.length);
		}
		
	}
}

class Node {

	Node parent;
	int id;
	ArrayList<Node> children = new ArrayList<Node>();
	int length = 0;
	boolean visited = false;

	public Node(Node parent, int id, int length) {
		this.parent = parent;
		this.id = id;
		this.length = length;
	}

	public Node(int id) {
		this.id = id;
	}

	public void addChild(Node child) {
		children.add(child);
	}
	
	public ArrayList<Node> getNext() {
		ArrayList<Node> list = new ArrayList<Node>();
		
		for (Node n : children) {
			if(!n.visited) list.add(n);
		}
		
		return list;
	}
	
	public void resetVisit() {
		visited = false;
		
		for (Node n : children) {
			n.resetVisit();
		}
	}
}
