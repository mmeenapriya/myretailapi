package com.my.retail.myretail.config;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.util.StringUtils;

import java.util.Base64;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Value("${mongo.username}")
    private String mongoUsername;

    @Value("${mongo.password}")
    private String mongoPassword;

    @Value("${mongo.connectionString}")
    private String connectionString;

    @Value("${mongo.database}")
    private String mongoDatabase;

    @Override
    protected String getDatabaseName() {
        return mongoDatabase;
    }

    @Override
    @Bean
    public MongoClient mongoClient() {
        byte[] pwd = Base64.getDecoder().decode(StringUtils.trimAllWhitespace(mongoPassword));
        String connectionStringWithCredentials = connectionString.replaceFirst("MONGO_USERNAME", mongoUsername).replaceFirst("MONGO_PASSWORD", new String(pwd).replaceAll("\n",""));
        return MongoClients.create(connectionStringWithCredentials);
    }
}