package Battle;

import java.util.Random;

public class Score {
    private static int type;

    public static String getType() {
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

    private int scoreEsctria(int rhymes) {
        return (int) 1 + 3 * rhymes;
    }

}
