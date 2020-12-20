package Rapper;

import java.util.Date;

public class Rapper {
    private String realName;
    private String stageName;
    private String birth;
    private String nationality;
    private int level;
    private String photo;

    public Rapper(String realName, String stageName, String birth2, String nationality, int level, String photo) {
        this.realName = realName;
        this.stageName= stageName;
        this.birth = birth2;
        this.nationality = nationality;
        this.level = level;
        this.photo = photo;
    }

    public String getStageName() {
        return stageName;
    }

}
