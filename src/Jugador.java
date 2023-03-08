public class Jugador {
    private String nombre;
    private String apellido;
    private String clase;
    private Equipo EQ;

    public Jugador(String nombre, String apellido, String clase, Equipo EQ) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.clase = clase;
        this.EQ = EQ;
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

    public Equipo getEQ() {
        return EQ;
    }

    public void setEQ(Equipo EQ) {
        this.EQ = EQ;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre +
                ", Apellido: " + apellido +
                ", Clase: " + clase +
                ", Equipo: " + EQ.getNombre();
    }
}
