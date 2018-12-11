package com.pivotal.logging.app.loggingapp;

import io.pivotal.logging.jcl.ApiUsingJCL;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication
public class LoggingAppApplication {

	static Logger LOGGER = LoggerFactory.getLogger(LoggingAppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LoggingAppApplication.class, args);
		LOGGER.info("Logging from slf4j logger!");
		ApiUsingJCL api = new ApiUsingJCL();
		api.doStuff();
	}
}
