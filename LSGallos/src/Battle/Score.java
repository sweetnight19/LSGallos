package Battle;

import java.util.ArrayList;
import java.util.Random;

public class Score {

    public static String getType() {
        int type;
        Random random = new Random();
        type = random.nextInt(3);

        if (type == 0) {
            return "Sangre";
        } else {
            if (type == 1) {
                return "Acapella";
            } else {
                return "Escrita";
            }
        }
    }

    private int scoreSangre(int rhymes) {
        return (int) (Math.PI * Math.pow(rhymes, 2)) / 4;
    }

    private int scoreAcapella(int rhymes) {
        return (int) (6 * Math.sqrt(rhymes) + 3) / 2;
    }

    private int scoreEscrita(int rhymes) {
        return (int) 1 + 3 * rhymes;
    }

    public int countRhymes(String rima, String type) {
        int count;
        String paragref = "12";
        StringBuilder myString = new StringBuilder(paragref);
        ArrayList<String> cadenaStrings = new ArrayList<String>();

        count = 0;

        // Aqui el codi
        for (int i = 0; rima.charAt(i) != '.'; i++) {
            if (rima.charAt(i) == ',') {
                // paragref.replace(paragref.charAt(0), rima.charAt(i - 3));
                // paragref.replace(paragref.charAt(1), rima.charAt(i - 2));
                myString.setCharAt(0, rima.charAt(i - 2));
                myString.setCharAt(1, rima.charAt(i - 1));
                paragref = myString.toString();
                cadenaStrings.add(paragref);
            }
        }
        myString.setCharAt(0, rima.charAt(rima.length() - 3));
        myString.setCharAt(1, rima.charAt(rima.length() - 2));
        paragref = myString.toString();
        cadenaStrings.add(paragref);

        if (cadenaStrings.size() > 1) {
            if (cadenaStrings.get(0).equals(cadenaStrings.get(1))) {
                count++;
            }
        }
        if (cadenaStrings.size() > 2) {
            if (cadenaStrings.get(0).equals(cadenaStrings.get(2))) {
                count++;
            }
            if (cadenaStrings.get(1).equals(cadenaStrings.get(2))) {
                count++;
            }
        }
        if (cadenaStrings.size() > 3) {
            if (cadenaStrings.get(0).equals(cadenaStrings.get(3))) {
                count++;
            }
            if (cadenaStrings.get(1).equals(cadenaStrings.get(3))) {
                count++;
            }
            if (cadenaStrings.get(2).equals(cadenaStrings.get(3))) {
                count++;
            }
        }

        switch (type) {
            case "Sangre":
                return scoreSangre(count);
            case "Acapella":
                return scoreAcapella(count);
            case "Escria":
                return scoreEscrita(count);
        }
        return count;
    }

}