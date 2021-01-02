package Battle;

import java.util.ArrayList;
import java.util.Random;
import Rapper.Rapper;
import Themes.Theme;

public class Threads implements Runnable {
    private Rapper rapper1;
    private Rapper rapper2;
    private ArrayList<Theme> themes;
    private String type;

    public Threads(Rapper rapper1, Rapper rapper2, ArrayList<Theme> themes, String type) {
        this.rapper1 = rapper1;
        this.rapper2 = rapper2;
        this.themes = themes;
        this.type = type;
    }

    @Override
    public void run() {
        // TODO: check score
        Random random = new Random();
        int topic, puntuacio1, puntuacio2;
        String rima;
        Score score = new Score();

        topic = random.nextInt(themes.size());
        puntuacio1 = 0;
        puntuacio2 = 0;

        // 1
        try {
            rima = themes.get(topic).getRhymes(rapper1.getLevel() - 1, 0);
            puntuacio1 += score.countRhymes(rima, type);
        } catch (IndexOutOfBoundsException e) {
        }

        // 2
        try {
            rima = themes.get(topic).getRhymes(rapper2.getLevel() - 1, 0);
            puntuacio2 += score.countRhymes(rima, type);
        } catch (IndexOutOfBoundsException e) {
        }

        // 3
        try {
            rima = themes.get(topic).getRhymes(rapper1.getLevel() - 1, 0);
            puntuacio1 += score.countRhymes(rima, type);
        } catch (IndexOutOfBoundsException e) {
        }

        // 4
        try {
            rima = themes.get(topic).getRhymes(rapper2.getLevel() - 1, 0);
            puntuacio2 += score.countRhymes(rima, type);
        } catch (IndexOutOfBoundsException e) {
        }

        // Actualizamos la puntuacion
        rapper1.setScore(puntuacio1);
        rapper2.setScore(puntuacio2);

    }

    public Rapper getRapero1() {
        return rapper1;
    }

    public Rapper getRapero2() {
        return rapper2;
    }
}