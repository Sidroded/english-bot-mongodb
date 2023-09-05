package com.sidroded.englishbotmongodb.database.repository;

import com.mongodb.client.result.UpdateResult;
import com.sidroded.englishbotmongodb.database.model.Dictionary;
import com.sidroded.englishbotmongodb.database.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import org.springframework.stereotype.Component;

@Component
public class CustomUserRepositoryImpl implements CustomUserRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void UpdateDictionaryName(String chatId, String name) {
        Query query = new Query(Criteria.where("chatId").is(chatId));
        Update update = new Update();
        update.set("name", name);

        UpdateResult result = mongoTemplate.updateFirst(query, update, User.class);

        if(result == null)
            System.out.println("No documents updated");
        else
            System.out.println(result.getModifiedCount() + " document(s) updated..");

    }
}
