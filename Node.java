package avltre3;


public class Node<E>{ //clase generica que representa un nodo
	protected E data;
	protected Node<E> left, right;

	public Node(E data) {
		this.data = data;
		left = right = null;
	}
	public String toString() {
		return data.toString(); //representacion como string
	}
}
