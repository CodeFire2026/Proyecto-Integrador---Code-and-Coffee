package geststock;

public class Usuario {

    private String nombre;
    private String clave;
    private Rol rol;
    private boolean activo;

    // Crea un usuario nuevo. Siempre nace activo.
    public Usuario(String nombre, String clave, Rol rol) {
        this.nombre = nombre;
        this.clave = clave;
        this.rol = rol;
        this.activo = true;
    }

    public String getNombre() {
        return nombre;
    }

    public String getClave() {
        return clave;
    }

    public Rol getRol() {
        return rol;
    }

    public boolean isActivo() {
        return activo;
    }

    // Permite cambiar la contraseña
    public void setClave(String clave) {
        this.clave = clave;
    }

    // Permite cambiar el rol del usuario
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    // Activa o desactiva el usuario (una baja lógica)
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    // Devuelve true si el usuario tiene rol de administrador
    public boolean esAdministrador() {
        return rol == Rol.ADMINISTRADOR;
    }

    // Verifica si el nombre y la clave coinciden y el usuario está activo
    public boolean credencialesValidas(String nombreIngresado, String claveIngresada) {
        return activo
                && this.nombre.equalsIgnoreCase(nombreIngresado)
                && this.clave.equals(claveIngresada);
    }

    // Devuelve una fila formateada para mostrar en unas tablas
    public String aFilaDeTabla() {
        String estado = activo ? "Activo" : "Inactivo";
        return String.format("%-15s | %-15s | %-10s",
                nombre, rol.getNombre(), estado);
    }
}