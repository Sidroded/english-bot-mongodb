package com.sidroded.englishbotmongodb.database.repository;

import com.sidroded.englishbotmongodb.database.model.Dictionary;
import com.sidroded.englishbotmongodb.database.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query("{chatId:'?0'}")
    User findUserByChatId(String chatId);

    @Override
    List<User> findAll();
}
