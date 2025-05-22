package com.ravi.journalApp.controller;

import com.ravi.journalApp.entity.JournalEntry;
import com.ravi.journalApp.entity.User;
import com.ravi.journalApp.service.JournalEntryService;
import com.ravi.journalApp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
@Tag(name = "Journal API", description = "Journal Entries related operations")
public class JournalEntryController {

    //controller ---> service ---> repository

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "Get all journal entries of a user")
    public ResponseEntity<?> getAllJournalEntriesOfUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        List<JournalEntry> all = user.getJournalEntries();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Operation(summary = "Create a journal entry")
    public ResponseEntity<JournalEntry> create(@RequestBody JournalEntry journalEntry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        try{
            journalEntryService.saveEntry(journalEntry, username);

            return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
        }catch (IllegalArgumentException ex) {
            // Handle specific validation or argument-related issues
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            // Handle other unexpected issues
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("id/{id}")
    @Operation(summary = "Get a journal entry by journal id")
    public ResponseEntity<?> findById(@PathVariable String id) {
        ObjectId objectId = new ObjectId(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userInDb = userService.findByUsername(username);
        List<JournalEntry> collect = userInDb.getJournalEntries().stream().filter(x -> x.getId().equals(objectId)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JournalEntry> journalEntry = journalEntryService.findById(objectId);
            if(journalEntry.isPresent()) {
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{id}")
    @Operation(summary = "Update a journal entry by journal id")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody JournalEntry newEntry) {
        ObjectId objectId = new ObjectId(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(objectId)).collect(Collectors.toList());

        if (!collect.isEmpty()) {
            JournalEntry old = journalEntryService.findById(objectId).orElse(null);
            if (old != null) {
                //null xaina ra empty xaina vane naya entry haalne hoina vane puraaina rakhne
                old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : old.getTitle());
                old.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : old.getContent());
                journalEntryService.saveEntry(old);
                return new ResponseEntity<>(old, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @DeleteMapping("id/{id}")
    @Operation(summary = "Delete a journal entry by journal id")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        ObjectId objectId = new ObjectId(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        boolean removed = journalEntryService.deleteById(objectId, username);
        if(removed){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
