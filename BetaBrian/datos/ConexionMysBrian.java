package datos;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionMysBrian {

    private String ip;
    private String usuario;
    private String baseDatos;

    // ✔ constructor correcto
    public ConexionMysBrian(String ip, String baseDatos, String usuario){
        this.ip = ip;
        this.usuario = usuario;
        this.baseDatos = baseDatos;
    }

    public Connection conectar(){
        Connection conexion = null;

        try{
            String url = "jdbc:mysql://" + ip + ":3306/" + baseDatos + "?useSSL=false&serverTimezone=UTC&noAccessToProcedureBodies=true";

            conexion = DriverManager.getConnection(url, usuario, "123");

        } catch (Exception e){
            System.out.println("Error de conexión: " + e.getMessage());
        }

        return conexion;
    }
}
