package avltree;
import java.util.Scanner;
import java.util.stream.IntStream;

public class TestAVL {
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
        Scanner entrada = new Scanner(System.in);
        int opcion;

        String[] opciones = {"Insertar elemento", "Mostrar árbol (jerárquico) con niveles", "Recorrido InOrden","Recorrido PreOrden",
            "Recorrido PostOrden","Recorrido por niveles (BFS)","Salir"
        };

        do {
            System.out.println("\n------ MENÚ DE OPCIONES ------\n");
            IntStream.range(0, opciones.length)
                     .forEach(i -> System.out.println((i + 1) + ". " + opciones[i]));

            System.out.print("Seleccione una opción: ");
            while (!entrada.hasNextInt()) {
                System.out.print("Por favor, ingrese un número válido: ");
                entrada.next();
            }
            opcion = entrada.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese un valor entero a insertar: ");
                    while (!entrada.hasNextInt()) {
                        System.out.print("Valor no válido. Ingrese un número entero: ");
                        entrada.next();
                    }
                    int insertar = entrada.nextInt();
                    tree.insert(insertar);
                    System.out.println("Elemento insertado correctamente.");
                    break;
                case 2:
                    System.out.println("\nÁrbol AVL (jerárquico, nodos derechos arriba):");
                    tree.printStructure();
                    break;
                case 3:
                    System.out.println("\nRecorrido InOrden:");
                    tree.printInOrder();
                    break;
                case 4:
                    System.out.println("\nRecorrido PreOrden:");
                    tree.printPreOrder();
                    break;
                case 5:
                    System.out.println("\nRecorrido PostOrden:");
                    tree.printPostOrder();
                    break;
                case 6:
                    System.out.println("\nRecorrido por niveles (BFS):");
                    tree.printLevelOrder();
                    break;
                case 7:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.err.println("Opción inválida. Intente con un número entre 1 y 7.");
            }
        } while (opcion != 7);

        entrada.close();
    }
}
