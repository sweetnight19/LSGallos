package Battle;

import java.util.ArrayList;
import java.util.Random;

public class Score {

    /**
     * @return Modalidad de la batalla
     */
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

    /**
     * @param rhymes (int) Número de rimas válidas
     * @return (int) Cálculo matemático segun el tipo "sangre"
     */
    private int scoreSangre(int rhymes) {
        return (int) (Math.PI * Math.pow(rhymes, 2)) / 4;
    }

    /**
     * @param rhymes (int) Número de rimas válidas
     * @return (int) Cálculo matemático segun el tipo "Acapella"
     */
    private int scoreAcapella(int rhymes) {
        return (int) (6 * Math.sqrt(rhymes) + 3) / 2;
    }

    /**
     * @param rhymes (int) Número de rimas válidas
     * @return (int) Cálculo matemático segun el tipo "Escrita"
     */
    private int scoreEscrita(int rhymes) {
        return 1 + 3 * rhymes;
    }

    /**
     * @param rima (String) Rima para analizar
     * @param type (String) Tipo de rima para calcular su puntuación
     * @return (int) Puntuación a esa rima segun el tipo de batalla
     */
    public int countRhymes(String rima, String type) {
        int count;
        String paragref = "12";
        StringBuilder myString = new StringBuilder(paragref);
        ArrayList<String> cadenaStrings = new ArrayList<>();

        count = 0;

        for (int i = 0; rima.charAt(i) != '.'; i++) {
            if (rima.charAt(i) == ',') {
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

        return switch (type) {
            case "Sangre" -> scoreSangre(count);
            case "Acapella" -> scoreAcapella(count);
            case "Escria" -> scoreEscrita(count);
            default -> count;
        };
    }

}