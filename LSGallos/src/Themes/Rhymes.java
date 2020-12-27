package Themes;

import java.util.ArrayList;

public class Rhymes {
    // TODO: el nombre nivell1 y nivell2 no corresponden con el
    // json y no se almacena la informacion porque no concuerda,
    // hay que preguntar a pol la manera que explico

    private ArrayList<String> nivell1;
    private ArrayList<String> nivell2;

    public String getNivell1(int turno) {
        return nivell1.get(turno - 1);
    }

    public String getNivell2(int turno) {
        return nivell2.get(turno - 1);
    }
}
