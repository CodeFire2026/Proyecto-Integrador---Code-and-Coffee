
package geststock;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<Producto> productos;
    private int proximoCodigo;
    
    public Inventario() {
        this.productos = new ArrayList<>();
        this.proximoCodigo = 1;
    }
    
    public Producto agregarProducto(String nombre, Seccion seccion,
            double precio,
                                   int stock, int stockMinimo){
        Producto p = new Producto(proximoCodigo, nombre, seccion, precio, stock, stockMinimo);
        productos.add(p);
        proximoCodigo++;
        return p;
    }
    
    public List<Producto> getProductosActivos() {
        List<Producto> activos = new ArrayList<>();
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).isActivo()) {
                activos.add(productos.get(i));
            }
        }
        return activos;
    }
    
    public List<Producto> getProductosPorSeccion(Seccion seccion) {
         List<Producto> lista = new ArrayList<>();

        for (int i = 0; i < productos.size(); i++) {
        Producto p = productos.get(i);

            if (p.getSeccion() == seccion) {
            lista.add(p);
            }
        }
        return lista;
    }
    
    public List<Producto> getProductosAReponer(){
        List<Producto> lista = new ArrayList<>();
        for (int i = 0; i < productos.size();i++){
            Producto p = productos.get(i);

            if (p.necesitaReposicion()) {
            lista.add(p);
            }
        }
        return lista;
    }
        
    public List<Producto> getTodos() {
        return productos;
    }
    
    public int getCantidadActivos() {
        return getProductosActivos().size();
    }
    
    public void cargarDatosDeEjemplo() {
        //seccion bebidas
        agregarProducto("Coca Cola 2.25L", Seccion.BEBIDAS, 5500.0, 50,10);
        agregarProducto("Sprite 1.5L", Seccion.BEBIDAS, 5000.0, 30, 8);
        agregarProducto("Agua Mineral 2L", Seccion.BEBIDAS, 3000.0, 80, 20);
        agregarProducto("Manaos 2L", Seccion.BEBIDAS, 2400.0, 5, 10);
        agregarProducto("Cerveza Quilmes 1L", Seccion.BEBIDAS, 3200.0, 5, 10);
        //seccion lacteos
        agregarProducto("Yogurt La Serenisima 1L", Seccion.LACTEOS, 5600.0, 25, 10);
        agregarProducto("Leche Liquida 1L", Seccion.LACTEOS, 3500.0, 30,10);
        agregarProducto("Queso Crema 500gs", Seccion.LACTEOS, 4300.0, 20, 10);
        agregarProducto("Crema de Leche", Seccion.LACTEOS, 6800.0, 15, 80);
        agregarProducto("Dulce de Leche", Seccion.LACTEOS, 4200.0, 40, 10); 
        //seccion de frutas
        agregarProducto("Banana x kg", Seccion.FRUTAS, 3400.0, 25, 5);
        agregarProducto("Naranja x kg", Seccion.FRUTAS, 4500.0, 15, 5);
        agregarProducto("Manzana x kg", Seccion.FRUTAS, 3600.0, 25, 5);
        agregarProducto("Manzana verde x kg", Seccion.FRUTAS, 3500.0, 19, 5);
        agregarProducto("Durazno x kg", Seccion.FRUTAS, 4700.0, 14, 5);
        //seccion verduras
        agregarProducto("Papa x kg", Seccion.VERDURAS, 4500.0, 20, 5);
        agregarProducto("lechuga x kg", Seccion.VERDURAS, 3000.0, 14, 5);
        agregarProducto("Limón x kg", Seccion.VERDURAS, 2500.0, 12, 5);
        agregarProducto("Zapallo x kg", Seccion.VERDURAS, 3400.0, 10, 5);
        agregarProducto("Pepino x kg", Seccion.VERDURAS, 1500.0, 13, 5);
        //seccion carnes
        agregarProducto("Matambre x kg", Seccion.CARNES, 15000.0, 25, 2);
        agregarProducto("Carne Molida x kg", Seccion.CARNES, 16000.0, 12, 2);
        agregarProducto("Blanda x kg", Seccion.CARNES, 14500.0, 7, 2);
        agregarProducto("Chorizo parrillero x kg", Seccion.CARNES, 9500.0, 30, 2);
        agregarProducto("Morcilla x kg", Seccion.CARNES, 5000.0, 13, 2);
        //seccion almacen
        agregarProducto("Arveja en lata", Seccion.ALMACEN, 1500.0, 25, 10);
        agregarProducto("Pañales Pampers XL", Seccion.ALMACEN, 15000.0, 20, 5);
        agregarProducto("Papel Higienico x4", Seccion.ALMACEN, 3000.0, 33, 3);
        agregarProducto("Pan x Kilo", Seccion.ALMACEN, 3500.0, 25, 10);
        agregarProducto("Huevo x Maple", Seccion.ALMACEN, 5500.0, 34, 10);
    }
    
    public Producto buscarPorCodigo(int codigo) {
        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            if (p.getCodigo() == codigo && p.isActivo()) {
                return p;
            }
        }
        return null;
    }
    
    public List<Producto> buscarPorNombre(String texto) {
        List<Producto> encontrados = new ArrayList<Producto>();
        String busqueda = texto.toLowerCase();

        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            if (p.isActivo() && p.getNombre().toLowerCase().contains(busqueda)) {
                encontrados.add(p);
            }
        }
        return encontrados;
    }   
    
}
