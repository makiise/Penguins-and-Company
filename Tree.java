package pgdp.tree;

import java.util.LinkedList;
import java.util.Queue;

// import com.sun.corba.se.impl.orbutil.graph.Node;

public class Tree<T> {

	private Node<T> root;

	public Tree(T data) {
		root = new Node(data);
	}

	public void insert(T value, T parent) {
		if(containsKey(value) || !containsKey(parent)) {
			return;
		}
		findKeyInSubtree(parent, root).insert(new Node<T>(value));
	}

	public void remove(T value) {
		findKeyInSubtree(value, root).remove();
	}

	public T LCA(T a, T b) {
		Node<T> x = findKeyInSubtree(a, root);
		Node<T> y = findKeyInSubtree(b, root);
		while(x != y) {
		    if(x.size() < y.size()) {
		        x = x.getParent();
		    } else {
		        y = y.getParent();
		    }
		}
		return x.getData();
	}

	public int distanceBetweenNodes(T a, T b) {
		Node<T> x = findKeyInSubtree(a, root);
		Node<T> y = findKeyInSubtree(b, root);
		int dist = 0;
		while(x != y) {
		    if(x.size() < y.size()) {
		        x = x.getParent();
		    } else {
		        y = y.getParent();
		    }
		    dist++;
		}
		return dist;
	}

	public Node<T> getRoot() {
		return this.root;
	}

	public boolean containsKey(T key) {
		return (findKeyInSubtree(key, root) != null);
	}
	
	private Node<T> findKeyInSubtree(T key, Node<T> node) {
		if(node.getData() == key) {
			return node;
		}
		for(Node<T> child : node.getChildren()) {
			Node<T> temp = findKeyInSubtree(key, child);
			if(temp != null) {
				return temp;
			}
		}
		return null;
	}

	public void traversal() {
		root.traversal(root);
	}

}
