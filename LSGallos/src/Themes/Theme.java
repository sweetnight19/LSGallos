package Themes;

import java.util.ArrayList;

public class Theme {
    private String name;
    private ArrayList<Rhymes> rhymes;

    public String getName() {
        return name;
    }

    public String getRhymes(int level,int turno) {
        if (level==1) {
            return rhymes.get(level-1).getNivell1(turno-1);
        } else {
            return rhymes.get(level-1).getNivell2(turno-1);
        }
        
    }
}
