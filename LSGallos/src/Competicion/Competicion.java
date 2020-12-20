package Competicion;

import java.util.ArrayList;

public class Competicion {
    private String name;
    private String startDate;
    private String endDate;
    private ArrayList<phases> phases;

    public String getName() {
        return name;
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
}
