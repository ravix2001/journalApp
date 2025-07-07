package com.ravi.journalApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journal_entries")
// For lombok @Data generates @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode
@Data
@NoArgsConstructor
public class JournalEntry {

    @Id
    @JsonIgnore
    private ObjectId id;

    @JsonProperty("id") // This exposes the string version in JSON
    public String getIdAsString() {
        return id != null ? id.toHexString() : null;
    }

    @NonNull
    private String title;

    private String content;

    private LocalDateTime date;

}





//    //Getters and Setters
//
//    public ObjectId getId() {
//        return id;
//    }
//
//    public void setId(ObjectId id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public LocalDateTime getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDateTime date) {
//        this.date = date;
//    }

