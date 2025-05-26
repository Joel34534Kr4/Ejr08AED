package avltree;
import java.util.LinkedList;
import java.util.Queue;
public class AVLTree<E extends Comparable<E>>{
    private NodeAVL<E> root;

    public AVLTree() {
        root = null;
    }

    public void insert(E value) {
        root = insert(root, value);
    }

    private NodeAVL<E> insert(NodeAVL<E> node, E value) {
        if (node == null) return new NodeAVL<>(value);
        int cmp = value.compareTo(node.data);
        if (cmp < 0) {
            node.left = insert((NodeAVL<E>) node.left, value);
            node = updateBalanceAfterLeftInsert(node);
        } else if (cmp > 0) {
            node.right = insert((NodeAVL<E>) node.right, value);
            node = updateBalanceAfterRightInsert(node);
        }
        return node;
    }

    private NodeAVL<E> updateBalanceAfterLeftInsert(NodeAVL<E> node) {
        node.bf--;
        if (node.bf == -2) {
            if (((NodeAVL<E>) node.left).bf <= 0) {
                return rotateSR(node); // RSR
            } else {
                node.left = rotateSL((NodeAVL<E>) node.left);
                return rotateSR(node); // RDR
            }
        }
        return node;
    }

    private NodeAVL<E> updateBalanceAfterRightInsert(NodeAVL<E> node) {
        node.bf++;
        if (node.bf == 2) {
            if (((NodeAVL<E>) node.right).bf >= 0) {
                return rotateSL(node); // RSL
            } else {
                node.right = rotateSR((NodeAVL<E>) node.right);
                return rotateSL(node); // RDL
            }
        }
        return node;
    }

    private NodeAVL<E> rotateSL(NodeAVL<E> node) {
        NodeAVL<E> newRoot = (NodeAVL<E>) node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        updateBF(node);
        updateBF(newRoot);
        return newRoot;
    }

    private NodeAVL<E> rotateSR(NodeAVL<E> node) {
        NodeAVL<E> newRoot = (NodeAVL<E>) node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        updateBF(node);
        updateBF(newRoot);
        return newRoot;
    }

    private void updateBF(NodeAVL<E> node) {
        node.bf = height((NodeAVL<E>) node.right) - height((NodeAVL<E>) node.left);
    }

    private int height(NodeAVL<E> node) {
        if (node == null) return -1;
        return 1 + Math.max(height((NodeAVL<E>) node.left), height((NodeAVL<E>) node.right));
    }

    public void printPreOrder() {
        preOrder(root);
        System.out.println();
    }

    public void printInOrder() {
        inOrder(root);
        System.out.println();
    }

    public void printPostOrder() {
        postOrder(root);
        System.out.println();
    }

    private void preOrder(Node<E> node) {
        if (node != null) {
            System.out.print(node + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    private void inOrder(Node<E> node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node + " ");
            inOrder(node.right);
        }
    }

    private void postOrder(Node<E> node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node + " ");
        }
    }

    public void printStructure() {
        printStructure(root, 0);
    }

    private void printStructure(Node<E> node, int level) {
        if (node != null) {
            printStructure(node.right, level + 1);
            System.out.println("    ".repeat(level) + node);
            printStructure(node.left, level + 1);
        }
    }

    public void printLevelOrder() {
        if (root == null) return;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<E> current = queue.poll();
            System.out.print(current + " ");
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
        System.out.println();
    }
}
