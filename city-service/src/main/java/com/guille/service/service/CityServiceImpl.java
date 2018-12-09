package com.guille.service.service;

import com.guille.service.model.City;
import com.guille.service.model.CityDTO;
import com.guille.service.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    public CityRepository cityRepository;

    @Autowired
    public CityMapper cityMapper;

    @Override
    public List<CityDTO> getCities() throws Exception {
        Iterable<City> citiesIterator = cityRepository.findAll();
        List<City> cities = new ArrayList<>();

        citiesIterator.forEach(cities::add);

        return cities.stream().map(cityDb -> cityMapper.map(cityDb,CityDTO.class)).collect(Collectors.toList());

    }




}
