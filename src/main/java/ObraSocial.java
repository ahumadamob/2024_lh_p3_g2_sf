public class ObraSocial {
    private String dni;     // Atributo clave primaria
    private String nombre;
    private String codigo;

    // Constructor
    public ObraSocial(String dni, String nombre, String codigo) {
        this.dni = dni;
        this.nombre = nombre;
        this.codigo = codigo;
    }

    // Getters y setters
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "DNI: " + dni + ", Nombre: " + nombre + ", Código: " + codigo;
    }
}
