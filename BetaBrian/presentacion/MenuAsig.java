package presentacion;

import java.util.Scanner;
import negocio.GestionAsig;

public class MenuAsig {

    public void seleccionar(String ip, String pass, String nom) {

        Scanner sc = new Scanner(System.in);
        GestionAsig obj = new GestionAsig();

        while (true) {

            limpiarConsola();

            System.out.println("========================================");
            System.out.println("      ASIGNACIÓN ESTUDIANTE-MATERIA     ");
            System.out.println("========================================");
            System.out.println("1. Registrar asignación");
            System.out.println("2. Actualizar asignación");
            System.out.println("3. Consultar asignaciones");
            System.out.println("4. Eliminar asignación");
            System.out.println("0. Volver");
            System.out.println("========================================");
            System.out.print("Seleccione una opción: ");

            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    obj.crearAsig(ip, pass, nom);
                    break;

                case 2:
                    obj.actualizarAsig(ip, pass, nom);
                    break;

                case 3:
                    obj.leerAsig(ip, pass, nom);
                    break;

                case 4:
                    obj.eliminarAsig(ip, pass, nom);
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Opción inválida. Presione ENTER...");
                    sc.nextLine();
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
