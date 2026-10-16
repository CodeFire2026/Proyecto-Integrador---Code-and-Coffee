package geststock;
    // Creamos la clase producto.
public class Producto {
    
    // Arrancamos con los atributos.
    
    private int codigo;
    private String nombre;
    private Seccion seccion;
    private double precio;
    private int stock;
    private int stockMinimo;
    private boolean activo;
    
    // Seguimos ahora con el constructor.
 
    public Producto (int codigo, String nombre, Seccion seccion, double precio, int stock, int stockMinimo){
        this.codigo = codigo;
        this.nombre = nombre;
        this.seccion = seccion;
        this.precio = precio;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
        this.activo = true;
    }

    // Establecemos los getters y setters
    
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Seccion getSeccion() {
        return this.seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    public double getPrecio() {
        return this.precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStockMinimo() {
        return this.stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public boolean isActivo() {
        return this.activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public int getStock() {
        return this.stock;
    }

    // Métodos propios del producto.
    
    public boolean necesitaReposicion() {
        return stock <= stockMinimo;
    }
    
    public double getValorEnStock() {
        return precio * stock;
    }
    
    public void aumentarStock (int cantidad) {
        this.stock = this.stock + cantidad;
    }
    
    public boolean reducirStock (int cantidad) {
        if (cantidad > this.stock) {
            return false;
        }
        this.stock = this.stock - cantidad;
        return true;
    }
    
    // Mostramos el Producto
    
    public String aFilaDeTabla() {
        String aviso = "";
        if (necesitaReposicion()) {
            aviso = " (!)";
        }
        return String.format("%-5d | %-26s | %-9s | $%-10.2f | %d%s",
                codigo, nombre, seccion.getNombre(), precio, stock, aviso);
    }
    
    // Utilizamos el método toString
    
    @Override
    public String toString() {
        String estado = activo ? "Activo" : "ELIMINADO";
        return "-------------------------------------------\n"
             + String.format("Codigo:        %d%n", codigo)
             + String.format("Nombre:        %s%n", nombre)
             + String.format("Seccion:       %s%n", seccion.getNombre())
             + String.format("Precio:        $%.2f%n", precio)
             + String.format("Stock actual:  %d%n", stock)
             + String.format("Stock minimo:  %d%n", stockMinimo)
             + String.format("Estado:        %s%n", estado)
             + "-------------------------------------------";
    }
    
}