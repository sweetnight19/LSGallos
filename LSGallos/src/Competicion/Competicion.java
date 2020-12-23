package Competicion;

import java.util.ArrayList;

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
}
