package com.guille.service.service;

import com.guille.service.model.CityDTO;
import java.util.List;

public interface CityService {

    List<CityDTO> getCities() throws Exception;
}
