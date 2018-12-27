package io.pivotal.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
//        Exception ex  = new Exception();
//        ex.printStackTrace();
        SpringApplication.run(Main.class, args);
//        LOGGER.info("Logging from slf4j logger!");
    }
}
