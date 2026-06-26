package presentacion;

import java.util.Scanner;

public class MenuAdmin {

    public void seleccionar(String ip, String pass, String nom){
        Scanner opcion = new Scanner(System.in);

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

            System.out.println("\n========= MENÚ ADMINISTRADOR =========");
            System.out.println("1. Gestionar Estudiantes");
            System.out.println("2. Gestionar Materias");
            System.out.println("3. Gestionar Docentes");
            System.out.println("4. Asignar Alumnos");
            System.out.println("0. Salir");
            System.out.println("=======================================");
            System.out.print("Seleccione el numero de la opcion: ");
            
            int opc = opcion.nextInt();

            switch (opc) {
                case 1:
                    MenuEst objA = new MenuEst();
                    objA.seleccionar(ip, pass, nom);
                    break;

                case 2:
                    MenuMat objB = new MenuMat();
                    objB.seleccionar(ip, pass, nom);
                    break;

                case 3:
                    MenuDoc objC = new MenuDoc();
                    objC.seleccionar(ip, pass, nom);
                    break;

                case 4:
                    MenuAsig objD = new MenuAsig();
                    objD.seleccionar(ip, pass, nom);
                    break;

                case 0:
                    System.out.println("Saliendo del sistema...");
                    System.exit(0); 
                    break;

                default:
                    System.out.println("Error: Opción no válida");
                    System.out.println("Presione Enter para continuar...");
                    opcion.nextLine(); 
                    opcion.nextLine(); 
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
            System.out.println(new String(new char[50]).replace("\0", "\n"));
        }
    }
}
