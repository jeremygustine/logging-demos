package io.pivotal.logging.jcl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ApiUsingJCL {

    private static Log LOGGER = LogFactory.getLog(ApiUsingJCL.class);

    public void doStuff() {
        LOGGER.info("Logging from Java Commons Logging!");
    }
}
