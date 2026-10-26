package geststock;

public class Operaciones {

    // ========== ALTA DE PRODUCTO ==========
    public static void altaProducto(Inventario inventario) {
        Entrada.limpiarPantalla();
        System.out.println("--- ALTA DE PRODUCTO ---");
        System.out.println();

        String nombre = Entrada.leerTexto("Nombre del producto: ");
        Seccion seccion = Entrada.leerSeccion("Seccion: ");
        double precio = Entrada.leerDecimalPositivo("Precio: $");
        int stock = Entrada.leerEnteroPositivo("Stock inicial: ");
        int stockMinimo = Entrada.leerEnteroPositivo("Stock minimo: ");

        System.out.println();
        if (Entrada.confirmar("Confirmas el alta?")) {
            Producto nuevo = inventario.agregarProducto(nombre, seccion, precio, stock, stockMinimo);
            System.out.println();
            System.out.println("Producto agregado con el codigo " + nuevo.getCodigo() + ".");
        } else {
            System.out.println("Alta cancelada.");
        }
        Entrada.pausar();
    }

    // ========== MODIFICAR PRODUCTO ==========
    public static void modificarProducto(Inventario inventario) {
        Entrada.limpiarPantalla();
        System.out.println("--- MODIFICAR PRODUCTO ---");
        System.out.println();

        int codigo = Entrada.leerEnteroPositivo("Codigo del producto: ");
        Producto p = inventario.buscarPorCodigo(codigo);

        if (p == null) {
            System.out.println("No se encontro ningun producto con ese codigo.");
            Entrada.pausar();
            return;
        }

        System.out.println();
        System.out.println(p);
        System.out.println();
        System.out.println("Que queres modificar?");
        System.out.println("  1. Nombre");
        System.out.println("  2. Seccion");
        System.out.println("  3. Precio");
        System.out.println("  4. Stock minimo");
        System.out.println("  0. Cancelar");
        System.out.println();

        int opcion = Entrada.leerEnteroEnRango("Opcion: ", 0, 4);

        switch (opcion) {
            case 1:
                p.setNombre(Entrada.leerTexto("Nuevo nombre: "));
                System.out.println("Nombre actualizado.");
                break;
            case 2:
                p.setSeccion(Entrada.leerSeccion("Nueva seccion: "));
                System.out.println("Seccion actualizada.");
                break;
            case 3:
                p.setPrecio(Entrada.leerDecimalPositivo("Nuevo precio: $"));
                System.out.println("Precio actualizado.");
                break;
            case 4:
                p.setStockMinimo(Entrada.leerEnteroPositivo("Nuevo stock minimo: "));
                System.out.println("Stock minimo actualizado.");
                break;
            case 0:
                System.out.println("Modificacion cancelada.");
                break;
        }
        Entrada.pausar();
    }

    // ========== ELIMINAR PRODUCTO (baja lógica) ==========
    public static void eliminarProducto(Inventario inventario) {
        Entrada.limpiarPantalla();
        System.out.println("--- ELIMINAR PRODUCTO ---");
        System.out.println();

        int codigo = Entrada.leerEnteroPositivo("Codigo del producto: ");
        Producto p = inventario.buscarPorCodigo(codigo);

        if (p == null) {
            System.out.println("No se encontro ningun producto con ese codigo.");
            Entrada.pausar();
            return;
        }

        System.out.println();
        System.out.println(p);
        System.out.println();

        if (Entrada.confirmar("Confirmas la eliminacion?")) {
            p.setActivo(false);
            System.out.println("Producto eliminado.");
        } else {
            System.out.println("Eliminacion cancelada.");
        }
        Entrada.pausar();
    }
}