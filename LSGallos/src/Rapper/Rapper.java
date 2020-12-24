package Rapper;

import java.util.ArrayList;

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

    public String getPhoto() {
        return photo;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getNationality() {
        return nationality;
    }

    public String getBirth() {
        return birth;
    }

    public String getRealName() {
        return realName;
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
        // Collections.sort(rapper, Collections.reverseOrder());
        rapper.sort((o1, o2) -> Float.compare(o2.getScore(), o1.getScore()));

        System.out.println("------------------------------------");
        System.out.println("| POS | Name               | SCORE |");
        System.out.println("------------------------------------");

        for (int i = 0; i < rapper.size(); i++) {
            System.out.println("  " + (i + 1) + "\t" + rapper.get(i).stageName + "   " + "\t\t" + rapper.get(i).score);
        }
    }
}
