package com.example.android.carbonfootprint;

import java.util.Map;
import java.util.Date;
import java.sql.Timestamp;
public class Entry implements  Comparable{
    private String name;
    private double weight;
    private double emmission;
    private Timestamp ts;


    public void entry(String name, double weight, double emmission){
        Date date = new Date();
        long time = date.getTime();
        ts = new Timestamp(time);
        this.name = name;
        this.weight = weight;
        this.emmission = emmission;
    }

    public String getName() {
        return name;
    }

    /*public void setName(String name) {
        this.name = name;
    }
    */


    public double getEmmision() {
        return emmission;
    }

    /*public void setEmmision(double emmision) {
        this.emmission = emmision;
    }
    */
    public double getWeight() {
        return weight;
    }
    /*
    public void setWeight(double weight) {
        this.weight = weight;
    }
    */

    /*public Date getDate() {

        return date;
    }

    public void setDate(Date mydate) {
        this.date = mydate;
    }
    */

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
        return ts.hashCode();

    }

}
