import java.util.Scanner;
import java.util.stream.IntStream;
//Clase para probar el árbol AVL con menú interactivo en consola
public class TestAVL {
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>(); // Crear árbol AVL para Integer
        Scanner entrada = new Scanner(System.in);
        int opcion;
        // Opciones del menú en arreglo para mostrar al usuario
        String[] opciones = {"Insertar elemento", "Mostrar árbol (jerárquico) con niveles", "Recorrido InOrden","Recorrido PreOrden",
            "Recorrido PostOrden","Recorrido por niveles (BFS)","Salir"
        };

        do {
            System.out.println("\n------ MENÚ DE OPCIONES ------\n");
            //recorre el arreglo de opciones
            IntStream.range(0, opciones.length).forEach(i -> System.out.println((i + 1) + ". " + opciones[i]));

            System.out.print("Seleccione una opción: ");
            // Validar que el usuario ingrese un número entero
            while (!entrada.hasNextInt()) {
                System.out.print("Por favor, ingrese un número válido: ");
                entrada.next();
            }
            opcion = entrada.nextInt();
            // Evaluar la opción escogida con switch
            switch (opcion) {
                case 1:
                    // Insertar un valor entero en el árbol AVL
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
                    // Mostrar estructura jerárquica del árbol
                    System.out.println("\nÁrbol AVL (jerárquico, nodos derechos arriba):");
                    tree.printStructure();
                    break;
                case 3:
                    // Mostrar recorrido InOrden
                    System.out.println("\nRecorrido InOrden:");
                    tree.printInOrder();
                    break;
                case 4:
                    // Mostrar recorrido PreOrden
                    System.out.println("\nRecorrido PreOrden:");
                    tree.printPreOrder();
                    break;
                case 5:
                    // Mostrar recorrido PostOrden
                    System.out.println("\nRecorrido PostOrden:");
                    tree.printPostOrder();
                    break;
                case 6:
                    // Mostrar recorrido por niveles (BFS)
                    System.out.println("\nRecorrido por niveles (BFS):");
                    tree.printLevelOrder();
                    break;
                case 7:
                    // Salir del programa
                    System.out.println("Saliendo del programa...");
                    break;
                default: 
                    System.err.println("Opción inválida. Intente con un número entre 1 y 7.");
            }
        } while (opcion != 7);

        entrada.close(); // para cerrar el scanner
    }
}
