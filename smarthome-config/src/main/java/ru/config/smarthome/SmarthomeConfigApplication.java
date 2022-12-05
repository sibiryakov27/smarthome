package ru.config.smarthome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SmarthomeConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmarthomeConfigApplication.class, args);
    }

}
