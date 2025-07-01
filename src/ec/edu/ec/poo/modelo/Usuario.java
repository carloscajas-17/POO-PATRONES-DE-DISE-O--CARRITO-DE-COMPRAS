package ec.edu.ec.poo.modelo;

public class Usuario {
    private String id;
    private String contrasenia;
    private Rol rol;
    private String nombre; // ✅ nuevo campo opcional

    public Usuario() {}

    // Constructor para Login

    // Constructor extendido para Registro
    public Usuario(String id, String nombre, String contrasenia, Rol rol) {
        this.id = id;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getContrasenia() { return contrasenia; }
    public void setContrasenia(String contrasenia) { this.contrasenia = contrasenia; }

    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", rol=" + rol +
                '}';
    }
}
