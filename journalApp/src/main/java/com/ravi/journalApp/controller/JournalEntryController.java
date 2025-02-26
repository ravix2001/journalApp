package com.ravi.journalApp.controller;

import com.ravi.journalApp.entity.JournalEntry;
import com.ravi.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    //controller ---> service ---> repository

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<JournalEntry> all = journalEntryService.getAll();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public ResponseEntity<JournalEntry> create(@RequestBody JournalEntry journalEntry) {
        try{
            journalEntry.setDate(LocalDateTime.now());

            journalEntryService.saveEntry(journalEntry);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (IllegalArgumentException ex) {
            // Handle specific validation or argument-related issues
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            // Handle other unexpected issues
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("id/{id}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId id) {
        Optional<JournalEntry> journalEntry = journalEntryService.findById(id);
        if (journalEntry.isPresent()) {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<?> update(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {

        JournalEntry old = journalEntryService.findById(id).orElse(null);
        if (old != null) {
            //null xaina ra empty xaina vane naya entry haalne hoina vane puraaina rakhne
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId id) {
        journalEntryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
