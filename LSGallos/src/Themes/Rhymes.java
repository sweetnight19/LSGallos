package Themes;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;


public class Rhymes {
    
    @SerializedName("1")
    private ArrayList<String> nivell1;
    @SerializedName("2")
    private ArrayList<String> nivell2;

    
    /** 
     * @param turno
     * @return String
     */
    public String getNivell1(int turno) {
        return nivell1.get(turno);
    }

    
    /** 
     * @param turno
     * @return String
     */
    public String getNivell2(int turno) {
        return nivell2.get(turno);
    }
}
