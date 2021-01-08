package Themes;

import java.util.ArrayList;

public class Theme {
    private final String name;
    private final ArrayList<Rhymes> rhymes;

    public Theme(String name, ArrayList<Rhymes> rhymes) {
        this.name = name;
        this.rhymes = rhymes;
    }

    /**
     * @return (String) Devuelve el nombre del tema
     */
    public String getName() {
        return name;
    }

    /**
     * @param level (int) Nivel del rapero
     * @param turno (int) Turno 0 o 1 de la batalla
     * @return (String) Devuelve la rima segun el nivel y el turno de la batalla
     */
    public String getRhymes(int level, int turno) {
        if (level == 0) {
            return rhymes.get(level).getNivell1(turno);
        } else {
            return rhymes.get(level).getNivell2(turno);
        }

    }
}
