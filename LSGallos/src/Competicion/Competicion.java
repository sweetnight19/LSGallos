package Competicion;

import Battle.Score;
import Battle.Threads;
import Rapper.Rapper;
import Themes.Theme;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Competicion {
    private String name;
    private String startDate;
    private String endDate;
    private ArrayList<phases> phases;
    private int status;
    private boolean finish;
    private ArrayList<Threads> threads = new ArrayList<Threads>();
    private int[] players;

    public Competicion() {
        status = 1;
        setFinish(false);
    }

    
    /** 
     * @return boolean
     */
    public boolean isFinish() {
        return finish;
    }

    
    /** 
     * @param finish
     */
    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    
    /** 
     * @return String
     */
    public String getName() {
        return name;
    }

    
    /** 
     * @return int
     */
    public int getStatus() {
        return status;
    }

    
    /** 
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    
    /** 
     * @return String
     */
    public String getStartDate() {
        return startDate;
    }

    
    /** 
     * @return String
     */
    public String getEndDate() {
        return endDate;
    }

    
    /** 
     * @return int
     */
    public int getPhasesCount() {
        return phases.size();
    }

    
    /** 
     * @param rapper
     * @param themList
     * @param type
     * @param contrincant
     * @param name
     */
    public void batallaInicial(ArrayList<Rapper> rapper, ArrayList<Theme> themList, String type, int contrincant,
            String name) {
        Random random = new Random();
        int primer, topic, puntuacio1, puntuacio2;
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
            System.out.println();
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
        System.out.println();
    }

    
    /** 
     * @param rapper
     * @param name
     * @param contricant
     * @param themList
     * @param type
     */
    public void getFase(ArrayList<Rapper> rapper, String name, int contricant, ArrayList<Theme> themList, String type) {
        int usuario, aux;
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
        for (int i = 0; i < players.length; i++) {
            players[i] = -1;
        }

        // Asignamos el emparejamiento
        players[usuario] = contricant;
        players[contricant] = usuario;
        for (int i = 0; i < players.length; i++) {
            do {
                aux = random.nextInt(players.length);
                if (players[i] == -1 && aux != i) {
                    players[i] = aux;
                    players[aux] = i;
                }
            } while (aux == i);

        }

        for (int i = 0; i < players.length / 2; i++) {
            threads.add(new Threads(rapper.get(i), rapper.get(players[players[i]]), themList, type));
            threads.get(i).run();
        }
    }

    
    /** 
     * @param rapper
     * @return ArrayList<Rapper>
     */
    public ArrayList<Rapper> getResult(ArrayList<Rapper> rapper) {
        Rapper rapero1, rapero2;
        for (int i = 0; i < threads.size(); i++) {
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
        return rapper;
    }

    
    /** 
     * @param rapper
     * @param countPhases
     * @param status
     */
    public static void eliminarRaperos(ArrayList<Rapper> rapper, int countPhases, int status) {
        // Ultima fase
        if ((countPhases == 2 && status == 2) || (countPhases == 3 && status == 3)) {
            for (int i = rapper.size() - 1; rapper.size() > 2; i--) {
                rapper.remove(i);
            }

        } else {
            // Penultima fase
            for (int i = rapper.size() - 1, count = rapper.size() / 2; rapper.size() > count; i--) {
                rapper.remove(i);
            }

        }
    }
}
