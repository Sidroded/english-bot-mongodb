package com.sidroded.englishbotmongodb.database.config;

import com.sidroded.englishbotmongodb.database.repository.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = UserRepository.class)
public class MongoDBConfig {

}
