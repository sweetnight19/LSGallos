package Competicion;

import java.util.ArrayList;
import java.util.Random;

import Rapper.Rapper;
import Themes.Theme;

public class Competicion {
    private String name;
    private String startDate;
    private String endDate;
    private ArrayList<phases> phases;
    private int status;

    public String getName() {
        return name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getPhasesCount() {
        return phases.size();
    }

    public void batallaInicial(ArrayList<Rapper> rapper, ArrayList<Theme> themList, String type, int contrincant,
            String name) {
        Random random = new Random();
        int primer = random.nextInt(2);
        int topic = random.nextInt(themList.size());

        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Topic: " + themList.get(topic).getName());
        System.out.println();

        if (primer == 1) {
            //1
            System.out.println("A coin is tossed in the air and....\n" + name + " ,it's your turn! Drop it!");
            System.out.println("\nYour turn!\nEnter your verse: ");
            //2
            System.out.println();
            System.out.println(rapper.get(contrincant).getStageName()+ " :");
            System.out.println(themList.get(topic).getRhymes(rapper.get(contrincant).getLevel(), 1));
            //3
            System.out.println("Your turn!\nEnter your verse: ");
            //4
            System.out.println();
            System.out.println(rapper.get(contrincant).getStageName()+ " :");
            System.out.println(themList.get(topic).getRhymes(rapper.get(contrincant).getLevel(), 2));
        } else {
            //1
            System.out.println("A coin is tossed in the air and....\n" + rapper.get(contrincant).getStageName()
                    + " ,it's your turn! Drop it!");
            System.out.println(themList.get(topic).getRhymes(rapper.get(contrincant).getLevel(), 1));
            //2
            System.out.println("Your turn!\nEnter your verse: ");
            //3
            System.out.println();
            System.out.println(rapper.get(contrincant).getStageName()+ " :");
            System.out.println(themList.get(topic).getRhymes(rapper.get(contrincant).getLevel(), 2));
            //4
            System.out.println("Your turn!\nEnter your verse: ");
        }

    }

}
