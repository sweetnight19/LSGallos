package Rapper;

public class Rapper {
    private String realName;
    private String stageName;
    private String birth;
    private String nationality;
    private int level;
    private String photo;
    private int score;

    public Rapper(String realName, String stageName, String birth2, String nationality, int level, String photo) {
        this.realName = realName;
        this.stageName = stageName;
        this.birth = birth2;
        this.nationality = nationality;
        this.level = level;
        this.photo = photo;
        score=0;
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

    

}
