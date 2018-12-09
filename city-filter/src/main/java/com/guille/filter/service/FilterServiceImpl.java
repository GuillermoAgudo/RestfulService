package com.guille.filter.service;

import com.guille.filter.model.City;
import com.guille.filter.model.Itinerate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilterServiceImpl implements FilterService {
    public List<Itinerate> itinerates;
    public List<City> cities;
    public String originService;
    public int cont;


    public FilterServiceImpl(){
        itinerates = new ArrayList<>();
        cont = 0;
    }

    public List<City> getCities() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<City>> response = restTemplate.exchange(
                "http://localhost:9090/itinerate/cities",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<City>>(){});
        return response.getBody();
    }

    @Override
    public void getAllItinerates(String origin) throws Exception{

        originService = origin;
        cities = getCities();
        Itinerate itinerate = new Itinerate();
        Itinerate itinerate2 = new Itinerate();
        searchAllItinerates(origin,itinerate,itinerate2,cont);

    }

    @Override
    public List<Itinerate> getBestConection() throws Exception {

        List<Itinerate> bestItinerates = new ArrayList<>();
        Itinerate itinerate  = new Itinerate();
        int conections = 0;
        int conections2 = 0;
        boolean add;
        int cont = 0;
        String city;
        String city2;
        List<String> cities;
        List<String> schedule;

        for (Itinerate i:itinerates) {
            add = true;
            conections = i.getCitiesItinerate().size();
            city = i.getCitiesItinerate().get(i.getCitiesItinerate().size() -1);
            cont = 0;
            for (Itinerate s:itinerates) {
                itinerate = new Itinerate();
                city2 = s.getCitiesItinerate().get(s.getCitiesItinerate().size() -1);
                conections2 = s.getCitiesItinerate().size();

                if (city2.equals(city)){
                    if (conections2 < conections){
                        cont++;
                        cities = s.getCitiesItinerate();
                        schedule = s.getSchedule();
                        itinerate.setCitiesItinerate(cities);
                        itinerate.setSchedule(schedule);

                    }
                }
                if (cont == 0){
                    cities = i.getCitiesItinerate();
                    schedule = i.getSchedule();
                    itinerate.setCitiesItinerate(cities);
                    itinerate.setSchedule(schedule);
                }

            }

            bestItinerates = fillArray(itinerate,bestItinerates,add);
        }

        return bestItinerates;
    }

    @Override
    public List<Itinerate> getBestTime()throws Exception{
        setTimes();
        Itinerate itinerate = new Itinerate();
        List<String> cities;
        List<String> schedule;
        List<Itinerate> bestTimeItinerates = new ArrayList<>();
        String city;
        String city2;
        boolean add;
        int cont;
        float time;
        float time2;
        float time3;

        for (Itinerate i: itinerates){
            add = true;
            time = i.getTime();
            city = i.getCitiesItinerate().get(i.getCitiesItinerate().size() -1);
            cont = 0;
            for (Itinerate s: itinerates){
                itinerate = new Itinerate();
                time2 = s.getTime();
                city2 = s.getCitiesItinerate().get(s.getCitiesItinerate().size() -1);

                if (city2.equals(city)){
                    if (time2 < time){
                        cont++;
                        cities = s.getCitiesItinerate();
                        schedule = s.getSchedule();
                        time3 = s.getTime();
                        itinerate.setCitiesItinerate(cities);
                        itinerate.setSchedule(schedule);
                        itinerate.setTime(time3);
                    }
                }
                if (cont == 0){
                    time3 = i.getTime();
                    cities = i.getCitiesItinerate();
                    schedule = i.getSchedule();
                    itinerate.setCitiesItinerate(cities);
                    itinerate.setSchedule(schedule);
                    itinerate.setTime(time3);
                }
            }
            bestTimeItinerates = fillArray(itinerate,bestTimeItinerates,add);

        }


        return bestTimeItinerates;
    }

    public boolean filterOrigin(Itinerate itinerate,String origin){
        int i = 0;
        for (String city: itinerate.getCitiesItinerate()) {

            if (i >= 2 && i%2 ==0){
                if (city.equals(origin)){
                    return false;
                }
            }
            i++;
        }
        return true;
    }

    public boolean filterDestiny(Itinerate itinerate,String destiny){
        int i = 0;
        for (String city: itinerate.getCitiesItinerate()) {

            if (i >= 1 && i%2 != 0){
                if (city.equals(destiny)){
                    return false;
                }
            }
            i++;
        }
        return true;
    }

    public void addToItinerates(Itinerate itinerate){
        Itinerate itinerateTransfer = new Itinerate();
        itinerateTransfer.setCitiesItinerate(itinerate.getCitiesItinerate().stream().map(e -> e).collect(Collectors.toList()));
        itinerateTransfer.setSchedule(itinerate.getSchedule().stream().map(e -> e).collect(Collectors.toList()));
        itinerates.add(itinerateTransfer);
    }

    public void searchAllItinerates(String origin,Itinerate itinerate,Itinerate itinerate2,int cont){
        List<City> originCities = cities.stream().filter(city -> city.getCity().equals(origin)).collect(Collectors.toList());
        if (!originCities.isEmpty()) {
            for (City o: originCities) {
                if(o.getCity().equals(originService)){
                    itinerate = new Itinerate();
                    itinerate2 = new Itinerate();
                }
                cont = 0;
                if (!o.getDestinyCity().equals(originService) && filterDestiny(itinerate,o.getDestinyCity()) && (filterOrigin(itinerate,origin) || itinerate.getCitiesItinerate().isEmpty())) {
                    itinerate2.setCitiesItinerate(itinerate.getCitiesItinerate().stream().map(e -> e).collect(Collectors.toList()));
                    itinerate2.setSchedule(itinerate.getSchedule().stream().map(e -> e).collect(Collectors.toList()));
                    itinerate.getCitiesItinerate().add(origin);
                    itinerate.getSchedule().add(o.getDepartureTime());
                    itinerate.getCitiesItinerate().add(o.getDestinyCity());
                    itinerate.getSchedule().add(o.getArrivalTime());
                    cont = 1;
                    searchAllItinerates(o.getDestinyCity(),itinerate,itinerate2,cont);
                    addToItinerates(itinerate);

                }
                if (itinerate2.getCitiesItinerate().size() > 0 && cont == 1 ){
                    itinerate.setCitiesItinerate(itinerate2.getCitiesItinerate().stream().map(e -> e).collect(Collectors.toList()));
                    itinerate.setSchedule(itinerate2.getSchedule().stream().map(e -> e).collect(Collectors.toList()));
                }

            }
        }


    }

    public float calculateTime(String hour1,String hour2) {

        try{
            float minutes;
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            Date date1 = format.parse(hour1);
            Date date2 = format.parse(hour2);
            long difference = date2.getTime() - date1.getTime();
            System.out.println(difference);
            System.out.println(difference/1000);
            if (difference < 0){
                difference = difference/60000;
                difference = 24 * 60 - difference;
                minutes = difference;
            }else {
                minutes = difference /60000;
            }

            return minutes;
        }catch (ParseException  p){
            return 0;
        }


    }

    public void setTimes(){
        int cont;
        String hour1;
        String hour2;
        float hourDiff = 0;
        for (Itinerate i: itinerates) {
            cont = 0;
            hour1 = "";
            hourDiff = 0;
            for (String hour: i.getSchedule()) {
                if (cont == 0){
                    hour1 = i.getSchedule().get(cont);
                }
                else if (cont %2 != 0){
                    hour2 = i.getSchedule().get(cont);
                    hourDiff = hourDiff + calculateTime(hour1,hour2);
                }else if (cont %2 ==0){
                    hour1 = i.getSchedule().get(cont);
                }

                cont++;
            }
            i.setTime(hourDiff);
        }
    }

    public List<Itinerate> fillArray(Itinerate itinerate,List<Itinerate> bestItinerates,boolean add){
        if (!itinerate.getCitiesItinerate().isEmpty()){
            for (Itinerate z:bestItinerates){
                if (z.getCitiesItinerate().equals(itinerate.getCitiesItinerate())){
                    add = false;
                }
            }
            if (add){
                bestItinerates.add(itinerate);
            }
        }
        return bestItinerates;
    }
}
