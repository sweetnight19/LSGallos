package Battle;

import Rapper.Rapper;
import Themes.Theme;

import java.util.ArrayList;
import java.util.Random;

public class Threads implements Runnable {
    private final Rapper rapper1;
    private final Rapper rapper2;
    private final ArrayList<Theme> themes;
    private final String type;

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
        } catch (IndexOutOfBoundsException ignored) {
        }

        // 2
        try {
            rima = themes.get(topic).getRhymes(rapper2.getLevel() - 1, 0);
            puntuacio2 += score.countRhymes(rima, type);
        } catch (IndexOutOfBoundsException ignored) {
        }

        // 3
        try {
            rima = themes.get(topic).getRhymes(rapper1.getLevel() - 1, 0);
            puntuacio1 += score.countRhymes(rima, type);
        } catch (IndexOutOfBoundsException ignored) {
        }

        // 4
        try {
            rima = themes.get(topic).getRhymes(rapper2.getLevel() - 1, 0);
            puntuacio2 += score.countRhymes(rima, type);
        } catch (IndexOutOfBoundsException ignored) {
        }

        // Actualizamos la puntuacion
        rapper1.setScore(puntuacio1);
        rapper2.setScore(puntuacio2);

    }

    /**
     * @return (Rapper) Vuelve el rapero1
     */
    public Rapper getRapero1() {
        return rapper1;
    }

    /**
     * @return (Rapper) Vuelve el rapero2
     */
    public Rapper getRapero2() {
        return rapper2;
    }
}