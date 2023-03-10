import java.util.ArrayList;
import java.util.Scanner;

public class Equipo {
    private String nombre;
    private int puntos;
    private ArrayList<Jugador> plantilla = new ArrayList<Jugador>();
    public Equipo(String nombre) {
        this.nombre = nombre;
    }
    public Equipo(String nombre, int puntos) {
        this.nombre = nombre;
        this.puntos = puntos;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
        int p=plantilla.size()-1;
        if(p>-1){
            for(int i=0; i<=p;i++){
                plantilla.get(i).setEQ(this.getNombre());
            }
        }
    }
    public ArrayList<Jugador> getplantilla() {
        return plantilla;
    }
    public int getPuntos() {
        return puntos;
    }
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    public void setplantilla(ArrayList<Jugador> plantilla) {
        this.plantilla = plantilla;
    }

    @Override
    public String toString() {
        return "\nEquipo{" +
                "nombre='" + nombre + '\'' +
                ", plantilla=" + plantilla +
                "puntos='" + puntos + '\'' +
                '}';
    }

    //Añadir

    public void addJugador(String item1, String item2, String item3) {
        plantilla.add(new Jugador(item1, item2, item3));
        int lastJugador = plantilla.size()-1;
        plantilla.get(lastJugador).setEQ(this.getNombre());
    }

    public void addJugador(Jugador item) {
        plantilla.add(item);
    }

    //Eliminar

    //Elimina a un jugador de la plantilla del equipo
    public Jugador rmJugadordePlantilla() {
        int idJugador;
        int longitud;
        Jugador nJugador=null;
        longitud = plantilla.size();
            System.out.println("Introduzca el id del jugador");
            //Saca el id del jugador
            idJugador = Gestion.sacarYcomprobarNumero(0, longitud);
            nJugador=plantilla.get(idJugador);
            System.out.println("Jugador " + nJugador.getNombre() + " ha sido eliminad@");
            //Elimina el jugador con el id correspondiente
            plantilla.remove(idJugador);
        return nJugador;
    }

    public void rmPlantilla() {
        plantilla.removeAll(plantilla);
    }

}
