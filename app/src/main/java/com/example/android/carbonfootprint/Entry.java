package com.example.android.carbonfootprint;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Map;
import java.util.Date;
import java.sql.Timestamp;

@Entity(tableName = "entry_table")
public class Entry implements Comparable{

    @ColumnInfo(name="entry")
    private String name;

    @ColumnInfo(name="weight")
    private double weight;

    @ColumnInfo(name="emission")
    private double emission;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="timestamp")
    private Timestamp ts;


    public Entry(String name, double weight, double emission){
        Date date = new Date();
        long time = date.getTime();
        ts = new Timestamp(time);
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
