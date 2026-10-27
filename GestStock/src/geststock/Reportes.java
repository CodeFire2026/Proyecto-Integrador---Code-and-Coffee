
package geststock;
import java.util.List;
public class Reportes {
    public static void mostrarTablaProductos(List<Producto> productos) {
        System.out.println(String.format("%-5s | %-26s | %-9s | %-11s | %s","COD", "NOMBRE", "SECCION", "PRECIO", "STOCK"));
        System.out.println("------+----------------------------+-----------+-------------+--------");

        if (productos.isEmpty()) {
            System.out.println("  (no hay productos para mostrar)");
            return;
        }

        for (int i = 0; i < productos.size(); i++) {
            System.out.println(productos.get(i).aFilaDeTabla());
        }
    }
    
    public static void listarTodos(Inventario inventario) {
        Entrada.limpiarPantalla();
        System.out.println("--- LISTADO COMPLETO DE PRODUCTOS ---");
        System.out.println();

        List<Producto> activos = inventario.getProductosActivos();
        mostrarTablaProductos(activos);

        System.out.println();
        System.out.println("Total de productos activos: " + activos.size());
        Entrada.pausar();
    }
    
     public static void listarPorSeccion(Inventario inventario) {
        Entrada.limpiarPantalla();
        System.out.println("--- LISTAR POR SECCION ---");

        Seccion seccion = Entrada.leerSeccion("Elegi una seccion: ");

        System.out.println();
        System.out.println("Productos en la seccion: " + seccion.getNombre());
        System.out.println();

        List<Producto> filtrados = inventario.getProductosPorSeccion(seccion);
        Reportes.mostrarTablaProductos(filtrados);

        System.out.println();
        System.out.println("Productos encontrados: " + filtrados.size());
        Entrada.pausar();
    }
    
    public static void buscarPorNombre(Inventario inventario) {
        Entrada.limpiarPantalla();
        System.out.println("--- BUSCAR POR NOMBRE ---");

        String texto = Entrada.leerTexto("Ingrese el nombre del producto: ");

        System.out.println();
        System.out.println("Resultados de la busqueda: ");
        System.out.println();

        List<Producto> filtrados = inventario.buscarPorNombre(texto);
        Reportes.mostrarTablaProductos(filtrados);

        System.out.println();
        System.out.println("Productos encontrados: " + filtrados.size());
        Entrada.pausar();
    }
    
    public static void reporteBajoStock(Inventario inventario) {
    Entrada.limpiarPantalla();
    System.out.println("--- REPORTE DE BAJO STOCK ---");

    List<Producto> productos = inventario.getProductosAReponer();

    Reportes.mostrarTablaProductos(productos);

    System.out.println();

    if (productos.isEmpty()) {
        System.out.println("Excelente, no hay productos con bajo stock.");
    } else {
        System.out.println("Productos que necesitan reposicion: " + productos.size());
    }

    Entrada.pausar();
}
    
    public static void buscarPorCodigo(Inventario inventario) {
        Entrada.limpiarPantalla();
        System.out.println("--- BUSCAR POR CODIGO ---");
        System.out.println();

        int codigo = Entrada.leerEnteroPositivo("Codigo: ");
        Producto p = inventario.buscarPorCodigo(codigo);

        System.out.println();
        if (p == null) {
            System.out.println("No se encontro ningun producto con ese codigo.");
        } else {
            System.out.println(p);
        }
        Entrada.pausar();
    }
    
    public static void estadisticasGenerales(Inventario inventario,
                                         RegistroVentas registroVentas) {
    Entrada.limpiarPantalla();

    System.out.println("--- ESTADISTICAS GENERALES ---");
    System.out.println();

    List<Producto> productos = inventario.getProductosActivos();

    double valorInventario = 0;
    int unidadesStock = 0;
    int productosBajoStock = 0;

    for (Producto producto : productos) {
        valorInventario += producto.getValorEnStock();
        unidadesStock += producto.getStock();

        if (producto.necesitaReposicion()) {
            productosBajoStock++;
        }
    }

    System.out.println("Productos activos: " + productos.size());
    System.out.println("Unidades totales en stock: " + unidadesStock);
    System.out.printf("Valor total del inventario: $%.2f%n", valorInventario);
    System.out.println("Productos que necesitan reposicion: " + productosBajoStock);
    System.out.println("Ventas registradas: " + registroVentas.getCantidadVentas());
    System.out.printf("Recaudacion total: $%.2f%n",
            registroVentas.calcularTotalVentas());

    Entrada.pausar();
    }
    
    public static void reporteVentas(RegistroVentas registroVentas) {
    Entrada.limpiarPantalla();

    System.out.println("--- REPORTE DE VENTAS ---");
    System.out.println();

    if (registroVentas.estaVacio()) {
        System.out.println("Todavia no hay ventas registradas.");
        Entrada.pausar();
        return;
    }

    for (Venta venta : registroVentas.getVentas()) {
        System.out.println(venta);
    }

    System.out.println();
    System.out.println("Cantidad de ventas: "
            + registroVentas.getCantidadVentas());

    System.out.printf("Recaudacion total: $%.2f%n",
            registroVentas.calcularTotalVentas());

    Entrada.pausar();
    }
}
