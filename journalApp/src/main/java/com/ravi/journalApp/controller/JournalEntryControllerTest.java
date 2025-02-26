//package com.ravi.journalApp.controller;
//
//import com.ravi.journalApp.entity.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
////@RestController
////@RequestMapping("/journal")
//
//public class JournalEntryControllerTest {
//
//    private Map<Long, JournalEntry> journalEntries = new HashMap<>();
//
//    @GetMapping
//    public List<JournalEntry> getAll() {
//        return new ArrayList<> (journalEntries.values());
//    }
//
//    @PostMapping
//    public boolean create(@RequestBody JournalEntry journalEntry) {
//        journalEntries.put(journalEntry.getId(), journalEntry);
//        return true;
//    }
//
//    @GetMapping("id/{id}")
//    public JournalEntry findById(@PathVariable Long id) {
//        return journalEntries.get(id);
//    }
//
//    @PutMapping("id/{id}")
//    public boolean update(@PathVariable Long id, @RequestBody JournalEntry journalEntry) {
//        journalEntries.put(id, journalEntry);
//        return true;
//    }
//    @DeleteMapping("id/{id}")
//    public JournalEntry deleteById(@PathVariable Long id) {
//        return journalEntries.remove(id);
//    }
//}
