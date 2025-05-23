package com.ravi.journalApp.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Document(collection = "users")
@Data
@NoArgsConstructor      // to use @Builder we need to use @NoArgsConstructor and @AllArgsConstructor together otherwise error will occur
@AllArgsConstructor
@Builder        //this was used for testing purpose
public class User {

    @Id
    private ObjectId id;

    @Indexed(unique = true)
    @NonNull
    private String username;

    @NonNull
    private String password;

    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();

    private List<String> roles = new ArrayList<>();

    private String email;

    private boolean sentimentAnalysis;

}
