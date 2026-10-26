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
    
    // ========== REGISTRAR VENTA ==========
    public static void registrarVenta(Inventario inventario,
                                      RegistroVentas registroVentas) {
        Entrada.limpiarPantalla();
        System.out.println("--- REGISTRAR VENTA ---");
        System.out.println();

        int codigo = Entrada.leerEnteroPositivo("Codigo del producto: ");
        Producto producto = inventario.buscarPorCodigo(codigo);

        if (producto == null || !producto.isActivo()) {
            System.out.println("El producto no existe o esta eliminado.");
            Entrada.pausar();
            return;
        }

        System.out.println();
        System.out.println(producto);
        System.out.println();

        int cantidad;
        do {
            cantidad = Entrada.leerEnteroPositivo("Cantidad a vender: ");

            if (cantidad <= 0) {
                System.out.println("La cantidad debe ser mayor que cero.");
            }
        } while (cantidad <= 0);

        if (cantidad > producto.getStock()) {
            System.out.println("Stock insuficiente. Stock disponible: "
                    + producto.getStock());
            Entrada.pausar();
            return;
        }

        double precioUnitario = producto.getPrecio();
        double total = precioUnitario * cantidad;

        System.out.println();
        System.out.println("Producto: " + producto.getNombre());
        System.out.println("Cantidad: " + cantidad);
        System.out.printf("Precio unitario: $%.2f%n", precioUnitario);
        System.out.printf("Total de la venta: $%.2f%n", total);
        System.out.println();

        if (!Entrada.confirmar("Confirmas la venta?")) {
            System.out.println("Venta cancelada.");
            Entrada.pausar();
            return;
        }

        // Descontar stock antes de registrar la venta.
        if (!producto.reducirStock(cantidad)) {
            System.out.println("No se pudo completar la venta. "
                    + "Verifica el stock disponible.");
            Entrada.pausar();
            return;
        }

        Venta venta = registroVentas.registrarVenta(
                producto.getCodigo(),
                producto.getNombre(),
                cantidad,
                precioUnitario
        );

        System.out.println();
        System.out.println("Venta registrada correctamente.");
        System.out.println(venta);
        System.out.println("Stock restante: " + producto.getStock());

        Entrada.pausar();
    }
    
    // ========== MOVIMIENTOS DE STOCK ==========
    public static void movimientoStock(Inventario inventario,
                                       RegistroVentas registroVentas) {
        int opcion;

        do {
            Entrada.limpiarPantalla();
            System.out.println("--- MOVIMIENTOS DE STOCK ---");
            System.out.println();
            System.out.println("1. Entrada de stock");
            System.out.println("2. Salida de stock");
            System.out.println("3. Registrar venta");
            System.out.println("0. Volver");
            System.out.println();

            opcion = Entrada.leerEnteroEnRango("Opcion: ", 0, 3);

            if (opcion == 3) {
                Operaciones.registrarVenta(inventario, registroVentas);
                continue;
            }

            if (opcion == 0) {
                break;
            }

            System.out.println();
            int codigo = Entrada.leerEnteroPositivo(
                    "Codigo del producto: ");

            Producto producto = inventario.buscarPorCodigo(codigo);

            if (producto == null || !producto.isActivo()) {
                System.out.println(
                        "Producto inexistente o eliminado.");
                Entrada.pausar();
                continue;
            }

            System.out.println();
            System.out.println(producto);
            System.out.println();

            int cantidad;
            do {
                cantidad = Entrada.leerEnteroPositivo(
                        "Cantidad: ");

                if (cantidad <= 0) {
                    System.out.println(
                            "La cantidad debe ser mayor que cero.");
                }
            } while (cantidad <= 0);

            if (opcion == 1) {
                if ((long) producto.getStock() + cantidad
                        > Integer.MAX_VALUE) {
                    System.out.println(
                            "La cantidad supera el stock maximo permitido.");
                    Entrada.pausar();
                    continue;
                }

                System.out.println("Stock actual: "
                        + producto.getStock());
                System.out.println("Stock despues de la entrada: "
                        + ((long) producto.getStock() + cantidad));

                if (Entrada.confirmar("Confirmas la entrada?")) {
                    producto.aumentarStock(cantidad);
                    System.out.println("Stock actualizado correctamente.");
                } else {
                    System.out.println("Operacion cancelada.");
                }

            } else if (opcion == 2) {
                if (cantidad > producto.getStock()) {
                    System.out.println(
                            "Stock insuficiente. Disponible: "
                            + producto.getStock());
                    Entrada.pausar();
                    continue;
                }

                System.out.println("Stock actual: "
                        + producto.getStock());
                System.out.println("Stock despues de la salida: "
                        + (producto.getStock() - cantidad));

                if (Entrada.confirmar("Confirmas la salida?")) {
                    producto.reducirStock(cantidad);
                    System.out.println("Stock actualizado correctamente.");
                } else {
                    System.out.println("Operacion cancelada.");
                }
            }

            Entrada.pausar();

        } while (opcion != 0);
    }

}