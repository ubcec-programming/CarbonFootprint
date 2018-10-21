package com.example.android.carbonfootprint;

import java.util.Map;
import java.util.Date;
import java.sql.Timestamp;
public class Entry implements  Comparable{
    private String name;
    private int code;
    private Date date = new Date();
    private double weight;
    private double emmision;
    private long time = date.getTime();
    private Timestamp ts = new Timestamp(time);


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public double getEmmision() {
        return emmision;
    }

    public void setEmmision(double emmision) {
        this.emmision = emmision;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Date getDate() {

        return date;
    }

    public void setDate(Date mydate) {
        this.date = mydate;
    }


    public Timestamp getTs() {
        return ts;
    }


    @Override
    public int compareTo(Object o) {
            assert((o instanceof Map.Entry)); // it has to be the same type
        return ts.compareTo((Timestamp) o);
    }

    @Override
    public int hashCode(){
        code = ts.hashCode();
        return code;
    }

}
