package geststock;

public class GestStock {

    public static void main(String[] args) {
        // Creamos las estructuras principales del sistema
        Inventario inventario = new Inventario();
        SistemaUsuarios sistema = new SistemaUsuarios();
        RegistroVentas registroVentas = new RegistroVentas();

        // Cargamos los datos de ejemplo
        inventario.cargarDatosDeEjemplo();
        sistema.cargarDatosDeEjemplo();

        int opcion;
        do {
            mostrarPortada();
            opcion = Entrada.leerEnteroEnRango("Opcion: ", 1, 2);

            if (opcion == 1) {
                if (intentarLogin(sistema)) {
                    // Si el login fue exitoso, entra al menú principal
                    menuPrincipal(inventario, sistema, registroVentas);
                    sistema.cerrarSesion();
                }
            }
        } while (opcion != 2);

        System.out.println();
        System.out.println("Hasta la proxima!");
    }

    // Pantalla de inicio (login o salir)
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

    // Muestra el menú según el rol del usuario conectado
    private static void mostrarMenuPrincipal(Usuario usuario) {
        Entrada.limpiarPantalla();
        System.out.println("============================================");
        System.out.println("  GESTSTOCK  |  " + usuario.getNombre()
                + " (" + usuario.getRol().getNombre() + ")");
        System.out.println("============================================");
        System.out.println();
        System.out.println("  --- CONSULTAS ---");
        System.out.println("   1. Listar todos los productos");
        System.out.println("   2. Listar por seccion");
        System.out.println("   3. Buscar por nombre");
        System.out.println("   4. Buscar por codigo");
        System.out.println("   5. Productos a reponer");
        System.out.println("   6. Estadisticas generales");
        System.out.println("   7. Reporte de ventas");
        System.out.println();
        System.out.println("  --- OPERACIONES ---");
        System.out.println("   8. Movimiento de stock");
        System.out.println();

        // Solo el administrador ve estas opciones
        if (usuario.esAdministrador()) {
            System.out.println("  --- ADMINISTRACION ---");
            System.out.println("   9. Alta de producto");
            System.out.println("  10. Modificar producto");
            System.out.println("  11. Eliminar producto");
            System.out.println();
        }

        System.out.println("   0. Cerrar sesion");
        System.out.println();
    }

    // Bucle principal del menú después del login
    private static void menuPrincipal(Inventario inventario, SistemaUsuarios sistema, RegistroVentas registroVentas) {
        Usuario usuario = sistema.getUsuarioConectado();
        int opcion;

        do {
            mostrarMenuPrincipal(usuario);
            opcion = Entrada.leerEnteroEnRango("Opcion: ", 0, 11);

            // Doble control de permisos: si no es admin e intenta opciones 9-11, se bloquea
            if (opcion >= 9 && opcion <= 11 && !usuario.esAdministrador()) {
                System.out.println();
                System.out.println("  >> Acceso denegado. Esa opcion es solo para administradores.");
                Entrada.pausar();
                continue;
            }

            switch (opcion) {
                case 1:
                    Reportes.listarTodos(inventario);
                    break;

                case 2:
                    Reportes.listarPorSeccion(inventario);
                    break;

                case 3:
                    Reportes.buscarPorNombre(inventario);
                    break;

                case 4:
                    Reportes.buscarPorCodigo(inventario);
                    break;

                case 5:
                    Reportes.reporteBajoStock(inventario);
                    break;

                case 8:
                    Operaciones.movimientoStock(inventario, registroVentas);
                    break;
                    
                case 9:
                    Operaciones.altaProducto(inventario);
                    break;

                case 10:
                    Operaciones.modificarProducto(inventario);
                    break;

                case 11:
                    Operaciones.eliminarProducto(inventario);
                    break;

                case 0:
                    System.out.println();
                    System.out.println("Cerrando sesion...");
                    break;

                default:
                    System.out.println();
                    System.out.println("  >> Esa opcion todavia no esta implementada.");
                    Entrada.pausar();
                    break;
            }
        } while (opcion != 0);
    }
}