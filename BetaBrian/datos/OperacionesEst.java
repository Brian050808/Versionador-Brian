package datos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class OperacionesEst {

    private Connection con;

    public OperacionesEst(Connection con) {
        this.con = con;
    }

    public void insertarEstudiante(String nombre, String apPaterno, String apMaterno,
                                   String matricula) throws Exception {

        CallableStatement cs = con.prepareCall("{CALL sp_ins_estudiante_brian(?,?,?,?)}");
        cs.setString(1, nombre);
        cs.setString(2, apPaterno);
        cs.setString(3, apMaterno);
        cs.setString(4, matricula);
        cs.execute();
        cs.close();
    }

    public ArrayList<String[]> obtenerEstudiantes() throws Exception {

        ArrayList<String[]> lista = new ArrayList<>();
        CallableStatement cs = con.prepareCall("{CALL sp_sel_estudiante_brian()}");
        ResultSet rs = cs.executeQuery();

        while (rs.next()) {
            lista.add(new String[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
            });
        }

        rs.close();
        cs.close();
        return lista;
    }

    public void actualizarEstudiante(int id, String nombre, String apPaterno,
                                     String apMaterno, String matricula) throws Exception {

        CallableStatement cs = con.prepareCall("{CALL sp_upd_estudiante_brian(?,?,?,?,?)}");
        cs.setInt(1, id);
        cs.setString(2, nombre);
        cs.setString(3, apPaterno);
        cs.setString(4, apMaterno);
        cs.setString(5, matricula);
        cs.execute();
        cs.close();
    }

    public void eliminarEstudiante(int id) throws Exception {

        CallableStatement cs = con.prepareCall("{CALL sp_del_estudiante_brian(?)}");
        cs.setInt(1, id);
        cs.execute();
        cs.close();
    }
}
