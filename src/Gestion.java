import java.util.ArrayList;
import java.util.Scanner;

public class Gestion {
    private Liga pipoliga = new Liga("pipotruco");

    public void menuInicial() {
        boolean val = true;
        boolean valMenuIni;
        boolean valEliminar;
        int menuGestionlistEquipos;
        int menuGestionJugadores;
        int selectorPrincipal;
        System.out.println("Le damos la bienvenida al programa de creacion de liga");

        do {
            System.out.println("Elija el menu al que quiere acceder:\n");
            System.out.println("1-Gestion de Equipos");
            System.out.println("2-Gestion de Jugadores");
            System.out.println("3-Generar Liga");
            System.out.println("4-Salir del programa");
            selectorPrincipal = sacarYcomprobarNumero(1, 4);

            //Menu principal

            switch (selectorPrincipal) {
                case 1:
                    valMenuIni = true;

                    //Menu Equipos

                    do {
                        System.out.println("Elija una opcion\n");
                        System.out.println("0-Listar Equipos");
                        System.out.println("1-Crear Equipo");
                        System.out.println("2-Modificar Equipo");
                        System.out.println("3-Fichar Jugador");
                        System.out.println("4-Despedir Jugador");
                        System.out.println("5-Eliminar Equipo");
                        System.out.println("6-Insertar Equipos (añade equipos para hacer pruebas)");
                        System.out.println("7-Salir a la selección de menús");
                        menuGestionlistEquipos = sacarYcomprobarNumero(0, 7);
                        System.out.println("\n");
                        switch (menuGestionlistEquipos) {
                            case 0:
                                pipoliga.mostrarEquiposYPlantilla();
                                System.out.println("\n");
                                break;
                            case 1:
                                pipoliga.addEquipoGuiado();
                                System.out.println("\n");
                                break;
                            case 2:
                                pipoliga.modEquipoGuiado();
                                System.out.println("\n");
                                break;
                            case 3:
                                pipoliga.ficharJugador();
                                System.out.println("\n");
                                break;
                            case 4:
                                pipoliga.rmJugadorDeEquipo();
                                System.out.println("\n");
                                break;
                            case 5:
                                System.out.println("Va a eliminar un equipo y desvincular todos sus jugadores del mismo");
                                System.out.println("¿Segur@ que quiere continuar?");
                                valEliminar = sacarYcomprobarYoN();
                                if (valEliminar) {
                                    pipoliga.rmEquipo();
                                } else {
                                    System.out.println("Operacion cancelada.");
                                }
                                System.out.println("\n");
                                break;
                            case 6:
                                //Estos equipos y jugadores estan para que no los tengas que añadir manualmente
                                //cada vez que quieres probar los enfrentamientos
                                crearEquipos();
                                break;
                            case 7:
                                valMenuIni = false;
                                break;
                        }
                    } while (valMenuIni);
                    break;

                case 2:
                    valMenuIni = true;

                    //Menu Jugadores

                    do {
                        System.out.println("Elija una opcion\n");
                        System.out.println("0-Listar Jugadores");
                        System.out.println("1-Crear Jugador");
                        System.out.println("2-Modificar Jugador");
                        System.out.println("3-Eliminar Jugador");
                        System.out.println("4-Transferir Jugador");
                        System.out.println("5-Salir a la selección de menús");
                        menuGestionJugadores = sacarYcomprobarNumero(0, 5);
                        switch (menuGestionJugadores) {
                            case 0:
                                pipoliga.mostrarJugadores();
                                System.out.println("\n");
                                break;
                            case 1:
                                pipoliga.addJugadorGuiado();
                                System.out.println("\n");
                                break;
                            case 2:
                                pipoliga.modJugador();
                                System.out.println("\n");
                                break;
                            case 3:
                                pipoliga.rmJugador();
                                System.out.println("\n");
                                break;
                            case 4:
                                pipoliga.cambioDeEquipo();
                                System.out.println("\n");
                                break;
                            case 5:
                                valMenuIni = false;
                                break;

                        }
                    } while (valMenuIni);
                    break;
                case 3:
                    int k=0;
                    int longitudEQ=pipoliga.getTodosEquipos().size();
                    int longitudLista;
                    int numEquipos;
                    int selectorEquipos;
                    int cantidadEquipos = pipoliga.getTodosEquipos().size();
                    boolean valEquipo;
                    boolean valLista;
                    String nEQ;
                    ArrayList<Equipo> listEquipos = new ArrayList<Equipo>();
                    int numJornadas;
                    String[][] calendario;
                    String[][] resultados;
                    Equipo equipo1;
                    Equipo equipo2;
                    Equipo equipoAux;

                        if(longitudEQ>=3){
                            System.out.println("¿Cuántos equipos van a participar? (Entre 3 y " + cantidadEquipos + ")");
                            numEquipos = sacarYcomprobarNumero(3, cantidadEquipos);
                            pipoliga.mostrarEquipos();
                                for (int i = 1; i < numEquipos + 1; i++) {
                                    do {
                                        valEquipo = false;
                                        System.out.println("\n Introduce el id del equipo " + i + ":");
                                        selectorEquipos = sacarYcomprobarNumero(0, cantidadEquipos - 1);
                                        longitudLista = listEquipos.size();
                                        nEQ = pipoliga.getTodosEquipos().get(selectorEquipos).getNombre();
                                        if (longitudLista <= 0) {
                                            listEquipos.add(pipoliga.getTodosEquipos().get(selectorEquipos));

                                        } else {
                                            k = 0;
                                            valLista=true;
                                            do {
                                                if (nEQ.equals(listEquipos.get(k).getNombre())) {
                                                    System.out.println("No se puede añadir el mismo equipo 2 veces");
                                                    valEquipo = true;
                                                } else {
                                                    listEquipos.add(pipoliga.getTodosEquipos().get(selectorEquipos));
                                                    valLista=false;
                                                }
                                                k++;
                                            } while (valLista && k < longitudLista);
                                        }
                                    } while (valEquipo);
                                }

                        // Calcular el numero de jornadas de la liga en caso de que el numero de equipos
                        // sea PAR

                        if (numEquipos % 2 == 0) {

                            numJornadas = numEquipos - 1; // Mostrar cuantas jornadas hay dado el numero de equipos

                            System.out.println("Con " + numEquipos + " equipos hay " + numJornadas + " jornadas");
                            System.out.println();

                            // Crear la matriz para almacenar la distribución de jornadas
                            calendario = new String[numJornadas][listEquipos.size() / 2];
                            resultados = new String[numJornadas][listEquipos.size() / 2];
                            // Generar la distribución de enfrentamientos
                            for (int i = 0; i < numJornadas; i++) {
                                for (int j = 0; j < listEquipos.size() / 2; j++) {

                                    // Seleccionar los equipos para los enfrentamientos
                                    equipo1 = listEquipos.get(j);
                                    equipo2 = listEquipos.get(listEquipos.size() - j - 1);

                                    // Validar que los equipos seleccionados no se repiten en la misma jornada
                                    if (equipo1.equals(equipo2)) {
                                        equipo2 = listEquipos.get(listEquipos.size() - j - 2);
                                    }
                                    // Añadir el partido a la matriz de jornadas
                                    calendario[i][j] = equipo1.getNombre() + " vs " + equipo2.getNombre();
                                    resultados[i][j] = generarResultado(equipo1, equipo2);
                                    //Aqui es donde se tienen que asignar resultados
                                }
                                // Rotar los equipos para la siguiente jornada
                                equipoAux = listEquipos.remove(1);
                                listEquipos.add(equipoAux);
                            }
                            // Mostrar todas las jornadas ya creadas
                            for (int i = 0; i < numJornadas; i++) {
                                System.out.println("--------------");
                                System.out.println("Jornada " + (i + 1) + ":");
                                System.out.println("--------------\n");
                                for (int j = 0; j < listEquipos.size() / 2; j++) {
                                    System.out.println(calendario[i][j]);
                                    System.out.println(resultados[i][j]);
                                }
                                System.out.println();
                            }
                            //Mostrar la clasificación final de los equipos
                            System.out.println("La clasificación final de la liga ha sido:");
                            Gestion.mostrarResultados(listEquipos);
                            // Calcular el numero de jornadas de la liga en caso de que el numero de equipos
                            // sea IMPAR
                        } else {
                            numJornadas = numEquipos; // Mostrar cuantas jornadas hay dado el numero de equipos
                            System.out.println("Con " + numEquipos + " equipos hay " + numJornadas + " jornadas");
                            System.out.println();

                            // Crear la matriz para almacenar la distribución de jornadas
                            calendario = new String[numJornadas][listEquipos.size() / 2];
                            resultados = new String[numJornadas][listEquipos.size() / 2];
                            // Generar la distribución de enfrentamientos
                            for (int i = 0; i < numJornadas; i++) {
                                for (int j = 0; j < listEquipos.size() / 2; j++) {
                                    // Seleccionar los equipos para los enfrentamientos
                                    equipo1 = listEquipos.get(j);
                                    equipo2 = listEquipos.get(listEquipos.size() - j - 1);
                                    // Validar que los equipos seleccionados no se repiten en la misma jornada
                                    if (equipo1.equals(equipo2)) {
                                        equipo2 = listEquipos.get(listEquipos.size() - j - 2);
                                    }
                                    // Añadir el partido a la matriz de jornadas
                                    calendario[i][j] = equipo1.getNombre() + " vs " + equipo2.getNombre();
                                    resultados[i][j] = generarResultado(equipo1, equipo2);
                                    //Aqui es donde se tienen que asignar resultados
                                }
                                // Rotar los equipos para la siguiente jornada
                                equipoAux = listEquipos.remove(0);
                                listEquipos.add(equipoAux);
                            }
                            // Mostrar todas las jornadas ya creadas
                            for (int i = 0; i < numJornadas; i++) {

                                System.out.println("Jornada " + (i + 1) + ":");

                                for (int j = 0; j < listEquipos.size() / 2; j++) {
                                    System.out.println(calendario[i][j]);
                                    System.out.println(resultados[i][j]);
                                }
                                System.out.println();
                            }
                            //Mostrar la clasificación final de los equipos
                            System.out.println("La clasificación final de la liga ha sido:");
                            Gestion.mostrarResultados(listEquipos);
                        }
                        valEliminar = true;
                        System.out.print("¿Desea eliminar la puntuación? ");
                        valEliminar = sacarYcomprobarYoN();
                        if (valEliminar) {
                            rmPuntuacion();
                        } else {
                            System.out.println("La puntuación de los equipos ha sido mantenida");
                        }
                        System.out.println("\n");
                        }else{
                            System.out.println("No hay suficientes equipos para empezar la liga, el minimo son 3.\n");
                        }
                    break;
                case 4:

                    //Salir
                    System.out.println("Tenga un buen dia uwu");
                    val = false;
                    break;
            }
        } while (val);
    }


//Otros metodos

    // Pregunta por un numero y comprueba si esta entre los numeros introducidos

    public static int sacarYcomprobarNumero(int numeroMin, int numeroMax) {
        int numeroOG = 0;
        boolean valGeneral;
        Scanner lector = new Scanner(System.in);
        do {
            valGeneral = true;
            try {
                numeroOG = lector.nextInt();
            } catch (java.util.InputMismatchException ime) {
                // Comprueba que se haya introducido un numero
                System.out.println("Introduzca solo numeros por favor.");
                valGeneral = false;
            }
            lector.nextLine();
            if (numeroOG < numeroMin || numeroOG > numeroMax) {
                System.out.println("Introduzca un numero valido");
                valGeneral = false;
            }
        } while (!valGeneral);
        return numeroOG;
    }

    // Pide y comprueba que el string no tenga numeros ni espacios

    public static String sacarYcomprobarString() {
        String datos;
        boolean val;
        boolean val2;
        Scanner lector = new Scanner(System.in);
        do {
            val = true;
            datos = lector.nextLine();
            if (datos == null || datos.equals("")) { // Si esta vacio el string, te hace repetir el nombre
                val = false;
                System.out.println("Introduzca algun contenido.");
            } else {
                val2 = false;
                //Comprueba si solo son letras
                if (datos.matches("[a-zA-Z]+")) {
                    val2 = true;
                }
                if (!val2) {
                    System.out.println("Solo se permiten letras");
                    val = false;
                }
            }
        } while (!val);
        return datos;
    }

    //Simple  metodo que pide un caracter Y/N, comprueba que sea alguno de los dos y devuelve un booleano || Y=true, N=false

    public static boolean sacarYcomprobarYoN() {
        Scanner lector = new Scanner(System.in);
        char caracter;
        String cadena;
        boolean comprobante = true;
        boolean comprobanteBucle;
        System.out.println("\033[32m"+"Y"+ "\u001B[0m"+"/"+"\033[31m"+"N"+ "\u001B[0m");
        do {
            //Pide un caracter
            cadena = lector.nextLine();
            cadena = cadena.toUpperCase();
            //Lo para a mayus por seguridad
            caracter = cadena.charAt(0);
            comprobanteBucle = false;

            if (caracter == 'Y') { //Si es y devuelve true
                comprobante = true;
            } else if (caracter == 'N') { //Si es n devuelve false
                comprobante = false;
            } else { // Y si no es ninguno lo repite
                System.out.println("Introduzca un caracter valido");
                comprobanteBucle = true;
            }
        } while (comprobanteBucle);
        return comprobante;
    }

    //Metodo que crea equipos y algunos jugadores para no tener que introducir manualmente todo el rato.

    private void crearEquipos() {
        pipoliga.addEquipo("Pipo");
        pipoliga.addEquipo("RanniTawani");
        pipoliga.addEquipo("RayoVallecano");
        pipoliga.addEquipo("Karma");
        pipoliga.addEquipo("Madrid");
        pipoliga.addEquipo("DescansacionFC");
        pipoliga.addJugador("RanniTawani", "maria", "navarro", "2ºA");
        pipoliga.addJugador("Pipo", "anton", "pedrolo", "1ºB");
        pipoliga.addJugador("RayoVallecano", "papo", "pipopapo", "2ºB");
        pipoliga.addJudador("Geronimo","Francisco","1ºA");
        pipoliga.addJudador("Toad","Pipa","2ºA");
        pipoliga.addJugador("DescansacionFC", "Mimir", "Two", "2ºB");
    }

    //Genera un resultado aleatoria entre 2 equipos
    private static String generarResultado(Equipo equipo1, Equipo equipo2) {

        int numAleatorio = (int) (Math.random() * (1 - 0 + 1));

        if (numAleatorio == 0) {
            equipo1.setPuntos(equipo1.getPuntos() + 1);
            return ("\033[32m" + "El equipo " + equipo1.getNombre() + " ha ganado.\n" + "\u001B[0m");
        } else {
            equipo2.setPuntos(equipo2.getPuntos() + 1);
            return ("\033[32m" + "El equipo " + equipo2.getNombre() + " ha ganado.\n" + "\u001B[0m");
        }
    }

    //Muestra una lista con los Equipos participantes y su puntuación ordenada
    private static void mostrarResultados(ArrayList<Equipo> datos) {

        datos.sort((e1, e2) -> e2.getPuntos() - (e1.getPuntos()));

        for (int i = 0; i < datos.size(); i++) {

            System.out.println(datos.get(i).getNombre() + " - " + datos.get(i).getPuntos());
        }
    }

    //Elimina la puntuacion
    private void rmPuntuacion() {
        int longitudi = pipoliga.getTodosEquipos().size();
        for (int i = 0; i < longitudi; i++) {
            pipoliga.getTodosEquipos().get(i).setPuntos(0);
        }
    }
}