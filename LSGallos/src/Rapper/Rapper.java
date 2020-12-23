package Rapper;

import java.util.ArrayList;
import java.util.Collections;

public class Rapper {
    private String realName;
    private String stageName;
    private String birth;
    private String nationality;
    private int level;
    private String photo;
    private int score;
    private boolean winner;

    public Rapper(String realName, String stageName, String birth2, String nationality, int level, String photo) {
        this.realName = realName;
        this.stageName = stageName;
        this.birth = birth2;
        this.nationality = nationality;
        this.level = level;
        this.photo = photo;
        score = 0;
        winner = false;
    }

    public int getWinner(ArrayList<Rapper> rapper) {
        for (int i = 0; i < rapper.size(); i++) {
            if (rapper.get(i).winner == true) {
                return i;
            }
        }
        return 0;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getStageName() {
        return stageName;
    }

    public static void mostrarRanking(ArrayList<Rapper> rapper) {
        Collections.sort(rapper, Collections.reverseOrder());

        System.out.println("------------------------------------");
        System.out.println("| POS | Name               | SCORE |");
        System.out.println("------------------------------------");

        for (int i = 0; i < rapper.size(); i++) {
            System.out.println(i + " " + rapper.get(i).stageName + " " + rapper.get(i).score);
        }
    }
}
