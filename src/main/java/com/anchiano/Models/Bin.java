package com.anchiano.Models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Bin {
    //    Id is auto incremented to keep adding to the list of bins. Each bin has a trash type, max capacity and geolocation to start with.
    //    The main value that will be updated constantly is the current capacity of the bin.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String trashType;

    @Column
    private int maxCapacity;

    @Column
    private int currentCapacity;

    @Column
    private String geoLocation;




    //Getters and Setters for all values
    public int getAllowedCapacity() {

        return 1750;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTrashType() {
        return trashType;
    }

    public void setTrashType(String trashType) {
        this.trashType = trashType;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public String getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(String geoLocation) {
        this.geoLocation = geoLocation;
    }
}
