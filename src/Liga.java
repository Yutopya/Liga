import java.util.ArrayList;
import java.util.Scanner;

public class Liga {
    private String nombre;
    private ArrayList<Equipo> todosEquipos = new ArrayList<Equipo>();
    private ArrayList<Jugador> todosJugadores = new ArrayList<Jugador>();
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

    public ArrayList<Jugador> getTodosJugadores() {
        return todosJugadores;
    }

    public void setTodosJugadores(ArrayList<Jugador> todosJugadores) {
        this.todosJugadores = todosJugadores;
    }

    @Override
    public String toString() {
        return "Liga{" +
                "nombre='" + nombre + '\'' +
                ", todosEquipos=" + todosEquipos +
                ", todosJugadores=" + todosJugadores +
                '}';
    }

    //AÑADIR ---------------------------------------------------------------------------------------------
    public void addEquipo(String nombre) {
        todosEquipos.add(new Equipo(nombre));
    }

    //Pide el nombre, comprueba que sea correcto (no espacios ni numeros) y crea un equipo con ese nombre
    public void addEquipoGuiado() {
        String nombreEquipo;
        System.out.println("Introduzca el nombre del equipo que quiere añadir: ");
        nombreEquipo = Gestion.sacarYcomprobarString();
        todosEquipos.add(new Equipo(nombreEquipo));
    }

    //Dado un objeto de tipo jugador y el id del equipo, se añade ese jugador a ese equipo
    public void addJugador(Jugador item, int idEquipo) {
        todosEquipos.get(idEquipo).addJugador(item);
    }

    //Dados datos de jugador, añade un jugador con esos datos
    public void addJudador(String nombre, String apellido, String clase) {
        todosJugadores.add(new Jugador(nombre, apellido, clase));
    }

    //Dado los datos de un jugador, se añade un jugador con esos datos y tambien al equipo correspondiente.
    public void addJugador(String NEquipo, String nombre, String apellido, String clase) {

        //Añade el jugador a la lista de todos los jugadores
        todosJugadores.add(new Jugador(nombre, apellido, clase));
        int lastJugador = todosJugadores.size() - 1;
        int longitud;
        //Le añade el nombre del equipo al jugador elegido
        todosJugadores.get(lastJugador).setEQ(NEquipo);
        longitud = todosEquipos.size();
        for (int j = 0; j < longitud; j++) {
            //Buscamos al equipo y cuando lo encuentra le añadimos el ultimo jugador añadiddo a la lista de jugadores
            if (todosEquipos.get(j).getNombre().equals(NEquipo)) {
                this.addJugador(todosJugadores.get(lastJugador), j);
            }
        }
    }

    //Abre un menu que pide los datos de un jugador y lo añade a la lista, tambien al equipo correspondiente si es así deseado.
    public void addJugadorGuiado() { // Abre un menu para añadir un jugador
        boolean val;
        boolean val2;
        boolean YoN;
        int i;
        int num;
        String nombrejugador;
        String apellidojugador;
        String clasejugador;
        int longitud;
        String copia;
        int lastJugador = todosJugadores.size();

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

        //Elije la clase del jugador y lo crea
        System.out.println("Introduzca la clase de " + nombrejugador);
        clasejugador = lector.nextLine();
        todosJugadores.add(new Jugador(nombrejugador, apellidojugador, clasejugador));
        if (todosEquipos.size() > 0) {
            //Añade el jugador a un equipo a seleccion del usuario
            System.out.println("¿Desea añadir el jugador creado a un equipo?");
            YoN = Gestion.sacarYcomprobarYoN();
            if (YoN) {
                //El usuario elije el id del equipo al que quiere añadir el jugador
                this.mostrarEquiposYPlantilla();
                System.out.println("Elija id el equipo al que pertenece el jugador");
                longitud = todosEquipos.size();
                num = Gestion.sacarYcomprobarNumero(0, longitud);

                //Se asigna el equipo de ese jugador al elegido y se añade ese objeto jugador al equipo correspondiente.
                todosJugadores.get(lastJugador).setEQ(todosEquipos.get(num).getNombre());
                todosEquipos.get(0).addJugador(todosJugadores.get(lastJugador));
            } else {
                System.out.println("Si desea insertarlo a un equipo, debe entrar a Equipo ---> Añadir jugador");
            }
        } else {
            System.out.println("No hay Equipos creados, si desea añadir este jugador a un equipo, cree el equipo y transfiera el jugador a su equipo preferido");
        }
    }

    //Menu para añadir un jugador sin equipo asignado
    public void ficharJugador() {
        int idJugador;
        int longitudEQ = todosEquipos.size();
        int longitudJuga = todosJugadores.size();
        int idEquipo = 0;

        this.mostrarEquipos();

        //Comprueba que haya equipos
        if (longitudEQ > 0) {

            //Selecciona el equipo al que va a ser añadido el jugador
            System.out.println("Seleccione un equipo");
            idEquipo = Gestion.sacarYcomprobarNumero(0, longitudEQ);

            //Comprueba que haya jugadores
            if (longitudJuga > 0) {
                this.mostrarJugadores();

                //Da a seleccionar un jugador
                System.out.println("Seleccione el jugador que quiere añadir a ese equipo");
                idJugador = Gestion.sacarYcomprobarNumero(0, longitudJuga);

                //Comprueba que el jugador seleccionado NO tenga un equipo asignado
                if (todosJugadores.get(idJugador).getEQ() == null) {
                    todosJugadores.get(idJugador).setEQ(todosEquipos.get(idEquipo).getNombre());
                    todosEquipos.get(idEquipo).getplantilla().add(todosJugadores.get(idJugador));
                } else {
                    System.out.println("Este jugador ya pertenece a un equipo");
                }
            } else {
                System.out.println("No hay jugadores creados.");
            }
        }
    }
//MODIFICAR ----------------------------------------------------------------------------

    //Muestra un menu de modificación de equipo (el nombre) y se lo cambia a los jugadores pertenecientes a ese equipo
    public void modEquipoGuiado() {
        int num;
        String nombreEquipo;
        int longitudEQ = todosEquipos.size();
        int longitudjug;

        this.mostrarEquipos();
        if (longitudEQ > 0) {
            //Muestra los equipos y le da a elegir
            System.out.println("Elija id del equipo que quiere modificar");
            num = Gestion.sacarYcomprobarNumero(0, longitudEQ);

            //Una vez elegido el equipo, se pide al usuario que introduzca el nuevo nombre (no espacios ni numeros)
            System.out.println("Introduzca el nuevo nombre del equipo: ");
            nombreEquipo = Gestion.sacarYcomprobarString();

            //Ahora que tenemos el nombre antiguo y nuevo del equipo, el metodo busca los Jugadores con ese nombre
            // y se lo cambia por el nuevo ANTES de asignarle el nuevo nombre al equipo.
            longitudjug = todosJugadores.size();
            String nAnterior = todosEquipos.get(num).getNombre();
            for (int i = 0; i < longitudjug; i++) {
                if (todosJugadores.get(i).getEQ().equals(nAnterior)) {
                    todosJugadores.get(i).setEQ(nombreEquipo);
                }
            }
            todosEquipos.get(num).setNombre(nombreEquipo);
            System.out.println("Nombre cambiado con Exito");
        }
    }


    //Muestra menu en el cual, despues de seleccionar un jugador, te muestra opciones que hay para modificarle
    //Se modifica tanto el jugador que esta en la plantilla de su equipo, como el jugador que esta en la lista todosJugadores
    //AQUI NO SE CAMBIA DE EQUIPO, ESO SE HACE EN EL MENU DE TRANSFERIR
    public void modJugador() {
        int selector;
        int longitudJuga = todosJugadores.size();
        boolean veri = true;
        Jugador jSeleccionado;
        Jugador jPlantilla;
        String NjGeneral;
        String EQGeneral;
        String clasejugador;

        if (longitudJuga > 0) {
            //LLama al metodo seleccionar jugador
            jSeleccionado = this.seleccionarJugador();

            //Despues de conseguir el Jugador deseado, usamos sus datos para encontrar su version que esta metida en un equipo
            NjGeneral = jSeleccionado.getNombre();
            EQGeneral = jSeleccionado.getEQ();
            jPlantilla = this.sacarJugadorItem(NjGeneral, EQGeneral);

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
                        System.out.println("Introduzca la nueva clase de " + jSeleccionado.getNombre());
                        clasejugador = lector.nextLine();
                        jSeleccionado.setClase(clasejugador);
                        break;
                    case 2:
                        jSeleccionado.setNombre(this.sacarYcomprobarDatosJugador("Nombre", jSeleccionado));
                        break;
                    case 3:
                        jSeleccionado.setApellido(this.sacarYcomprobarDatosJugador("Apellido", jSeleccionado));
                        break;
                    case 4:
                        System.out.println("Introduzca la nueva clase de " + jSeleccionado.getNombre());
                        clasejugador = lector.nextLine();
                        jSeleccionado.setClase(clasejugador);
                        break;
                    case 5:
                        veri = false;
                        break;
                }
                if (jPlantilla != null) {
                    jPlantilla.setNombre(jSeleccionado.getNombre());
                    jPlantilla.setApellido(jSeleccionado.getApellido());
                    jPlantilla.setClase(jSeleccionado.getClase());
                }
            } while (veri);
        } else {
            System.out.println("No se pueden modificar jugadores si no hay jugadores.");
        }
    }

    //Transfiere un jugador de un equipo a otro, si hay suficientes jugadores y equipos.
    public void cambioDeEquipo() {
        int idJugador;
        int idEquipoTransfer;
        int equipoOG;
        int longitudEQ = todosEquipos.size();
        int longitudPlantilla;
        int longitudJuga = todosJugadores.size() - 1;
        int idEquipo = 0;
        Jugador nJugador = null;

        //Muestra y da a elegir un equipos
        this.mostrarEquiposYPlantilla();
        System.out.println("Introduzca el id del Equipo");
        equipoOG = Gestion.sacarYcomprobarNumero(0, todosEquipos.size());
        longitudPlantilla = todosEquipos.get(equipoOG).getplantilla().size();

        //Primero se comprueba que haya mas de 2 Equipos
        if (longitudEQ > 1) {

            //Luego se comprueba que el equipo seleccionado tenga al menos 1 jugador
            if (longitudPlantilla > 0) {

                //Una vez elegido el equipo, se elije el jugador que se quiere transferir
                System.out.println("Introduzca el id del jugador");
                idJugador = Gestion.sacarYcomprobarNumero(0, longitudPlantilla);

                //Despues, se elije el equipo al que va a ser transferido el jugador seleccionado
                this.mostrarEquipos();
                longitudPlantilla = todosEquipos.size();
                System.out.println("Introduzca el id del Equipo al que quiere ser transferido");
                idEquipoTransfer = Gestion.sacarYcomprobarNumero(0, longitudPlantilla);

                //Se añade el jugador al nuevo equipo y despues se elimina del anterior
                System.out.println("El jugador: " + todosEquipos.get(equipoOG).getplantilla().get(idEquipo).getNombre() + " a sido transferido al Equipo: " + todosEquipos.get(idEquipoTransfer).getNombre());
                todosEquipos.get(idEquipoTransfer).addJugador(todosEquipos.get(equipoOG).getplantilla().get(idJugador));
                nJugador = todosEquipos.get(equipoOG).getplantilla().get(idJugador);
                for (int i = 0; i <= longitudJuga; i++) {
                    if (todosJugadores.get(i).equals(nJugador)) {
                        todosJugadores.get(i).setEQ(todosEquipos.get(idEquipoTransfer).getNombre());
                    }
                }
                todosEquipos.get(equipoOG).getplantilla().remove(idJugador);
            } else {
                System.out.println("Este Equipo no tiene Jugadores, operacion cancelada.");
            }
        } else {
            System.out.println("No hay suficientes Equipos para hacer una transferencia.");
        }
    }

//MOSTRAR ---------------------------------------------------------------------------------

    //Equipos

    //Muestra solo los equipos
    public void mostrarEquipos() {
        int longitud;
        longitud = todosEquipos.size();

        //Comprueba que haya al menos 1 Equipo creado
        if (longitud > 0) {
            for (int j = 0; j < longitud; j++) {
                System.out.println(j + " --> " + todosEquipos.get(j).getNombre());
                System.out.println("\n");
            }
        } else {
            System.out.println("No hay Equipos creados.");
        }
    }

    //Muestra los equipos y sus plantillas
    public void mostrarEquiposYPlantilla() {
        int longitudEQ = todosEquipos.size();

        //Comprueba que haya al menos 1 Equipo creado
        if (longitudEQ > 0) {
            for (int j = 0; j < longitudEQ; j++) {
                System.out.println("");
                System.out.println(j + " --> " + todosEquipos.get(j).getNombre());
                this.mostrarPlantilladeEquipo(j);
                System.out.println("\n");
            }
        } else {
            System.out.println("No hay Equipos creados.");
        }
    }

    // Jugadores
    public void mostrarJugadores() {
        int longitud;
        longitud = todosJugadores.size();

        //Comprueba que haya al menos 1 jugador creado
        if (longitud > 0) {
            for (int j = 0; j < longitud; j++) {
                System.out.println(j + " --> " + todosJugadores.get(j).toString());
            }
        } else {
            System.out.println("No hay Jugadores creados.");
        }
    }


    public void mostrarPlantilladeEquipo(int idEquipo) {
        int longitud = todosEquipos.get(idEquipo).getplantilla().size();

        //Comprueba que haya jugadores en dicho equipo
        if (longitud > 0) {
            for (int j = 0; j < longitud; j++) {
                System.out.println(j + " ------> " + todosEquipos.get(idEquipo).getplantilla().get(j));
            }
        } else {
            System.out.println("Este equipo no tiene jugadores");
        }
    }

//ELIMINAR------------------------------------------------------------------------------------

    //Equipo

    //Elimina un equipo y asigna NULL al atributo equipo en los jugadores que pertenecian al mismo
    public void rmEquipo() {

        int idEquipo;
        int longitudEQ = todosEquipos.size();
        int longitudJuga;
        String nombreEQ;

        this.mostrarEquiposYPlantilla();
        if (longitudEQ > 0) {
            //selecciona un equipo
            System.out.println("Introduzca el id del Equipo");
            idEquipo = Gestion.sacarYcomprobarNumero(0, longitudEQ);

            //Guarda el nombre del equipo a eliminar en una variable
            nombreEQ = todosEquipos.get(idEquipo).getNombre();
            System.out.println("Equipo " + nombreEQ + " ha sido eliminad@");
            longitudJuga = todosJugadores.size() - 1;

            //Comprueba que haya jugadores
            if (longitudJuga > 0) {
                for (int i = 0; i <= longitudJuga; i++) {

                    //Si encuentra algun jugador con ese equipo asignado, reasigna su equipo a NULL
                    try {
                        if (todosJugadores.get(i).getEQ().equals(nombreEQ)) {
                            todosJugadores.get(i).setEQ(null);
                        }
                    } catch (NullPointerException NPE) {
                    }
                }

                //Se elimina la plantilla de dicho equipo
                todosEquipos.get(idEquipo).rmPlantilla();
            }

            //Por ultimo, se elimina el equipo y se muestran los equipos restantes
            todosEquipos.remove(idEquipo);
            this.mostrarEquiposYPlantilla();
        }

    }

    //Jugador

    //Elimina un jugador de la lista todosJugadores y por lo tanto el objeto jugador equivalente se elimina de todos los equipos
    public void rmJugador() {
        int idJugador;
        int longitudEQ = todosEquipos.size() - 1;
        int longitudJuga = todosJugadores.size();
        int longitudPlant;
        String nJugador;
        String nombreEQ;

        //Selecciona al jugador
        this.mostrarJugadores();
        idJugador = Gestion.sacarYcomprobarNumero(0, longitudJuga);

        //Elimino al jugador pero guardo su nombre y su equipo
        nJugador = todosJugadores.get(idJugador).getNombre();
        nombreEQ = todosJugadores.get(idJugador).getEQ();
        todosJugadores.remove(idJugador);

        //Compruebo si estaba en algun equipo
        if (nombreEQ != null) {
            for (int i = 0; i < longitudEQ; i++) {

                //Encuentro el ID del equipo con el nombre almacenado
                if (todosEquipos.get(i).getNombre().equals(nombreEQ)) {
                    longitudPlant = todosEquipos.get(i).getplantilla().size();

                    //Comprueba si dicho equipo tiene al menos un jugador
                    if (longitudPlant > 0) {
                        for (int j = 0; j < longitudPlant; j++) {

                            //Si encuentra a un jugador con el nombre almacenado previamente, lo elimina del equipo
                            if (todosEquipos.get(i).getplantilla().get(j).getNombre().equals(nJugador)) {
                                todosEquipos.get(i).getplantilla().remove(j);
                            }
                        }
                    }
                }
            }
        }
    }

    //Despide al jugador seleccionado del equipo seleccionado y asigna NULL al atributo equipo del objeto equivalente en la lista todosJugadores
    public void rmJugadorDeEquipo() {
        int longitudEQ = todosEquipos.size();
        int longitudPlantilla;
        int longitudJuga;
        int idEquipo;
        Jugador nJugador;


        this.mostrarEquiposYPlantilla();
        if (longitudEQ > 0) {
            //Selecciona un equipo
            System.out.println("Introduzca el id del Equipo");
            idEquipo = Gestion.sacarYcomprobarNumero(0, longitudEQ);
            longitudPlantilla = todosEquipos.get(idEquipo).getplantilla().size();

            //Comprueba que el equipo elegido tenga al menos 1 Jugador
            if (longitudPlantilla > 0) {
                longitudJuga = todosJugadores.size() - 1;
                //Este metodo, aparte de eliminar el jugador del propio equipo, devuelve un Objeto jugador
                //para poder buscarlo en la lista todosJugadores y poner su equipo NULL
                nJugador = todosEquipos.get(idEquipo).rmJugadordePlantilla();
                for (int i = 0; i <= longitudJuga; i++) {
                    if (todosJugadores.get(i).equals(nJugador)) {
                        todosJugadores.get(i).setEQ(null);
                    }
                }
            } else {
                System.out.println("Este Equipo no tiene jugadores a eliminar");
            }
        }
    }

//OTROS ----------------------------------------------------------------------------------

    //Pide y comprueba un String y lo devuelve (no puede tener ni espacios ni numeros)
    private String sacarYcomprobarDatosJugador(String dato, Jugador item) { // Das lo que quieres cambiar y el objeto jugador que quieres cambiar
        String datojugador;
        boolean val;
        boolean val2;
        do {
            val = true;
            System.out.println("Introduzca el " + dato + " del jugador " + item.getNombre() + "(no se admiten ni espacios ni numeros)");
            datojugador = lector.nextLine();
            if (datojugador == null || datojugador.equals("")) { // Si esta vacio el string, te hace repetir el nombre
                val = false;
                System.out.println("Introduzca algun contenido.");
            } else {
                val2 = false;
                //Comprueba si solo son letras
                if (datojugador.matches("[a-zA-Z]+")) {
                    val2 = true;
                }
                if (!val2) {
                    System.out.println("Solo se permiten letras");
                    val = false;
                }
            }
        } while (!val);

        //Devuelve un String con el dato del jugador
        return datojugador;
    }

    //Pide y devuelve un objeto Jugador, se podría haber usado mas
    private Jugador seleccionarJugador() {
        int idjugador;
        int longitud = todosJugadores.size() - 1;

        this.mostrarJugadores();

        //Te da a elegir un jugador
        System.out.println("Seleccione el jugador");
        idjugador = Gestion.sacarYcomprobarNumero(0, longitud);

        //Devuelve un objeto jugador
        return todosJugadores.get(idjugador);
    }

    // Metodo que te da a elegir un jugador en una plantilla y devuelve un objeto Jugador
    private Jugador seleccionarJugadordePlantilla() {
        int idjugador;
        int longitud = todosEquipos.size() - 1;
        int longitud2;
        int idEquipo;

        //Muestra y Selecciona al el id del equipo preferido
        this.mostrarEquiposYPlantilla();
        System.out.println("Seleccione un id de Equipo");
        idEquipo = Gestion.sacarYcomprobarNumero(0, longitud);

        //Muestra y Selecciona al el id del jugador preferido
        this.mostrarPlantilladeEquipo(idEquipo);
        longitud2 = todosEquipos.get(idEquipo).getplantilla().size() - 1;
        System.out.println("Seleccione un id de jJugador");
        idjugador = Gestion.sacarYcomprobarNumero(0, longitud2);

        return todosEquipos.get(idEquipo).getplantilla().get(idjugador);
    }

    //Dado un nombre de Equipo y un Nombre de jugador, lo busca en la lista todosEquipos y sus plantillas y devuelve el objeto jugador deseado
    private Jugador sacarJugadorItem(String nombreEQ, String nombreJugador) {
        boolean valEQ;
        boolean valJuga;
        int i = 0;
        int j = 0;
        int longitudEQ = todosEquipos.size();
        int longitudJuga;
        int idEquipo = 0;
        Jugador jSeleccionado = null;

        if (longitudEQ > 0) {
            longitudEQ--;
            //Recorre la lista de equipos para sacar el id del equipo con ese nombre
            do {
                valEQ = true;
                if (todosEquipos.get(i).getNombre().equals(nombreEQ)) {
                    idEquipo = i;
                    valEQ = false;
                }
                i++;
            } while (valEQ && i < longitudEQ);

            //Si hay un equipo con ese nombre, procede a buscar el jugador con el nombre proporcionado
            //Recorriendo la plantilla
            if (!valEQ) {
                longitudJuga = todosEquipos.get(idEquipo).getplantilla().size();
                do {
                    valJuga = true;
                    if (todosEquipos.get(idEquipo).getplantilla().get(j).getNombre().equals(nombreJugador)) {
                        jSeleccionado = todosEquipos.get(idEquipo).getplantilla().get(j);
                    }
                } while (valJuga && j < longitudJuga);
            }
        }
        //Devuelve el jugador que coincide con los terminos proporcionados
        return jSeleccionado;
    }
}
