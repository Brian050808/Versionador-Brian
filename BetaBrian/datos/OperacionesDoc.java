package datos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class OperacionesDoc {

    private Connection con;

    public OperacionesDoc(Connection con) {
        this.con = con;
    }

    public void insertarDocente(String nombre, String apPaterno, String apMaterno,
                                String rfc) throws Exception {

        CallableStatement cs = con.prepareCall("{CALL sp_ins_docente_brian(?,?,?,?)}");
        cs.setString(1, nombre);
        cs.setString(2, apPaterno);
        cs.setString(3, apMaterno);
        cs.setString(4, rfc);

        cs.execute();
        cs.close();
    }

    public ArrayList<String[]> obtenerDocentes() throws Exception {

        ArrayList<String[]> lista = new ArrayList<>();
        CallableStatement cs = con.prepareCall("{CALL sp_sel_docente_brian()}");
        ResultSet rs = cs.executeQuery();

        while (rs.next()) {
            lista.add(new String[]{
                rs.getString("ID_Docente"),
                rs.getString("Nombre"),
                rs.getString("Apellido_Paterno"),
                rs.getString("Apellido_Materno"),
                rs.getString("RFC")
            });
        }

        rs.close();
        cs.close();
        return lista;
    }

    public void actualizarDocente(int id, String nombre, String apPaterno,
                                  String apMaterno, String rfc) throws Exception {

        CallableStatement cs = con.prepareCall("{CALL sp_upd_docente_brian(?,?,?,?,?)}");
        cs.setInt(1, id);
        cs.setString(2, nombre);
        cs.setString(3, apPaterno);
        cs.setString(4, apMaterno);
        cs.setString(5, rfc);

        cs.execute();
        cs.close();
    }

    public void eliminarDocente(int id) throws Exception {

        CallableStatement cs = con.prepareCall("{CALL sp_del_docente_brian(?)}");
        cs.setInt(1, id);
        cs.execute();
        cs.close();
    }
}
