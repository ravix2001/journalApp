package com.ravi.journalApp.service;

import org.springframework.stereotype.Service;

@Service
public class SentimentAnalysisService {

    public String getSentimnet(String text){
        return "Positive";
    }

}
