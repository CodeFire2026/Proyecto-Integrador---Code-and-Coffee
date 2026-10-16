package geststock;

public enum Seccion {
    
    // Creamos la sección Enum, la cual le pone nombre a cada valor posible.
    
    BEBIDAS ("Bebidas"),
    FRUTAS ("Frutas"),
    VERDURAS ("Verduras"),
    CARNES ("Carnes"),
    LACTEOS ("Lácteos"),
    ALMACEN ("Almacén");
    
    private final String nombre;
    
    Seccion (String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public static Seccion porNumero (int numero) {
        Seccion[] valores = Seccion.values();
        if (numero >=1 && numero <= valores.length) {
            return valores [numero - 1];
        }
        return null;
    }
    
    public static void mostrarOpciones() {
        Seccion[] valores = Seccion.values();
        System.out.println();
        System.out.println("Secciones disponibles:");
        for (int i = 0; i < valores.length; i++) {
            System.out.println("   " + (i + 1) + ". " + valores[i].getNombre());
        }
        System.out.println();
    }
    
}
