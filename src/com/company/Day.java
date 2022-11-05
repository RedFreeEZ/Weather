package com.company;

import java.util.ArrayList;
import java.util.List;

public class Day {
    private final String date;
    private final String morning;
    private final String day ;
    private final String evening;



    public Day(String Date, String Morning, String Day, String Evening) {
        this.date = Date;
        this.morning = Morning;
        this.day  = Day ;
        this.evening = Evening;


    }

    public String getDate() {
        return date;
    }

    public String getMorning() {
        return morning;
    }

    public String getDay() {
        return day;
    }

    public String getEvening() {
        return evening;
    }
}


