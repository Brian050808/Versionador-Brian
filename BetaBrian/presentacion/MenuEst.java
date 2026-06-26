package presentacion;

import java.util.Scanner;
import negocio.GestionEst;

public class MenuEst {
    public void seleccionar(String ip, String pass, String nom) {
        limpiarConsola();
        Scanner sc = new Scanner(System.in);
        int op = -1;
        GestionEst obj = new GestionEst();

        while (op != 0) {

            System.out.println("\n========= GESTIÓN DE ESTUDIANTES =========");
            System.out.println("1. Registrar Estudiante");
            System.out.println("2. Mostrar Estudiantes");
            System.out.println("3. Modificar Estudiante");
            System.out.println("4. Eliminar Estudiante");
            System.out.println("0. Regresar al menú principal");
            System.out.print("Seleccione una opción: ");
            op = sc.nextInt();

            switch (op) {
                case 1:
                    obj.crearEst(ip, pass, nom);
                    break;

                case 2:
                    obj.leerEst(ip, pass, nom);
                    break;

                case 3:
                    obj.actualizarEst(ip, pass, nom);
                    break;

                case 4:
                    obj.eliminarEst(ip, pass, nom);
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
