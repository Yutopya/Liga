public class Jugador {
    private String nombre;
    private String apellido;
    private String clase;
    private String EQ;
    public Jugador(String nombre, String apellido, String clase) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.clase = clase;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getEQ() {
        return EQ;
    }

    public void setEQ(String EQ) {
        this.EQ = EQ;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre +
                ", Apellido: " + apellido +
                ", Clase: " + clase +
                ", Equipo: " + EQ;
    }
}
