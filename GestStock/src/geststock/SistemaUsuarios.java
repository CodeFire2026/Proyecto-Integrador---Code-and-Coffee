package geststock;

import java.util.ArrayList;
import java.util.List;

public class SistemaUsuarios {

    private List<Usuario> usuario;    // Lista de todos los usuarios del sistema
    private Usuario usuarioConectado;   // Guarda quién está logueado ahora

    public SistemaUsuarios() {
        this.usuario = new ArrayList<Usuario>();
        this.usuarioConectado = null;
    }
// Carga usuarios de prueba para poder probar el sistema
    public void cargarDatosDeEjemplo() {
        usuario.add(new Usuario("Santiago", "admin123", Rol.ADMINISTRADOR));
        usuario.add(new Usuario("Mateo", "1234", Rol.ADMINISTRADOR));
        usuario.add(new Usuario("Noelia", "1234", Rol.EMPLEADO));
        usuario.add(new Usuario("Bruno", "1234", Rol.EMPLEADO));
    }
    // Busca si existe un usuario con ese nombre y clave. Si lo encuentra, lo deja conectado.
    public boolean iniciarSesion(String nombre, String clave) {
        for (int i = 0; i < usuario.size(); i++) {
            Usuario u = usuario.get(i);
            if (u.credencialesValidas(nombre, clave)) {
                usuarioConectado = u;
                return true;
            }
        }
        return false;
    }

    // Cierra la sesión actual
    public void cerrarSesion() {
        usuarioConectado = null;
    }

    // Devuelve el usuario que está logueado en este momento
    public Usuario getUsuarioConectado() {
        return usuarioConectado;
    }

    // Busca un usuario activo por su nombre. Devuelve null si no lo encuentra.
    public Usuario buscarPorNombre(String nombre) {
        for (int i = 0; i < usuario.size(); i++) {
            Usuario u = usuario.get(i);
            if (u.isActivo() && u.getNombre().equalsIgnoreCase(nombre)) {
                return u;
            }
        }
        return null;
    }

    // Devuelve true si ya existe un usuario con ese nombre
    public boolean existeUsuario(String nombre) {
        return buscarPorNombre(nombre) != null;
    }
}