package datos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class OperacionesMat {

    private Connection con;

    public OperacionesMat(Connection con){
        this.con = con;
    }

    public void insertarMateria(String nombre, int creditos) throws Exception {

        CallableStatement cs = con.prepareCall("{CALL sp_ins_materia_brian(?,?)}");
        cs.setString(1, nombre);
        cs.setInt(2, creditos);
        cs.execute();
        cs.close();
    }

    public ArrayList<String[]> obtenerMaterias() throws Exception {

        ArrayList<String[]> lista = new ArrayList<>();
        CallableStatement cs = con.prepareCall("{CALL sp_sel_materia_brian()}");
        ResultSet rs = cs.executeQuery();

        while (rs.next()){
            lista.add(new String[]{
                    rs.getString(1),   // idMateria
                    rs.getString(2),   // nombre
                    rs.getString(3)    // creditos
            });
        }

        rs.close();
        cs.close();
        return lista;
    }

    public void actualizarMateria(int id, String nombre, int creditos) throws Exception {

        CallableStatement cs = con.prepareCall("{CALL sp_upd_materia_brian(?,?,?)}");
        cs.setInt(1, id);
        cs.setString(2, nombre);
        cs.setInt(3, creditos);
        cs.execute();
        cs.close();
    }

    public void eliminarMateria(int id) throws Exception {

        CallableStatement cs = con.prepareCall("{CALL sp_del_materia_brian(?)}");
        cs.setInt(1, id);
        cs.execute();
        cs.close();
    }
}
