package Competicion;

import java.sql.Date;

public class Competicion {
    private String name;
    private Date startDate;
    private Date endDate;
    //private phases phases;

    public Competicion(String name, Date startDate, Date endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
