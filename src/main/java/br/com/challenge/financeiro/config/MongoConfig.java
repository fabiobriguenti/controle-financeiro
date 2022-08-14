package br.com.challenge.financeiro.config;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

import br.com.challenge.financeiro.config.codec.YearMonthCodec;

@Configuration
public class MongoConfig {
	@Value("${spring.data.mongodb.uri}")
    private String connectionString;
	
    @Bean
    public MongoClient mongoClient() {
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
        		CodecRegistries.fromCodecs(new YearMonthCodec()),
        		MongoClientSettings.getDefaultCodecRegistry(),
        		CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        
        return MongoClients.create(MongoClientSettings.builder()
              .applyConnectionString(new ConnectionString(connectionString))
              .codecRegistry(codecRegistry)
              .build());
    }
}