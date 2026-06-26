package negocio;

import java.util.Scanner;
import datos.ConexionMysBrian;
import datos.OperacionesAsig;

public class GestionAsig {

    Scanner sc = new Scanner(System.in);

    public void crearAsig(String ip, String pass, String nom){
    limpiarConsola();
    System.out.println("\n===== REGISTRAR ASIGNACIÓN =====");

    ConexionMysBrian con = new ConexionMysBrian(ip, pass, nom);
    OperacionesAsig op = new OperacionesAsig(con.conectar());

    System.out.println("\n=== ESTUDIANTES DISPONIBLES ===");
    var ests = op.obtenerEstudiantes();
    for (var e : ests) {
        System.out.println(e[0] + " | " + e[1] + " " + e[2]);
    }

    System.out.println("\n=== DOCENTES DISPONIBLES ===");
    var docs = op.obtenerDocentes();
    for (var d : docs) {
        System.out.println(d[0] + " | " + d[1] + " " + d[2]);
    }

    System.out.println("\n=== MATERIAS DISPONIBLES ===");
    var mats = op.obtenerMaterias();
    for (var m : mats) {
        System.out.println(m[0] + " | " + m[1]);
    }

        System.out.print("\nID Estudiante: ");
        int idEst = Integer.parseInt(sc.nextLine());

        System.out.print("ID Docente: ");
        int idDoc = Integer.parseInt(sc.nextLine());

        System.out.print("ID Materia: ");
        int idMat = Integer.parseInt(sc.nextLine());

        System.out.print("Fecha (YYYY-MM-DD HH:MM:SS): ");
        String fecha = sc.nextLine();

        op.insertarAsignacion(idEst, idDoc, idMat, fecha);
    }


    public void leerAsig(String ip, String pass, String nom){
        limpiarConsola();
        System.out.println("\n===== CONSULTA DE ASIGNACIONES =====");

        ConexionMysBrian con = new ConexionMysBrian(ip, pass, nom);
        OperacionesAsig op = new OperacionesAsig(con.conectar());

        var lista = op.obtenerAsignaciones();

        for (String[] a : lista) {
            System.out.println(
                "ID: " + a[0] +
                " | Estudiante: " + a[1] +
                " | Docente: " + a[2] +
                " | Materia: " + a[3] +
                " | Fecha: " + a[4]
            );
        }

        System.out.println("\nPresione ENTER para continuar...");
        sc.nextLine();
    }

    public void actualizarAsig(String ip, String pass, String nom){
        limpiarConsola();
        System.out.println("\n===== ACTUALIZAR ASIGNACIÓN =====");

        System.out.print("ID de Registro: ");
        int idReg = Integer.parseInt(sc.nextLine());

        System.out.print("Nuevo ID Estudiante: ");
        int idEst = Integer.parseInt(sc.nextLine());

        System.out.print("Nuevo ID Docente: ");
        int idDoc = Integer.parseInt(sc.nextLine());

        System.out.print("Nuevo ID Materia: ");
        int idMat = Integer.parseInt(sc.nextLine());

        System.out.print("Nueva Fecha: ");
        String fecha = sc.nextLine();

        ConexionMysBrian con = new ConexionMysBrian(ip, pass, nom);
        OperacionesAsig op = new OperacionesAsig(con.conectar());

        op.actualizarAsignacion(idReg, idEst, idDoc, idMat, fecha);
    }

    public void eliminarAsig(String ip, String pass, String nom){
        limpiarConsola();
        System.out.println("\n===== ELIMINAR ASIGNACIÓN =====");

        System.out.print("Ingrese el ID de la asignación: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("¿Seguro que desea eliminarlo? (S/N): ");
        String c = sc.nextLine().toUpperCase();

        if (c.equals("S")) {
            ConexionMysBrian con = new ConexionMysBrian(ip, pass, nom);
            OperacionesAsig op = new OperacionesAsig(con.conectar());
            op.eliminarAsignacion(id);
        } else {
            System.out.println("Operación cancelada.");
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
