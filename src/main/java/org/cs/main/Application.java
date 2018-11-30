package org.cs.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "org.cs")
@EnableReactiveMongoRepositories(basePackages = "org.cs")
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
