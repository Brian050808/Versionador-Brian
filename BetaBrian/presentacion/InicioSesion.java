package presentacion;

import java.util.Scanner;

public class InicioSesion {
    public void acceder(){
        int tipoUsuario;
        Scanner entrada = new Scanner(System.in);
        
        System.out.println("--- Inicio de Sesión para Conexión a BD Remota ---");
        System.out.print("Ingrese la Dirección IP: ");
        String ipServidor = entrada.nextLine();
        
        System.out.print("Ingrese el Nombre de Usuario: ");
        String usuarioBD = entrada.nextLine();
        
        System.out.print("Ingrese el Nombre de la Base de Datos: ");
        String contrasenaBD = entrada.nextLine();

        if(usuarioBD.equals("admin")){
            MenuAdmin obj = new MenuAdmin();
            obj.seleccionar(ipServidor, contrasenaBD, usuarioBD);
        } else if (usuarioBD.equals("user")){
            MenuUser obj = new MenuUser();
            obj.seleccionar(ipServidor, contrasenaBD, usuarioBD);
        } else if (!usuarioBD.equals("admin") && !usuarioBD.equals("user")) {
    MensajeError.mostrarErrorLogin();
    return;
        }
    }
}
