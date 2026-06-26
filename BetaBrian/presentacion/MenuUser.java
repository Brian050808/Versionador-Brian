package presentacion;

import java.util.Scanner;
import negocio.GestionEst;

public class MenuUser {

    public void seleccionar(String ip, String pass, String nom){

        Scanner opcion = new Scanner(System.in);
        GestionEst obj = new GestionEst();

        while (true) {

            limpiarConsola();

            System.out.println("========================================");
            System.out.println("        SISTEMA CONTROL ESCOLAR         ");
            System.out.println("========================================");
            System.out.println(" Proyecto final - Arquitecturas de Software");
            System.out.println();
            System.out.println(" Herramientas utilizadas:");
            System.out.println(" - Lenguaje: Java");
            System.out.println(" - Arquitectura: 3 Capas (Presentación, Negocio, Datos)");
            System.out.println(" - SGBD: MySQL (Conexión remota)");
            System.out.println(" - Sistema Operativo: Windows");
            System.out.println(" - IDE: IntelliJ IDEA");
            System.out.println("========================================");
            System.out.println("          Bienvenido al Sistema          ");
            System.out.println("========================================");

            System.out.println("\n=========    MENÚ USUARIO    =========");
            System.out.println("1. Crear perfil de alumno");
            System.out.println("2. Actualizar perfil de alumno");
            System.out.println("3. Consultar perfiles de alumnos");
            System.out.println("0. Salir");
            System.out.println("========================================");
            System.out.print("Seleccione el numero de la opcion: ");

            int op = opcion.nextInt();
            opcion.nextLine(); // para limpiar buffer

            switch(op){

                case 1:
                    obj.crearEst(ip, pass, nom);
                    break;

                case 2:
                    obj.actualizarEst(ip, pass, nom);
                    break;

                case 3:
                    obj.leerEst(ip, pass, nom);

                    System.out.println("\nPresione ENTER para continuar...");
                    opcion.nextLine();
                    break;

                case 0:
                    System.out.println("Cerrando sesión...");
                    return; // salir del menú y regresar al sistema

                default:
                    System.out.println("Opción inválida. Presione ENTER para continuar...");
                    opcion.nextLine();
                    break;
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
            System.out.println("\n".repeat(50));
        }
    }
}
