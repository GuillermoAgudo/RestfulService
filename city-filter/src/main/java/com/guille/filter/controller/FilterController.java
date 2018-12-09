package com.guille.filter.controller;

import com.guille.filter.model.Itinerate;
import com.guille.filter.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/itinerate")
public class FilterController {

    @Autowired
    FilterService filterService;

    @GetMapping(value = "/ConectionCity",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Itinerate>> getConection(@RequestParam(value = "city") String city) throws Exception{

            filterService.getAllItinerates(city);

            return new ResponseEntity<List<Itinerate>>(filterService.getBestConection(), HttpStatus.OK);
    }

    @GetMapping(value = "/TimeCity",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Itinerate>> getTime(@RequestParam (value = "city") String city) throws Exception{

            filterService.getAllItinerates(city);

            return new ResponseEntity<List<Itinerate>>(filterService.getBestTime(), HttpStatus.OK);
    }
}
