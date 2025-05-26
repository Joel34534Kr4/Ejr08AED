package avltree;
import java.util.LinkedList;
import java.util.Queue;
public class AVLTree<E extends Comparable<E>>{  // clase principal del arbol AVL generico
    private NodeAVL<E> root; //inicializamos raiz del arbol

    public AVLTree() {
        root = null; // se crea un arbol vacio
    }

    public void insert(E value) { // para insertar elemenetos 
        root = insert(root, value);
    }
    // Método recursivo para insertar un valor en el subárbol dado
    private NodeAVL<E> insert(NodeAVL<E> node, E value) {
        if (node == null) return new NodeAVL<>(value);
        // Si el nodo actual es nulo, crear un nuevo nodo AVL con el valor
        int cmp = value.compareTo(node.data);
        if (cmp < 0) {         
        	// Comparar el valor a insertar con el dato del nodo actual
            node.left = insert((NodeAVL<E>) node.left, value);
            // Actualizar balance después de insertar por la izquierda
            node = updateBalanceAfterLeftInsert(node);
        } else if (cmp > 0) {
            // Si el valor es mayor, insertar en el subárbol derecho
            node.right = insert((NodeAVL<E>) node.right, value);
            // Actualizar balance después de insertar por la derecha
            node = updateBalanceAfterRightInsert(node);
        }
        // Si el valor es igual no se inserta (no se permiten duplicados)
        return node;// Retorna el nodo actualizado o balanceado
    }
    // Actualiza factor de balance y realiza rotaciones si es necesario tras inserción a la izquierda
    private NodeAVL<E> updateBalanceAfterLeftInsert(NodeAVL<E> node) {
        node.bf--;// Disminuye factor de balance porque el subárbol izquierdo creció
        if (node.bf == -2) {// Caso desbalance izquierdo
            if (((NodeAVL<E>) node.left).bf <= 0) {
                return rotateSR(node); // RSR
            } else {
                node.left = rotateSL((NodeAVL<E>) node.left);
                return rotateSR(node); // RDR
            }
        }
        return node;
        // Retorna nodo si no hay desbalance
    }
    // Actualiza factor de balance y realiza rotaciones si es necesario tras inserción a la derecha
    private NodeAVL<E> updateBalanceAfterRightInsert(NodeAVL<E> node) {
        node.bf++;// Aumenta factor de balance porque el subárbol derecho creció
        if (node.bf == 2) { // Caso desbalance derecho
            if (((NodeAVL<E>) node.right).bf >= 0) {
                return rotateSL(node); // RSL
            } else {
                node.right = rotateSR((NodeAVL<E>) node.right);
                return rotateSL(node); // RDL
            }
        }
        return node;// Retorna nodo si no hay desbalance
    }
    // Rotación simple a la izquierda (RSL)
    private NodeAVL<E> rotateSL(NodeAVL<E> node) {
        NodeAVL<E> newRoot = (NodeAVL<E>) node.right;// nuevo nodo raíz será el hijo derecho
        node.right = newRoot.left;// el hijo izquierdo del nuevo raíz pasa a ser hijo derecho del nodo original
        newRoot.left = node; // el nodo original pasa a ser hijo izquierdo del nuevo raíz
        updateBF(node);
        // Actualizar factores de balance de ambos nodos afectados
        updateBF(newRoot);
        return newRoot; // Retorna nuevo nodo raíz de la subárbol rotado
    }
    // Rotación simple a la derecha (RSR)
    private NodeAVL<E> rotateSR(NodeAVL<E> node) { // nuevo nodo raíz será el hijo izquierdo
        NodeAVL<E> newRoot = (NodeAVL<E>) node.left;// el hijo derecho del nuevo raíz pasa a ser hijo izquierdo del nodo original
        node.left = newRoot.right;// el nodo original pasa a ser hijo derecho del nuevo raíz
        newRoot.right = node;
     // Actualizar factores de balance
        updateBF(node);
        updateBF(newRoot);
        return newRoot; // Retorna nuevo nodo raíz tras rotación
    }
    // Actualiza el factor de balance de un nodo
    private void updateBF(NodeAVL<E> node) {
        // bf = altura subárbol derecho - altura subárbol izquierdo
        node.bf = height((NodeAVL<E>) node.right) - height((NodeAVL<E>) node.left);
    }
    // Calcula la altura del nodo (0 para hoja, -1 para nodo nulo)
    private int height(NodeAVL<E> node) {
        if (node == null) return -1;
        return 1 + Math.max(height((NodeAVL<E>) node.left), height((NodeAVL<E>) node.right));
    }

    //recorridos 
    public void printPreOrder() { // raiz - izq- der
        preOrder(root);
        System.out.println();
    }

    public void printInOrder() { // izq - raiz - der
        inOrder(root);
        System.out.println();
    }

    public void printPostOrder() { // izq - der -raiz
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
    // Imprime la estructura del árbol con niveles y nodos derechos arriba
    public void printStructure() {
        printStructure(root, 0);
    }
    // Método recursivo para imprimir árbol jerárquico con indentación por nivel
    private void printStructure(Node<E> node, int level) {
        if (node != null) {
            printStructure(node.right, level + 1); // Primero imprime subárbol derecho (arriba)
            System.out.println("    ".repeat(level) + node);// Imprime el nodo con indentación
            printStructure(node.left, level + 1);// Luego imprime subárbol izquierdo
        }
    }    
    // Imprime el recorrido por niveles (BFS) usando una cola
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
