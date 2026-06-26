package negocio;

import java.util.Scanner;

import datos.ConexionMysBrian;
import datos.OperacionesMat;

public class GestionMat {

    Scanner sc = new Scanner(System.in);

    public void crearMat(String ip, String pass, String nom){
        limpiarConsola();
        System.out.println("\n===== REGISTRO DE MATERIA =====");

        System.out.print("Nombre de la materia: ");
        String nombre = sc.nextLine();

        System.out.print("Créditos: ");
        int creditos = Integer.parseInt(sc.nextLine());

        try {
            ConexionMysBrian con = new ConexionMysBrian(ip, pass, nom);
            OperacionesMat op = new OperacionesMat(con.conectar());
            op.insertarMateria(nombre, creditos);

            System.out.println("\n[OK] Materia registrada.");

        } catch (Exception e){
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void leerMat(String ip, String pass, String nom){
        limpiarConsola();
        System.out.println("\n===== LISTADO DE MATERIAS =====");

        try {
            ConexionMysBrian con = new ConexionMysBrian(ip, pass, nom);
            OperacionesMat op = new OperacionesMat(con.conectar());
            var lista = op.obtenerMaterias();

            if (lista.isEmpty()){
                System.out.println("No hay materias registradas.");
            }

            for (String[] mat : lista){
                System.out.println("ID: " + mat[0] +
                                   " | Nombre: " + mat[1] +
                                   " | Créditos: " + mat[2]);
            }

        } catch (Exception e){
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void actualizarMat(String ip, String pass, String nom){
        limpiarConsola();
        System.out.println("\n===== ACTUALIZAR MATERIA =====");

        System.out.print("Ingrese el ID de la materia: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Nuevo nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Nuevos créditos: ");
        int creditos = Integer.parseInt(sc.nextLine());

        try {
            ConexionMysBrian con = new ConexionMysBrian(ip, pass, nom);
            OperacionesMat op = new OperacionesMat(con.conectar());
            op.actualizarMateria(id, nombre, creditos);

            System.out.println("\n[OK] Materia actualizada.");

        } catch (Exception e){
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void eliminarMat(String ip, String pass, String nom){
        limpiarConsola();
        System.out.println("\n===== ELIMINAR MATERIA =====");

        System.out.print("ID de la materia: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("¿Seguro? (S/N): ");
        String confirma = sc.nextLine();

        if (!confirma.equalsIgnoreCase("S")){
            System.out.println("Operación cancelada.");
            return;
        }

        try {
            ConexionMysBrian con = new ConexionMysBrian(ip, pass, nom);
            OperacionesMat op = new OperacionesMat(con.conectar());
            op.eliminarMateria(id);

            System.out.println("\n[OK] Materia eliminada.");

        } catch (Exception e){
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
