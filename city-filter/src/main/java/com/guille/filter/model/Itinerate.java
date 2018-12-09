package com.guille.filter.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
//@JsonIgnoreProperties(value = { "time" })
public class Itinerate {

    private List<String> citiesItinerate  = new ArrayList<>();
    private List<String> schedule = new ArrayList<>();
    private float time ;

    public List<String> getCitiesItinerate() {
        return citiesItinerate;
    }

    public void setCitiesItinerate(List<String> citiesItinerate) {
        this.citiesItinerate = citiesItinerate;
    }

    public List<String> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<String> schedule) {
        this.schedule = schedule;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }
}
