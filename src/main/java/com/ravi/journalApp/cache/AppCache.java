package com.ravi.journalApp.cache;

import com.ravi.journalApp.entity.Config;
import com.ravi.journalApp.repository.ConfigRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        WEATHER_API;
    }

    @Autowired
    private ConfigRepository configRepository;

    public Map<String, String> appCache;

    @PostConstruct
    public void init(){
        appCache = new HashMap<>();
        List<Config> all = configRepository.findAll();
        appCache = all.stream().collect(java.util.stream.Collectors.toMap(Config::getKey, Config::getValue));
//        appCache.put(keys.WEATHER_API.name(), all.stream().filter(config -> config.getKey().equals(keys.WEATHER_API.name())).findFirst().get().getValue());
    }
}


// The method annotated with `@PostConstruct` is automatically invoked by the container (e.g., Spring, Jakarta EE, etc.) after the bean initialization and all dependencies are set.
// Typically, it is used within a `@Component`, `@Bean`, or similar managed resources.