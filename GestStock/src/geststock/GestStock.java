package geststock;

public class GestStock {
    
    public static void main(String[] args) {
        System.out.println("=== SIMULACION DE ALTA DE PRODUCTO ===");

String nombre = Entrada.leerTexto("Nombre del producto: ");
Seccion sec = Entrada.leerSeccion("Seccion: ");
double precio = Entrada.leerDecimalPositivo("Precio: $");
int stock = Entrada.leerEnteroPositivo("Stock inicial: ");
int stockMin = Entrada.leerEnteroPositivo("Stock minimo: ");
int mes = Entrada.leerEnteroEnRango("Mes de alta (1-12): ", 1, 12);

System.out.println();
System.out.println("Producto: " + nombre);
System.out.println("Seccion: " + sec.getNombre());
System.out.println("Precio: $" + precio);
System.out.println("Stock: " + stock + " (minimo " + stockMin + ")");
System.out.println("Mes: " + mes);

if (Entrada.confirmar("Confirmas el alta?")) {
    System.out.println("Producto dado de alta.");
} else {
    System.out.println("Alta cancelada.");
}
Entrada.pausar();
    }
}