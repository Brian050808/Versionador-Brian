package presentacion;

import java.util.Scanner;
import negocio.GestionMat;

public class MenuMat {
    public void seleccionar(String ip, String pass, String nom) {
        limpiarConsola();
        Scanner sc = new Scanner(System.in);
        int op = -1;
        GestionMat obj = new GestionMat();

        while (op != 0) {

            System.out.println("\n=========== GESTIÓN DE MATERIAS ===========");
            System.out.println("1. Registrar Materia");
            System.out.println("2. Mostrar Materias");
            System.out.println("3. Modificar Materia");
            System.out.println("4. Eliminar Materia");
            System.out.println("0. Regresar al menú principal");
            System.out.print("Seleccione una opción: ");
            op = sc.nextInt();

            switch (op) {
                case 1:
                    obj.crearMat(ip, pass, nom);
                    break;

                case 2:
                    obj.leerMat(ip, pass, nom);
                    break;

                case 3:
                    obj.actualizarMat(ip, pass, nom);
                    break;

                case 4:
                    obj.eliminarMat(ip, pass, nom);
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    public static void limpiarConsola() {
    try {
        	String os = System.getProperty("os.name").toLowerCase();

        	if (os.contains("win")) {
            	new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        	} else {
            	new ProcessBuilder("clear").inheritIO().start().waitFor();
        	}
    	} catch (Exception e) {
        	// Si falla, imprime varias líneas como respaldo
        	System.out.println(new String(new char[50]).replace("\0", "\n"));
    	}
	}
}