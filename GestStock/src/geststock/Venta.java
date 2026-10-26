package geststock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Venta {
    
    private final int idVenta;
    private final LocalDateTime fecha;
    private final int codigoProducto;
    private final String nombreProducto;
    private final int cantidad;
    private final double precioUnitario;
    private final double total;

    public Venta(int idVenta, int codigoProducto, String nombreProducto,
                 int cantidad, double precioUnitario) {
        
        // Validar los datos para evitar registrar ventas con valores inválidos.
        if (idVenta <= 0) {
            throw new IllegalArgumentException("El ID de venta debe ser mayor que cero.");
        }
        if (codigoProducto <= 0) {
            throw new IllegalArgumentException("El código de producto debe ser mayor que cero.");
        }
        if (nombreProducto == null || nombreProducto.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero.");
        }
        if (!Double.isFinite(precioUnitario) || precioUnitario < 0) {
            throw new IllegalArgumentException("El precio unitario no puede ser negativo ni inválido.");
        }

        this.idVenta = idVenta;
        this.fecha = LocalDateTime.now();
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto.trim();
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        // Guardar el total calculado para conservar el importe original de la venta.
        this.total = cantidad * precioUnitario;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Venta #" + idVenta
                + " | Fecha: " + fecha.format(formato)
                + " | Producto: " + nombreProducto
                + " (código " + codigoProducto + ")"
                + " | Cantidad: " + cantidad
                + " | Precio unitario: $" + String.format("%.2f", precioUnitario)
                + " | Total: $" + String.format("%.2f", total);
    }
}