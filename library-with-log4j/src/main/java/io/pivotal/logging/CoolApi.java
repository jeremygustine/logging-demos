package io.pivotal.logging;

import org.apache.log4j.Logger;

public class CoolApi {
    final static Logger logger = Logger.getLogger(CoolApi.class);

    public void doApiStuff() {
        org.apache.log4j.BasicConfigurator.configure(); //will this screw things up?
        logger.info("Logging from the library that includes log4j!");
    }
}