package geststock;

public enum Rol {

    ADMINISTRADOR("Administrador"),
    EMPLEADO("Empleado");

    private final String nombre;

    Rol(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
//Traduce un numero del menu al rol correspondiente
    
    public static Rol porNumero(int numero) {
        if (numero == 1) {
            return ADMINISTRADOR;
        }
        if (numero == 2) {
            return EMPLEADO;
        }
        return null;
    }
}