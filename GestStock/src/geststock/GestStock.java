package geststock;

public class GestStock {
    
    
    public static void main(String[] args) {
        Producto p = new Producto(1, "Coca Cola 2.25L", Seccion.BEBIDAS, 2500.0, 50, 10);

        System.out.println("=== PROBANDO SECCION ===");

        Seccion.mostrarOpciones();

        System.out.println("porNumero(1): " + Seccion.porNumero(1));
        System.out.println("porNumero(4): " + Seccion.porNumero(4));
        System.out.println("porNumero(6): " + Seccion.porNumero(6));
        System.out.println("porNumero(9): " + Seccion.porNumero(9));
        System.out.println("porNumero(0): " + Seccion.porNumero(0));
        
        System.out.println("Cantidad de secciones: " + Seccion.values().length);
        System.out.println("getNombre de CARNES: " + Seccion.CARNES.getNombre());
        System.out.println();
        
        System.out.println(p);
        System.out.println();
        System.out.println(p.aFilaDeTabla());
        System.out.println();

        System.out.println("Valor en stock: " + p.getValorEnStock());
        System.out.println("Necesita reposicion? " + p.necesitaReposicion());

        p.reducirStock(45);
        System.out.println("Despues de vender 45, stock: " + p.getStock());
        System.out.println("Necesita reposicion? " + p.necesitaReposicion());

        boolean pudo = p.reducirStock(100);
        System.out.println("Pude vender 100? " + pudo);
        System.out.println("Stock sigue en: " + p.getStock());
    }
}