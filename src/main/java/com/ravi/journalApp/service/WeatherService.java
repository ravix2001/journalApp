package com.ravi.journalApp.service;

import com.ravi.journalApp.api.response.WeatherResponse;
import com.ravi.journalApp.cache.AppCache;
import com.ravi.journalApp.constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

//    Method-1 : Using API as static
//    private static final String API = "https://api.openweathermap.org/data/2.5/weather?q=CITY&appid=API_KEY";
//
//    public WeatherResponse getWeather(String city) {
//        String finalAPI = API.replace("CITY", city).replace("API_KEY", apiKey);
//        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalUrl, HttpMethod.GET, null, WeatherResponse.class);       // Deserialization -> process of converting JSON response into corresponding Java object
//        WeatherResponse weatherResponse = response.getBody();
//        return weatherResponse;
//    }


//    Method-2 : Using API from AppCache (from database)

    @Autowired
    private AppCache appCache;

    public WeatherResponse getWeather(String city) {
//        String finalAPI = appCache.appCache.get("WEATHER_API").replace("<city>", city).replace("<apiKey>", apiKey);
//        We avoid using hardcode so we can make enum or repository
        String finalAPI = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(Placeholders.CITY, city).replace(Placeholders.API_KEY, apiKey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);       // Deserialization -> process of converting JSON response into corresponding Java object
        WeatherResponse weatherResponse = response.getBody();
        return weatherResponse;
    }


}
