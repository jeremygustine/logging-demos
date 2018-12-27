package io.pivotal.demo;

import org.apache.logging.log4j.LogManager;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import org.slf4j.LoggerFactory;

@Component
public class RunOnStartup implements ApplicationRunner {
    static org.slf4j.Logger SLF4J_LOGGER = LoggerFactory.getLogger(RunOnStartup.class);
    static org.apache.logging.log4j.Logger LOG4J2_LOGGER = LogManager.getLogger(RunOnStartup.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        SLF4J_LOGGER.info("Logging using slf4j api and log4j2 implementation!");
        LOG4J2_LOGGER.info("Logging using log4j2 api and log4j2 implementation!");
    }
}
