package com.ravi.journalApp.repository;

import com.ravi.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    // check if user exists or not in database based on username not objectId and then the user can be updated or deleted
    User findByUsername(String username);

    void deleteById(ObjectId id);

    void deleteByUsername(String username);

}
