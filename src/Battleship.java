import java.util.Scanner;
import java.util.Random;

public class Battleship {
    private static int T_tablero = 10;
    private static int Agua = 0;
    private static int Barco = 1;
    private static int Tocado = 2;
    private static int Hundido = 3;
    
   /**
    * el juego se basa en un juego de batalla naval en la que el objetivo es hundoir los barcos de tu oponente 
    el juego cuenta con tres tipos de barcos:lancha,barco de municion,barco medico y lancha. en la que 
    debe elegir una posicion entre 0-9 el ganador sera elprimero en hundir todos los barcos y 
    tambien eljugador humano tendra la opcion de seguri jugando
    * @param args
    */


    /**inicio del juego */
    public static void main(String[] args) {
        Scanner Scanner = new Scanner(System.in);
        Random random = new Random();

        int[] jugadorhumano = new int[T_tablero];
        int[] tableroMaquina = new int[T_tablero];

        boolean Jugardenuevo = true;

        while (Jugardenuevo) {
            System.out.println("Bienvenido a Battleship");
            System.out.println("Puedes colocar tus barcos");

            Pcolocarbarco(jugadorhumano, Scanner);
            PcolocarbarcoMaquina(tableroMaquina, random);
            System.out.println("Es el turno de la maquina");


            boolean Sterminaeljuego = false;
            while (!Sterminaeljuego) {
                System.out.println("Es tu turno");
                PDisparar(jugadorhumano, tableroMaquina, Scanner);

                if (VerificacionPartidaGanada(tableroMaquina)) {
                    System.out.println("Genial, has logrado hundir los barcos de tu oponente");
                    Sterminaeljuego = true;
                    continue;

                }
            }
        }
    }
                
    
                

    /*
     * @param tablero del jugador
     * @param Scanner para leer la entrada
     */
    private static void Pcolocarbarco(int[] tablero, Scanner Scanner) {
        System.out.println("Coloca tus barcos");
        int[] T_barcos = {1, 2, 3};
        for (int T_barco : T_barcos) {
            boolean posicionValida = false;
            while (!posicionValida) {
                System.out.println("Indica la posición (0-9) para el barco" + T_barco + " casillas:");
                int Pinicial = Scanner.nextInt();
                int Pfinal = Pinicial + T_barco - 1;
                if (Pinicial >= 0 && Pfinal < T_tablero && !haySuperposicion(tablero, Pinicial, Pfinal)) {
                    for (int i = Pinicial; i <= Pfinal; i++) tablero[i] = Barco;
                    posicionValida = true;
                } else {
                    System.out.println("Posición inválida o superposición. Inténtalo de nuevo.");
                }
            }
        }
    }


    /**
     * @tablero de la maquina
     * @random se genera una posiicion random para la maquina
     */
    private static void PcolocarbarcoMaquina(int[] tablero, Random random) {
        System.out.println("La máquina está colocando sus barcos");
        int[] T_barcos = {1, 2, 3};
        for (int T_barco : T_barcos) {
            boolean posicionValida  = false;
            while (  !posicionValida) {
                int Pinicial = random.nextInt(T_tablero - T_barco + 1);
                int Pfinal = Pinicial + T_barco - 1;
                if (!haySuperposicion(tablero, Pinicial, Pfinal)) {
                    for (int i = Pinicial; i <= Pfinal; i++) tablero[i] = Barco;
                    posicionValida = true;
                }
            }
        }
    }
    /**
     * 
     * @param tablero
     * @param Pinicial posicion  inicial del braco
     * @param Pfinal posicion final del barco
     * @return
     */
    private static boolean haySuperposicion(int[] tablero, int Pinicial, int Pfinal) {
        for (int i = Pinicial; i <= Pfinal; i++) {
            if (tablero[i] != Agua) return true;
        }
        return false;
    }

    /**
     * 
     * @param atacante jugador que ataca al oponente
     * @param defensor jugador que se defendera del otro jugador
     * @param Scanner leer entrada 
     */

    private static void PDisparar(int[] atacante, int[] defensor, Scanner Scanner) {
        boolean DisparoExitoso = false;
        while (!DisparoExitoso) {
            System.out.println("Ingresa una coordenada (0-9) para disparar:");
            int coordenada = Scanner.nextInt();
            if (defensor[coordenada] == Barco) {
                defensor[coordenada] = Tocado;
                defensor[coordenada]=Hundido;
                System.out.println("¡impactaste un barco!");
            } else {
                System.out.println("Agua");
            }
            DisparoExitoso = true;
        }
    }


    /**
     * se verifica quien fue el ganador de la partida
     * @param tablero
     * @return true si todos los barcos han sido hundidos
     */
    private static boolean VerificacionPartidaGanada(int[] tablero) {
        for (int casilla : tablero) {
            if (casilla == Barco) return false;
        }
        return true;
    }
}



        