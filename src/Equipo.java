import java.util.ArrayList;
import java.util.Scanner;

public class Equipo {
    private String nombre;
    private ArrayList<Jugador> todosJugadores = new ArrayList<>();

    private int puntos;
    Scanner lector = new Scanner(System.in);
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
    }

    public ArrayList<Jugador> getTodosJugadores() {
        return todosJugadores;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }


    public void setTodosJugadores(ArrayList<Jugador> todosJugadores) {
        this.todosJugadores = todosJugadores;
    }

    @Override
    public String toString() {
        return "\nEquipo{" +
                "nombre='" + nombre + '\'' +
                ", todosJugadores=" + todosJugadores +
                "puntos='" + puntos + '\'' +
                '}';
    }

    //Añadir

    public void addJugador(String item1, String item2, String item3) {
        todosJugadores.add(new Jugador(item1, item2, item3, this));
    }

    public void addJugadorObjeto(Jugador item) {
        todosJugadores.add(item);
    }

    public void menuFichar() {//
        boolean val = true;
        boolean val2 = true;
        int i = 0;
        String nombrejugador;
        String apellidojugador;
        String clasejugador;
        int longitud;
        String copia;
        // Comprobacion de nombre
        System.out.println("Introduzca el nombre del jugador (no se admiten ni espacios ni numeros)");
        nombrejugador = Gestion.sacarYcomprobarString();

        //Comprobacion apellido
        do {
            val = true;
            System.out.println("Introduzca el Apellido de " + nombrejugador + " (no se admiten numeros)");
            apellidojugador = lector.nextLine();
            if (apellidojugador == null || apellidojugador.equals("")) { // Si esta vacio el string, te hace repetir el nombre
                val = false;
                System.out.println("Introduzca algun contenido.");
            } else {
                i = 0;
                longitud = apellidojugador.length();
                do {
                    val2 = false;
                    copia = apellidojugador.charAt(i) + ""; // Pilla 1 caracter del nombre
                    try { // Convierte el String a Double
                        Double.parseDouble(copia);
                    } catch (NumberFormatException nfe) {
                        // SI DA ERROR significa que el caracter introducido es una letra, lo cual
                        // significa que esta bien
                        val2 = true;
                    }
                    // SI NO DA ERROR significa que el caracter es un numero o un espacio en blanco,
                    // lo cual implica que el nombre introducido no es valido y por ende, pregunta
                    // otra vez el nombre.
                    if (!val2) {
                        System.out.println("No se permiten numeros en el apellido");
                        val = false;
                        val2 = false;
                    }
                    i++;
                } while (val2 && i < longitud);
            }
        } while (!val);
        System.out.println("Introduzca la clase de " + nombrejugador);
        clasejugador = lector.nextLine();
        this.addJugador(nombrejugador, apellidojugador, clasejugador);
    }

    //Eliminar

    public void rmJugador() {
        int idJugador;
        int longitud;
        longitud = todosJugadores.size();
        //Comprueba que haya al menos 1 jugador en el equipo
        if (longitud > 0) {
            System.out.println("Introduzca el id del jugador");
            idJugador = Gestion.sacarYcomprobarNumero(0, longitud);
            //Saca el id del jugador
            System.out.println("Jugador " + todosJugadores.get(idJugador).getNombre() + " ha sido eliminad@");

            todosJugadores.remove(idJugador);
        } else {
            System.out.println("Este Equipo no tiene jugadores a eliminar");
        }
    }

    public void rmAllJugador() {
        todosJugadores.removeAll(todosJugadores);
    }

    //Mostrar

    public void mostrarJugadores() {
        int longitud;
        longitud = todosJugadores.size();
        for (int j = 0; j < longitud; j++) {
            System.out.println(j + " --> " + todosJugadores.get(j).getNombre());
        }
    }
}
