
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
    
    
 }

