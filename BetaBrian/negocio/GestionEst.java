package negocio;

import java.util.Scanner;

import datos.ConexionMysBrian;
import datos.OperacionesEst;

public class GestionEst {

    Scanner sc = new Scanner(System.in);

    public void crearEst(String ip, String pass, String nom){
        limpiarConsola();
        System.out.println("\n===== REGISTRO DE ESTUDIANTE =====");

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Apellido Paterno: ");
        String apPaterno = sc.nextLine();

        System.out.print("Apellido Materno: ");
        String apMaterno = sc.nextLine();

        System.out.print("Matrícula: ");
        String matricula = sc.nextLine();

        try {
            ConexionMysBrian con = new ConexionMysBrian(ip, pass, nom);
            OperacionesEst op = new OperacionesEst(con.conectar());
            op.insertarEstudiante(nombre, apPaterno, apMaterno, matricula);

            System.out.println("\n[OK] Estudiante registrado.");

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void leerEst(String ip, String pass, String nom){
        limpiarConsola();
        System.out.println("\n===== LISTADO DE ESTUDIANTES =====");

        try {
            ConexionMysBrian con = new ConexionMysBrian(ip, pass, nom);
            OperacionesEst op = new OperacionesEst(con.conectar());

            var lista = op.obtenerEstudiantes();

            if (lista.isEmpty()) {
                System.out.println("No hay estudiantes registrados.");
            }

            for (String[] est : lista) {
                System.out.println(
                    "ID: " + est[0] + " | " +
                    est[1] + " " + est[2] + " " + est[3] +
                    " | Matrícula: " + est[4]
                );
            }

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void actualizarEst(String ip, String pass, String nom){
        limpiarConsola();
        System.out.println("\n===== ACTUALIZAR ESTUDIANTE =====");

        System.out.print("ID del estudiante: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Nuevo Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Nuevo Apellido Paterno: ");
        String apPaterno = sc.nextLine();

        System.out.print("Nuevo Apellido Materno: ");
        String apMaterno = sc.nextLine();

        System.out.print("Nueva Matrícula: ");
        String matricula = sc.nextLine();

        try {
            ConexionMysBrian con = new ConexionMysBrian(ip, pass, nom);
            OperacionesEst op = new OperacionesEst(con.conectar());
            op.actualizarEstudiante(id, nombre, apPaterno, apMaterno, matricula);

            System.out.println("\n[OK] Estudiante actualizado.");

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void eliminarEst(String ip, String pass, String nom){
        limpiarConsola();
        System.out.println("\n===== ELIMINAR ESTUDIANTE =====");

        System.out.print("ID del estudiante a eliminar: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("¿Está seguro? (S/N): ");
        String confirma = sc.nextLine();

        if (!confirma.equalsIgnoreCase("S")) {
            System.out.println("Operación cancelada.");
            return;
        }

        try {
            ConexionMysBrian con = new ConexionMysBrian(ip, pass, nom);
            OperacionesEst op = new OperacionesEst(con.conectar());
            op.eliminarEstudiante(id);

            System.out.println("\n[OK] Estudiante eliminado.");

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
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
