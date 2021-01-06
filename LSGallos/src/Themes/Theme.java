package Themes;

import java.util.ArrayList;

public class Theme {
    private String name;
    private ArrayList<Rhymes> rhymes;

    
    /** 
     * @return String
     */
    public String getName() {
        return name;
    }

    
    /** 
     * @param level
     * @param turno
     * @return String
     */
    public String getRhymes(int level,int turno) {
        if (level==0) {
            return rhymes.get(level).getNivell1(turno);
        } else {
            return rhymes.get(level).getNivell2(turno);
        }
        
    }
}
