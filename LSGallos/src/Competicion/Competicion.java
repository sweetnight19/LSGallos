package Competicion;

import Battle.Score;
import Battle.Threads;
import Rapper.Rapper;
import Themes.Theme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Competicion {
    private String name;
    private String startDate;
    private String endDate;
    private ArrayList<phases> phases;
    private int status;
    private boolean finish;
    private final ArrayList<Threads> threads = new ArrayList<>();

    public Competicion() {
        status = 1;
        setFinish(false);
    }

    /**
     * @return (boolean) Si es ganador o no
     */
    public boolean isFinish() {
        return finish;
    }

    /**
     * @param finish (boolean) para indicar que rapero es el ganador
     */
    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    /**
     * @return (String) Devuelve el nombre de la competicion
     */
    public String getName() {
        return name;
    }

    /**
     * @return (int) Devuelve el estado actual de la competicion
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status (int) Indicar a la competicion, en que estado estamos
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return (String) Devuelve la fecha de inicio de la competicion
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @return (String) Devuelve la fecha de finalizacion de la competicion
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @return (int) Devuelve el numero de fases de la competicion
     */
    public int getPhasesCount() {
        return phases.size();
    }

    /**
     * @param rapper      (ArrayList<Rapper>) Conjunto de raperos
     * @param themList    (ArrayList<Theme>) Conjunto de rimas
     * @param type        (String) Modalidad de la batalla
     * @param contrincant (int) indicador de quien es el rival dentro del conjunto
     *                    de raperos
     * @param name        (String) Nombre del ususario con el que se ha logeado a la
     *                    competicion
     */
    public void batallaInicial(ArrayList<Rapper> rapper, ArrayList<Theme> themList, String type, int contrincant,
                               String name) {
        Random random = new Random();
        int primer, topic;
        double puntuacio1, puntuacio2;
        String rima;
        Scanner scanner = new Scanner(System.in);
        Score score = new Score();

        primer = random.nextInt(2);
        topic = random.nextInt(themList.size());
        puntuacio1 = 0;
        puntuacio2 = 0;

        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Topic: " + themList.get(topic).getName());
        System.out.println();

        if (primer == 1) { // Empieza el usuario
            // 1
            System.out.println("A coin is tossed in the air and....\n" + name + " ,it's your turn! Drop it!");
            System.out.println("\nYour turn!\nEnter your verse: ");
            rima = scanner.nextLine();
            puntuacio1 += score.countRhymes(rima, type);
            // 2
            System.out.println();
            System.out.println(rapper.get(contrincant).getStageName() + " :");
            try {
                System.out.println(themList.get(topic).getRhymes(rapper.get(contrincant).getLevel() - 1, 0));
                rima = themList.get(topic).getRhymes(rapper.get(contrincant).getLevel() - 1, 0);
                puntuacio2 += score.countRhymes(rima, type);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Me quedado en blanco...");
            }

            // 3
            System.out.println();
            System.out.println("Your turn!\nEnter your verse: ");
            rima = scanner.nextLine();
            puntuacio1 += score.countRhymes(rima, type);

            // 4
            System.out.println();
            System.out.println(rapper.get(contrincant).getStageName() + " :");
            try {
                System.out.println(themList.get(topic).getRhymes(rapper.get(contrincant).getLevel() - 1, 1));
                rima = themList.get(topic).getRhymes(rapper.get(contrincant).getLevel() - 1, 1);
                puntuacio2 += score.countRhymes(rima, type);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Me quedado en blanco...");
            }

        } else { // Empieza el contricante
            // 1
            System.out.println("A coin is tossed in the air and....\n" + rapper.get(contrincant).getStageName()
                    + " ,it's your turn! Drop it!");
            System.out.println();
            try {
                System.out.println(themList.get(topic).getRhymes(rapper.get(contrincant).getLevel() - 1, 0));
                rima = themList.get(topic).getRhymes(rapper.get(contrincant).getLevel() - 1, 0);
                puntuacio2 += score.countRhymes(rima, type);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Me quedado en blanco...");
            }

            // 2
            System.out.println();
            System.out.println("Your turn!\nEnter your verse: ");
            rima = scanner.nextLine();
            puntuacio1 += score.countRhymes(rima, type);

            // 3
            System.out.println();
            System.out.println(rapper.get(contrincant).getStageName() + " :");
            try {
                System.out.println(themList.get(topic).getRhymes(rapper.get(contrincant).getLevel() - 1, 1));
                rima = themList.get(topic).getRhymes(rapper.get(contrincant).getLevel() - 1, 1);
                puntuacio2 += score.countRhymes(rima, type);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Me quedado en blanco...");
            }

            // 4
            System.out.println("Your turn!\nEnter your verse: ");
            rima = scanner.nextLine();
            puntuacio1 += score.countRhymes(rima, type);
        }

        // Actualizamos la puntuacion
        rapper.get(contrincant).setScore(puntuacio2);

        for (int i = 0, flag = 0; i < rapper.size() && flag == 0; i++) {
            if (rapper.get(i).getStageName().equals(name)) {
                rapper.get(i).setScore(puntuacio1);
                flag = 1;
            }
        }
    }

    /**
     * @param rapper     (ArrayList<Rapper>) Conjunto de raperos
     * @param name       (String) Nombre del ususario con el que se ha logeado a la
     *                   competicion
     * @param contricant (int) Indicador de quien es el rival dentro del conjunto de
     *                   raperos
     * @param themList   (ArrayList<Theme>) Conjunto de rimas
     * @param type       (String) Modalidad de la batalla
     */
    public void getFase(ArrayList<Rapper> rapper, String name, int contricant, ArrayList<Theme> themList, String type) {
        int usuario, aux;
        int[] players;
        Random random = new Random();

        usuario = -1;

        // Buscamos el numero que corresponde al usuario
        for (int i = 0, flag = 0; i < rapper.size() && flag == 0; i++) {
            if (rapper.get(i).getStageName().equals(name)) {
                usuario = i;
                flag = 1;
            }
        }

        // Comprovamos el array y descartamos en el caso que sea impar
        // el numero de raperos

        if (rapper.size() % 2 == 0) {
            players = new int[rapper.size()];
        } else {
            players = new int[rapper.size() - 1];
        }

        // Inicializamos el array a -1 para tener una referencia
        Arrays.fill(players, -1);

        // Asignamos el emparejamiento
        players[usuario] = contricant;
        players[contricant] = usuario;
        System.out.println();

        for (int i = 0; i < players.length; i++) {
            do {
                aux = random.nextInt(players.length);
                if (players[i] == -1 && players[aux] == -1 && i != usuario && i != contricant) {
                    players[i] = aux;
                    players[aux] = i;
                }
            } while (players[i] == -1);
        }

        // Lanzamos los threadas
        for (int i = 0; i < players.length / 2; i++) {
            if (i != usuario && i != contricant) {
                threads.add(new Threads(rapper.get(i), rapper.get(players[i]), themList, type));
                threads.get(threads.size() - 1).run();
            }

        }

    }

    /**
     * @param rapper (ArrayList<Rapper>) Conjunto de raperos
     * @return (ArrayList < Rapper >) Devuelve el conjunto de raperos despues de sus
     * batallas respectivas
     */
    public ArrayList<Rapper> getResult(ArrayList<Rapper> rapper) throws InterruptedException {
        Rapper rapero1, rapero2;
        for (int i = 0; i < threads.size(); i++) {
            threads.get(i).join();
            rapero1 = threads.get(i).getRapero1();
            rapero2 = threads.get(i).getRapero2();
            for (int j = 0, flag = 0; j < rapper.size() && flag == 0; j++) {
                if (rapper.get(j).getStageName().equals(rapero1.getStageName())) {
                    rapper.remove(j);
                    rapper.add(rapero1);
                    flag = 1;
                }
            }
            for (int j = 0, flag = 0; j < rapper.size() && flag == 0; j++) {
                if (rapper.get(j).getStageName().equals(rapero2.getStageName())) {
                    rapper.remove(j);
                    rapper.add(rapero2);
                    flag = 1;
                }
            }
        }
        System.out.println();
        return rapper;
    }

    /**
     * @param rapper      (ArrayList<Rapper>) Conjunto de raperos
     * @param countPhases (int) Numero de fases que tiene la competicion
     * @param status      (int) fase actual de la competicion
     */
    public static void eliminarRaperos(ArrayList<Rapper> rapper, int countPhases, int status, String name,
                                       Competicion competicion) {
        // Ultima fase
        if ((countPhases == 2 && status == 2) || (countPhases == 3 && status == 3)) {
            for (int i = rapper.size() - 1; rapper.size() > 2; i--) {
                if (rapper.get(i).getStageName().equals(name)) {
                    competicion.setFinish(true);
                    rapper.get(0).setWinner(true);
                }
                rapper.remove(i);
            }

        } else {
            // Penultima fase
            for (int i = rapper.size() - 1, count = rapper.size() / 2; rapper.size() > count; i--) {
                if (rapper.get(i).getStageName().equals(name)) {
                    competicion.setFinish(true);
                    rapper.get(0).setWinner(true);
                }
                rapper.remove(i);
            }

        }
    }
}
