package avltree;

public class Node<E>{
	protected E data;
	protected Node<E> left, right;

	public Node(E data) {
		this.data = data;
		left = right = null;
	}
	public String toString() {
		return data.toString();
	}
}
