package presentacion;

import java.util.Scanner;
import negocio.GestionDoc;

public class MenuDoc {
    public void seleccionar(String ip, String pass, String nom) {
        limpiarConsola();
        Scanner sc = new Scanner(System.in);
        int op = -1;
        GestionDoc obj = new GestionDoc();

        while (op != 0) {

            System.out.println("\n========== GESTIÓN DE DOCENTES ==========");
            System.out.println("1. Registrar Docente");
            System.out.println("2. Mostrar Docentes");
            System.out.println("3. Modificar Docente");
            System.out.println("4. Eliminar Docente");
            System.out.println("0. Regresar al menú principal");
            System.out.print("Seleccione una opción: ");
            op = sc.nextInt();

            switch (op) {
                case 1:
                    obj.crearDoc(ip, pass, nom);
                    break;

                case 2:
                    obj.leerDoc(ip, pass, nom);
                    break;

                case 3:
                    obj.actualizarDoc(ip, pass, nom);
                    break;

                case 4:
                    obj.eliminarDoc(ip, pass, nom);
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