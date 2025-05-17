package com.ravi.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse {

    private Main main;

    @Getter
    @Setter
    public class Main{

        public double temp;

        @JsonProperty("feels_like")
        public double feelsLike;        //convert all snake case to camel case in java  so we use @JsonProperty to convert it

        @JsonProperty("temp_min")
        public double tempMin;

        @JsonProperty("temp_max")
        public double tempMax;

    }
}

// We filter it out from the below code according to our needs

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */

//public class Clouds{
//    public int all;
//}
//
//public class Coord{
//    public double lon;
//    public double lat;
//}
//
//public class Main{
//    public double temp;
//    public double feels_like;
//    public double temp_min;
//    public double temp_max;
//    public int pressure;
//    public int humidity;
//    public int sea_level;
//    public int grnd_level;
//}
//
//public class Root{
//    public Coord coord;
//    public ArrayList<Weather> weather;
//    public String base;
//    public Main main;
//    public int visibility;
//    public Wind wind;
//    public Clouds clouds;
//    public int dt;
//    public Sys sys;
//    public int timezone;
//    public int id;
//    public String name;
//    public int cod;
//}
//
//public class Sys{
//    public String country;
//    public int sunrise;
//    public int sunset;
//}
//
//public class Weather{
//    public int id;
//    public String main;
//    public String description;
//    public String icon;
//}
//
//public class Wind{
//    public double speed;
//    public int deg;
//    public double gust;
//}