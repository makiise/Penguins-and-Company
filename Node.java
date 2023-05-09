package pgdp.tree;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {

	private List<Node<T>> children;
	private Node<T> parent;
	private T data;

	public Node(T data) {
		this.data = data;
		children = new ArrayList<>();
	}

	public void insert(Node<T> value) {
		children.add(value);
		value.setParent(this);
	}

	public boolean isLeaf() {
		return children.isEmpty();
	}

	public int size() {
		int res = 1;
		for(Node<T> child : children) {
			res += child.size();
		}
		return res;
	}

	public void remove() {
		parent.getChildren().remove(this);
		for (Node<T> child : children) {
			parent.insert(child);
		}
	}

	public List<Node<T>> getChildren() {
		return children;
	}

	public Node<T> getParent() {
		return parent;
	}
	
	public void setParent(Node<T> node) {
		parent = node;
	}

	public T getData() {
		return data;
	}

	public void traversal(Node<T> root) {
		System.out.println(root.getData());
		for(Node<T> child : children) {
			child.traversal(root);
		}
	}

}
