package com.guille.service.service;

import com.guille.service.model.City;
import com.guille.service.model.CityDTO;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class CityMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(City.class, CityDTO.class).byDefault().register();
    }
}
