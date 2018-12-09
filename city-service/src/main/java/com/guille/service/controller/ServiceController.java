package com.guille.service.controller;

import com.guille.service.model.CityDTO;
import com.guille.service.service.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/itinerate")
@Api(value = "cities",tags = "Itinerate API")
public class ServiceController {
    @Autowired
    CityService cityService;

    @GetMapping(value = "/cities", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Gets the itinerates of a city" , response = CityDTO.class)
    public ResponseEntity<List<CityDTO>> getCities() throws Exception{

        return new ResponseEntity<List<CityDTO>>(cityService.getCities(), HttpStatus.OK);
    }


}
