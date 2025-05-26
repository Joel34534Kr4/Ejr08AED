package avltree;
//modo extendido para el avl, incluyendo el factor de balance
public class NodeAVL<E>  extends Node<E>{
    protected int bf; // balance factor
    // Constructor que llama al constructor del nodo base y establece bf = 0
    public NodeAVL(E data) {
        super(data);
        bf = 0;
    }
    // Sobrescribe el método toString para mostrar dato y factor de balance
    @Override
    public String toString() {
        return data + " (" + bf + ")";
    }
}

