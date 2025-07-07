package com.ravi.journalApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonIgnore
    private ObjectId id;

    @JsonProperty("id") // This exposes the string version in JSON
    public String getIdAsString() {
        return id != null ? id.toHexString() : null;
    }

    @Indexed(unique = true)
    @NonNull
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NonNull
    private String password;

    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();

    private List<String> roles = new ArrayList<>();

    private String email;

    private boolean sentimentAnalysis;

}
