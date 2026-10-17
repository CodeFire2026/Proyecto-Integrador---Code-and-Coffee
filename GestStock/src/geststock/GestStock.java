package geststock;

public class GestStock {

    public static void main(String[] args) {
        SistemaUsuarios sistema = new SistemaUsuarios();
        sistema.cargarDatosDeEjemplo();
        int opcion;
        do {
            mostrarPortada();
            opcion = Entrada.leerEnteroEnRango("Opcion: ", 1, 2);

            if (opcion == 1) {
                if (intentarLogin(sistema)) {
                    System.out.println("Aca despues va el menu principal");
                    Entrada.pausar();
                    sistema.cerrarSesion();
                }
            }
        } while (opcion != 2);

        System.out.println();
        System.out.println("Hasta la proxima!");
    }

    // Muestra la pantalla de bienvenida con las opciones
    private static void mostrarPortada() {
        Entrada.limpiarPantalla();
        System.out.println("============================================");
        System.out.println("            G E S T S T O C K");
        System.out.println("     Sistema de Gestion de Productos");
        System.out.println("============================================");
        System.out.println();
        System.out.println("  1. Iniciar sesion");
        System.out.println("  2. Salir");
        System.out.println();
    }

    // Maneja el login con máximo 3 intentos
    private static boolean intentarLogin(SistemaUsuarios sistema) {
        int intentos = 0;
        final int MAX_INTENTOS = 3;

        while (intentos < MAX_INTENTOS) {
            System.out.println();
            System.out.println("--- INICIAR SESION ---");
            String nombre = Entrada.leerTexto("Usuario: ");
            String clave = Entrada.leerTexto("Clave: ");

            if (sistema.iniciarSesion(nombre, clave)) {
                Usuario u = sistema.getUsuarioConectado();
                System.out.println();
                System.out.println("Bienvenido, " + u.getNombre() + "!");
                System.out.println("Rol: " + u.getRol().getNombre());
                Entrada.pausar();
                return true;
            }

            intentos++;
            System.out.println();
            System.out.println("  >> Usuario o clave incorrectos.");
            if (intentos < MAX_INTENTOS) {
                System.out.println("  >> Intentos restantes: " + (MAX_INTENTOS - intentos));
            } else {
                System.out.println("  >> Demasiados intentos fallidos.");
                Entrada.pausar();
            }
        }
        return false;
    }
}