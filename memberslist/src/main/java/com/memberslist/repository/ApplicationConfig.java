package com.memberslist.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories
class ApplicationConfig extends AbstractMongoConfiguration {

  @Override
  protected String getDatabaseName() {
    return "member";
  }

  public MongoClient mongoClient() {
    return new MongoClient();
  }

  @Override
  protected String getMappingBasePackage() {
    return "com.oreilly.springdata.mongodb";
  }

@Override
public Mongo mongo() throws Exception {
	return new MongoClient("127.0.0.1");
	
}
}
