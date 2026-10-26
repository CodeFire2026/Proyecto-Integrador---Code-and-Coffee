package geststock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class RegistroVentas {
    
    // Lista de ventas registradas y próximo ID disponible.
    private final List<Venta> ventas;
    private int proximoId;

    // Inicializa el registro vacío y el primer ID de venta.
    public RegistroVentas() {
        this.ventas = new ArrayList<>();
        this.proximoId = 1;
    }

    // Registra una venta con un ID correlativo y devuelve la venta creada.
    public Venta registrarVenta(int codigoProducto, String nombreProducto,
                                int cantidad, double precioUnitario) {
        Venta venta = new Venta(proximoId, codigoProducto, nombreProducto,
                cantidad, precioUnitario);
        ventas.add(venta);
        proximoId++;
        return venta;
    }

    public Venta buscarVentaPorId(int idVenta) {
        for (Venta venta : ventas) {
            if (venta.getIdVenta() == idVenta) {
                return venta;
            }
        }
        return null;
    }

    // Devuelve una lista de solo lectura para evitar modificaciones externas.
    public List<Venta> getVentas() {
        return Collections.unmodifiableList(ventas);
    }

    public boolean estaVacio() {
        return ventas.isEmpty();
    }

    public int getCantidadVentas() {
        return ventas.size();
    }

    public double calcularTotalVentas() {
        double total = 0;
        for (Venta venta : ventas) {
            total += venta.getTotal();
        }
        return total;
    }
}