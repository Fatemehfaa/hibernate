package com.example.hibernate.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ManualForLoopWithIterator {
    public static void main(String[] args) {


        List<City> cities = new ArrayList<>(List.of(
                new City(1, "tehran")
                , new City(2, "kish")
                , new City(3, "tabriz")
        ));

        Iterator<City> iterator = cities.iterator();


        while (iterator.hasNext()) {
            City city = iterator.next();
            System.out.println(city.getCityName());

        }
    }
}
