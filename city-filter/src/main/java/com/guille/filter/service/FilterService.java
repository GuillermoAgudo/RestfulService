package com.guille.filter.service;

import com.guille.filter.model.City;
import com.guille.filter.model.Itinerate;

import java.util.List;


public interface FilterService {
    List<Itinerate>  getBestConection() throws Exception;
    List<Itinerate> getBestTime() throws Exception;
    void getAllItinerates(String origin) throws Exception;

}
