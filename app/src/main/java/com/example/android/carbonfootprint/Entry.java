package com.example.android.carbonfootprint;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Map;
import java.util.Date;

@Entity(tableName = "entry_table")
public class Entry implements Comparable{

    @ColumnInfo(name="entry")
    private String name;

    @ColumnInfo(name="weight")
    private double weight;

    @ColumnInfo(name="emission")
    private double emission;

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setEmission(double emission) {
        this.emission = emission;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="time")
    private long time;


    public Entry(String name, double weight, double emission){
        Date date = new Date();
        this.time = date.getTime();
        this.name = name;
        this.weight = weight;
        this.emission = emission;
    }



    public String getName() {
        return name;
    }

    public double getEmission() {
        return emission;
    }

    public double getWeight() {
        return weight;
    }

    public long getTime() {
        return time;
    }

    @Override
    public int compareTo(Object o) {
            assert((o instanceof Map.Entry)); // it has to be the same type
        return ((Long)time).compareTo(((Entry)o).getTime());
    }

    @Override
    public int hashCode(){
        return name.hashCode();
    }

}
