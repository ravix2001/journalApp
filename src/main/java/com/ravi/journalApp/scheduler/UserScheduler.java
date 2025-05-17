package com.ravi.journalApp.scheduler;

import com.ravi.journalApp.cache.AppCache;
import com.ravi.journalApp.entity.JournalEntry;
import com.ravi.journalApp.entity.User;
import com.ravi.journalApp.repository.UserRepositoryImpl;
import com.ravi.journalApp.service.EmailService;
import com.ravi.journalApp.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    private AppCache appCache;


//    A cron expression is a string that defines a schedule for running tasks in Unix-like operating systems, consisting of fields that specify the timing of the task execution.
//    @Scheduled(cron = "0 * * ? * *")
    @Scheduled(cron = "0 0 8 * * 7")
    public void fetchUserAndSendMail(){
        List<User> userForSentimentAnalysis = userRepositoryImpl.getUserForSentimentAnalysis();
        for(User user : userForSentimentAnalysis){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<String> filteredEntries = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getContent()).collect(Collectors.toList());
            String contents = String.join(" ", filteredEntries);
            String sentiment = sentimentAnalysisService.getSentimnet(contents);
            emailService.sendEmail(user.getEmail(), "Sentiment analysis", sentiment);
        }
    }

    @Scheduled(cron = "0 0 0 * * 7")
    public void clearAppCache(){
        appCache.init();
    }
//    this is scheduled for clearing app cache every week
//    if want to clear app cache manually then you can call admin/clear-app-cache endpoint

}
