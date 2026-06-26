package datos;

import java.sql.*;
import java.util.ArrayList;

public class OperacionesAsig {

    private Connection con;

    public OperacionesAsig(Connection con) {
        this.con = con;
    }

    public void insertarAsignacion(int idEst, int idDoc, int idMat, String fecha){
        try {
            CallableStatement cs = con.prepareCall("{ call sp_ins_registro_brian(?,?,?,?) }");
            cs.setInt(1, idEst);
            cs.setInt(2, idDoc);
            cs.setInt(3, idMat);
            cs.setString(4, fecha);
            cs.execute();
            System.out.println("Asignación registrada correctamente.");
            System.out.println("Conexión es nula? " + (con == null));
        } catch (Exception e) {
            System.out.println("Conexión es nula? " + (con == null));
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public ArrayList<String[]> obtenerAsignaciones(){
        ArrayList<String[]> lista = new ArrayList<>();

        try {
            CallableStatement cs = con.prepareCall("{ call sp_sel_registro_brian() }");
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                lista.add(new String[]{
                    rs.getString("ID_Registra"),
                    rs.getString("Estudiante"),
                    rs.getString("Docente"),
                    rs.getString("Nombre_Materia"),
                    rs.getString("Fecha_Inscripcion")
                });
            }

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        return lista;
    }

    public void actualizarAsignacion(int id, int idEst, int idDoc, int idMat, String fecha){
        try {
            CallableStatement cs = con.prepareCall("{ call sp_upd_registro_brian(?,?,?,?,?) }");
            cs.setInt(1, id);
            cs.setInt(2, idEst);
            cs.setInt(3, idDoc);
            cs.setInt(4, idMat);
            cs.setString(5, fecha);
            cs.execute();
            System.out.println("Asignación actualizada correctamente.");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void eliminarAsignacion(int id){
        try {
            CallableStatement cs = con.prepareCall("{ call sp_del_registro_brian(?) }");
            cs.setInt(1, id);
            cs.execute();
            System.out.println("Asignación eliminada correctamente.");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public ArrayList<String[]> obtenerEstudiantes() {
    ArrayList<String[]> lista = new ArrayList<>();
    try {
        PreparedStatement ps = con.prepareStatement(
            "SELECT ID_Estudiante, Nombre, Apellido_Paterno FROM Estudiante"
        );
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            lista.add(new String[]{
                rs.getString("ID_Estudiante"),
                rs.getString("Nombre"),
                rs.getString("Apellido_Paterno")
            });
        }
    } catch (Exception e) {
        System.out.println("ERROR: " + e.getMessage());
    }
    return lista;
}

public ArrayList<String[]> obtenerDocentes() {
    ArrayList<String[]> lista = new ArrayList<>();
    try {
        PreparedStatement ps = con.prepareStatement(
            "SELECT ID_Docente, Nombre, Apellido_Paterno FROM Docente"
        );
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            lista.add(new String[]{
                rs.getString("ID_Docente"),
                rs.getString("Nombre"),
                rs.getString("Apellido_Paterno")
            });
        }
    } catch (Exception e) {
        System.out.println("ERROR: " + e.getMessage());
    }
    return lista;
}

public ArrayList<String[]> obtenerMaterias() {
    ArrayList<String[]> lista = new ArrayList<>();
    try {
        PreparedStatement ps = con.prepareStatement(
            "SELECT ID_Materia, Nombre_Materia FROM Materia"
        );
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            lista.add(new String[]{
                rs.getString("ID_Materia"),
                rs.getString("Nombre_Materia")
            });
        }
    } catch (Exception e) {
        System.out.println("ERROR: " + e.getMessage());
    }
    return lista;
}


}
