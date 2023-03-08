import java.util.ArrayList;
import java.util.Scanner;

public class Liga {
    private String nombre;
    private ArrayList<Equipo> todosEquipos = new ArrayList<>();
    Scanner lector = new Scanner(System.in);

    public Liga(String nombre) {
        super();
        this.nombre = nombre;
    }

    public Liga() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Equipo> getTodosEquipos() {
        return todosEquipos;
    }

    public void setTodosEquipos(ArrayList<Equipo> todosEquipos) {
        this.todosEquipos = todosEquipos;
    }

    @Override
    public String toString() {
        return "Liga{" +
                "nombre='" + nombre + '\'' +
                "\n todosEquipos=" + todosEquipos +
                "}";
    }

    //Variables usadas en los metodos

    private boolean val;
    private int i = 0;
    private String nombreEquipo;
    private int longitud;
    private int idEquipo;

    //AÑADIR ---------------------------------------------------------------------------------------------
    public void addEquipo(String nombre) {
        todosEquipos.add(new Equipo(nombre));
    }

    public void addJugadorAEquipoNombre(String NEquipo, String nombre, String apellido, String clase) {
        longitud = todosEquipos.size();
        int cont = 0;
        for (int j = 0; j < longitud; j++) {
            if (todosEquipos.get(j).getNombre().equals(NEquipo)) {
                todosEquipos.get(j).addJugador(nombre, apellido, clase);
                cont++;
            }
        }
        if (cont == 0) {
            System.out.println("No se ha encontrado ningun equipo con ese nombre, combruebe lo que ha escrito.");
        }
    }

    public void addJugadorGuiado() { // Abre un menu para añadir un jugador
        int num;
        this.mostrarEquipos();
        System.out.println("Elija id el equipo al que pertenece el jugador");
        longitud = todosEquipos.size();
        num = Gestion.sacarYcomprobarNumero(0, longitud); // Metodo que pide y comprueba el numero introducido
        todosEquipos.get(num).menuFichar(); // Llama al metodo que tiene el menu para fichar.
    }

    public void addEquipoGuiado() {
        System.out.println("Introduzca el nombre del equipo que quiere añadir: ");
        nombreEquipo = Gestion.sacarYcomprobarString();
        todosEquipos.add(new Equipo(nombreEquipo));
    }

    //MODIFICAR ----------------------------------------------------------------------------

    public void modEquipoGuiado() {
        int num;
        this.mostrarEquipos();
        System.out.println("Elija id del equipo que quiere modificar");
        longitud = todosEquipos.size();
        num = Gestion.sacarYcomprobarNumero(0, longitud);
        System.out.println("Introduzca el nuevo nombre del equipo: ");
        nombreEquipo = Gestion.sacarYcomprobarString();
        todosEquipos.get(num).setNombre(nombreEquipo);
        System.out.println("Nombre cambiado con Exito");
    }

    public void modJugadorEquipo() {
        int selector;
        boolean veri = true;
        Jugador jSeleccionado;
        longitud = todosEquipos.size();
        jSeleccionado = this.seleccionarJugador();
        do {
            System.out.println("Seleccione lo que quiere hacer al jugador: " + jSeleccionado.getNombre());
            System.out.println("1. Modificar todo un jugador");
            System.out.println("2. Modificar el Nombre");
            System.out.println("3. Modificar el Apellido");
            System.out.println("4. Modificar la clase");
            System.out.println("5. Salir\n");
            selector = Gestion.sacarYcomprobarNumero(1, 5);

            switch (selector) {
                case 1:
                    jSeleccionado.setNombre(this.sacarYcomprobarDatosJugador("Nombre", jSeleccionado));
                    jSeleccionado.setApellido(this.sacarYcomprobarDatosJugador("Apellido", jSeleccionado));
                    jSeleccionado.setClase(this.sacarYcomprobarDatosJugador("Clase", jSeleccionado));
                    break;
                case 2:
                    jSeleccionado.setNombre(this.sacarYcomprobarDatosJugador("Nombre", jSeleccionado));
                    break;
                case 3:
                    jSeleccionado.setApellido(this.sacarYcomprobarDatosJugador("Apellido", jSeleccionado));
                    break;
                case 4:
                    jSeleccionado.setClase(this.sacarYcomprobarDatosJugador("Clase", jSeleccionado));
                    break;
                case 5:
                    veri = false;
                    break;
            }
        } while (veri);

    }

    public void cambioDeEquipo() {
        int idJugador;
        int idEquipoTransfer;
        int equipoOG = 0;
        this.mostrarJugadores();
        System.out.println("Introduzca el id del Equipo");
        equipoOG = Gestion.sacarYcomprobarNumero(0, todosEquipos.size());
        longitud = todosEquipos.get(equipoOG).getTodosJugadores().size();
        if (longitud > 0) {

            System.out.println("Introduzca el id del jugador");
            idJugador = Gestion.sacarYcomprobarNumero(0, longitud);

            this.mostrarEquipos();
            longitud = todosEquipos.size();
            System.out.println("Introduzca el id del Equipo al que quiere ser transferido");
            idEquipoTransfer = Gestion.sacarYcomprobarNumero(0, longitud);


            System.out.println("El jugador: " + todosEquipos.get(equipoOG).getTodosJugadores().get(idEquipo).getNombre() + " a sido transferido al Equipo: " + todosEquipos.get(idEquipoTransfer).getNombre());
            todosEquipos.get(idEquipoTransfer).addJugadorObjeto(todosEquipos.get(equipoOG).getTodosJugadores().get(idJugador));
            todosEquipos.get(equipoOG).getTodosJugadores().remove(idJugador);
        } else {
            System.out.println("Este Equipo no tiene Jugadores, operacion cancelada.");
        }
    }

    //MOSTRAR ---------------------------------------------------------------------------------

    //Equipos
    public void mostrarEquipos() {
        longitud = todosEquipos.size();
        for (int j = 0; j < longitud; j++) {
            System.out.println(j + " --> " + todosEquipos.get(j).getNombre());
        }
    }

    // Jugadores
    public void mostrarJugadores() {
        longitud = todosEquipos.size();

        for (int j = 0; j < longitud; j++) {

            System.out.println("*************");
            System.out.println("Equipo:");
            System.out.println(j + " ----> " + todosEquipos.get(j).getNombre() + "\n");
            System.out.println("Jugadores:");
            todosEquipos.get(j).mostrarJugadores();
            System.out.println("*************\n");
        }
    }


    public void mostrarJugadoresUnoaUno() {
        longitud = todosEquipos.size();
        int longitud2;
        for (int j = 0; j < longitud; j++) {
            longitud2 = todosEquipos.get(j).getTodosJugadores().size();
            for (int k = 0; k < longitud2; k++) {
                System.out.println(j + " - " + todosEquipos.get(j).getTodosJugadores().get(k).toString());
            }
        }
    }

    //ELIMINAR------------------------------------------------------------------------------------

    //Equipo
    public void rmEquipo() {
        int idEquipo;
        longitud = todosEquipos.size();
        System.out.println("----------------------");
        for (int j = 0; j < longitud; j++) {
            System.out.println("Equipo:");
            System.out.println(j + " --> " + todosEquipos.get(j).getNombre() + "\n");
            System.out.println("Jugadores:");
            todosEquipos.get(j).mostrarJugadores();
            System.out.println(" ");
            System.out.println("----------------------");
        }
        System.out.println("Introduzca el id del Equipo");
        idEquipo = Gestion.sacarYcomprobarNumero(0, longitud);
        System.out.println("Equipo " + todosEquipos.get(idEquipo).getNombre() + " ha sido eliminad@");
        todosEquipos.get(idEquipo).rmAllJugador();
        todosEquipos.remove(idEquipo);
        this.mostrarEquipos();
    }

    //Jugador

    public void rmJugadorDeEquipo() {

        this.mostrarJugadores();
        longitud = todosEquipos.size();
        System.out.println("Introduzca el id del Equipo");
        idEquipo = Gestion.sacarYcomprobarNumero(0, longitud);
        todosEquipos.get(idEquipo).rmJugador();
    }

    //OTROS ----------------------------------------------------------------------------------

    private String sacarYcomprobarDatosJugador(String dato, Jugador item) { // Das lo que quieres cambiar y el objeto jugador que quieres cambiar
        String nombrejugador;

        boolean val2;
        do {
            val = true;
            System.out.println("Introduzca el " + dato + " del jugador " + item.getNombre() + "(no se admiten ni espacios ni numeros)");
            nombrejugador = lector.nextLine();
            if (nombrejugador == null || nombrejugador.equals("")) { // Si esta vacio el string, te hace repetir el nombre
                val = false;
                System.out.println("Introduzca algun contenido.");
            } else {
                val2 = false;
                //Comprueba si solo son letras
                if (nombrejugador.matches("[a-zA-Z]+")) {
                    val2 = true;
                }
                if (!val2) {
                    System.out.println("Solo se permiten letras");
                    val = false;
                }
            }
        } while (!val);
        return nombrejugador;
    }

    private Jugador seleccionarJugador() { // Metodo que te da a elegir un jugador y devuelve un objeto Jugador
        int idjugador;
        int longitud2;
        longitud = todosEquipos.size();

        //Total tiene la suma de todos los jugadores en todos los equipos
        this.mostrarJugadores();
        System.out.println("Seleccione un id de Equipo");
        idEquipo = Gestion.sacarYcomprobarNumero(0, longitud);
        //Muestra todos los jugadores juntos en una lista
        longitud2 = todosEquipos.get(idEquipo).getTodosJugadores().size() - 1;
        System.out.println("Seleccione un id de jJugador");
        idjugador = Gestion.sacarYcomprobarNumero(0, longitud2);
        //Selecciona al el id del jugador preferido

        return todosEquipos.get(idEquipo).getTodosJugadores().get(idjugador);
    }
}
