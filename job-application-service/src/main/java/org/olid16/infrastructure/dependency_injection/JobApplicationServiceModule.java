package org.olid16.infrastructure.dependency_injection;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import org.olid16.domain.collections.Resumes;
import org.olid16.infrastructure.circuit_breaker.commands.GetUserByIdCommandFactory;
import org.olid16.infrastructure.clients.UserApi;
import org.olid16.infrastructure.repositories.MongoResumes;
import retrofit.RestAdapter;
import retrofit.converter.JacksonConverter;

import java.net.UnknownHostException;

public class JobApplicationServiceModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(Resumes.class).to(MongoResumes.class).in(Singleton.class);
        install(new FactoryModuleBuilder()
                .build(GetUserByIdCommandFactory.class));
    }

    @Provides @Singleton
    DBCollection resumes(){
        try {
            return new MongoClient().getDB("jobApplicationService").getCollection("resumes");
        } catch (UnknownHostException e) {
            throw new IllegalStateException(e);
        }
    }
    
    @Provides @Singleton UserApi userClient(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setConverter(jacksonConverter())
                .setEndpoint("http://localhost:8081")
                .build();
        return restAdapter.create(UserApi.class);
        
    }

    private JacksonConverter jacksonConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return new JacksonConverter(objectMapper);
    }


}