package presentacion;

public class MensajeError {

    // Texto en rojo (ANSI). Si no lo quieres, borra estos dos.
    private static final String ROJO = "\u001B[31m";
    private static final String RESET = "\u001B[0m";

    public static void mostrarErrorLogin() {

        limpiarConsola();

        System.out.println("========================================");
        System.out.println(ROJO + "          ERROR DE INICIO DE SESIÓN" + RESET);
        System.out.println("========================================");
        System.out.println(" El usuario o la contraseña no son correctos.");
        System.out.println(" Verifique sus datos e intente nuevamente.");
        System.out.println("========================================");

        try {
            Thread.sleep(2000); 
        } catch (InterruptedException e) {}

        limpiarConsola();
        InicioSesion inicio = new InicioSesion();
        inicio.acceder();  
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
            System.out.println("\n".repeat(40));
        }
    }
}
