package com.guille.service.repository;


import com.guille.service.model.City;
import org.springframework.data.repository.Repository;

public interface CityRepository extends Repository<City,Long> {
    Iterable<City> findAll();
    Iterable<City> findAllByCity(String city);
}
