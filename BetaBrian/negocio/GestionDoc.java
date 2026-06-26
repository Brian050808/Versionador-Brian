package negocio;

import java.util.Scanner;

import datos.OperacionesDoc;
import datos.ConexionMysBrian;

public class GestionDoc {

    Scanner sc = new Scanner(System.in);

    public void crearDoc(String ip, String pass, String nom){
        limpiarConsola();
        System.out.println("\n===== REGISTRO DE DOCENTE =====");

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Apellido Paterno: ");
        String apPaterno = sc.nextLine();

        System.out.print("Apellido Materno: ");
        String apMaterno = sc.nextLine();

        System.out.print("RFC: ");
        String rfc = sc.nextLine();

        try {
            ConexionMysBrian con = new ConexionMysBrian(ip, pass, nom);
            OperacionesDoc op = new OperacionesDoc(con.conectar());
            op.insertarDocente(nombre, apPaterno, apMaterno, rfc);

            System.out.println("\n[OK] Docente registrado.");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void leerDoc(String ip, String pass, String nom){
        limpiarConsola();
        System.out.println("\n===== LISTADO DE DOCENTES =====");

        try {
            ConexionMysBrian con = new ConexionMysBrian(ip, pass, nom);
            OperacionesDoc op = new OperacionesDoc(con.conectar());

            var lista = op.obtenerDocentes();

            for (String[] doc : lista) {
                System.out.println(
                    "ID: " + doc[0] + " | " +
                    doc[1] + " " + doc[2] + " " + doc[3] +
                    " | RFC: " + doc[4]
                );
            }

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void actualizarDoc(String ip, String pass, String nom){
        limpiarConsola();
        System.out.println("\n===== ACTUALIZAR DOCENTE =====");

        System.out.print("ID del docente: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Nuevo nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Nuevo apellido paterno: ");
        String apPaterno = sc.nextLine();

        System.out.print("Nuevo apellido materno: ");
        String apMaterno = sc.nextLine();

        System.out.print("Nuevo RFC: ");
        String rfc = sc.nextLine();

        try {
            ConexionMysBrian con = new ConexionMysBrian(ip, pass, nom);
            OperacionesDoc op = new OperacionesDoc(con.conectar());

            op.actualizarDocente(id, nombre, apPaterno, apMaterno, rfc);

            System.out.println("\n[OK] Docente actualizado.");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void eliminarDoc(String ip, String pass, String nom){
        limpiarConsola();
        System.out.println("\n===== ELIMINAR DOCENTE =====");

        System.out.print("ID del docente a eliminar: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("¿Seguro? (S/N): ");
        String conf = sc.nextLine().toUpperCase();

        if(!conf.equals("S")){
            System.out.println("Operación cancelada.");
            return;
        }

        try {
            ConexionMysBrian con = new ConexionMysBrian(ip, pass, nom);
            OperacionesDoc op = new OperacionesDoc(con.conectar());

            op.eliminarDocente(id);

            System.out.println("\n[OK] Docente eliminado.");
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
