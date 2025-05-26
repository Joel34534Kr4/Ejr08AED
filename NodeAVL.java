package avltree;

public class NodeAVL<E>  extends Node<E>{
    protected int bf; // balance factor

    public NodeAVL(E data) {
        super(data);
        bf = 0;
    }

    @Override
    public String toString() {
        return data + " (" + bf + ")";
    }
}
